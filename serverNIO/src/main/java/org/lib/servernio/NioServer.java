package org.lib.servernio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class NioServer implements Runnable {

    private final Selector socketSelector;
    static final int COMMAND_BUFF_LEN = 2048;
    static final Logger LOG = Logger.getLogger(NioServer.class.getName());
    private final ExecutorService threadPool = Executors.newCachedThreadPool();

    public NioServer(int port) throws IOException {
        socketSelector = SelectorProvider.provider().openSelector();
        ServerSocketChannel serverChannel
                = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));
        serverChannel.register(socketSelector,
                SelectionKey.OP_ACCEPT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                LOG.info("waiting for client or command");
                int n = socketSelector.select();
                Iterator<SelectionKey> selectedKeys
                        = socketSelector.selectedKeys().iterator();
                while (selectedKeys.hasNext()) {
                    SelectionKey key = selectedKeys.next();
                    selectedKeys.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    }
                }
            } catch (Exception e) {
                LOG.log(Level.SEVERE, null, e);
            }
        }

    }

    private void accept(SelectionKey key) throws IOException {
        ServerSocketChannel serverSocketChannel
                = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(socketSelector,
                SelectionKey.OP_READ);
        LOG.info("accepted channel: " + socketChannel);
    }

    private void read(SelectionKey key) throws IOException {
        SocketChannel socketChannel
                = (SocketChannel) key.channel();
        LOG.info("read channel: " + socketChannel);
        ByteBuffer readBuffer = (ByteBuffer) (key.attachment());
        if (readBuffer == null) {
            readBuffer = ByteBuffer.allocate(COMMAND_BUFF_LEN);
            key.attach(readBuffer);
        }

        int numRead;
        try {
            numRead = socketChannel.read(readBuffer);
        } catch (IOException e) {
            LOG.info(e.toString());
// The remote forcibly closed the connection, 
            socketChannel.close();
            key.cancel();
            return;
        }
        if (numRead == -1) // Remote entity shut the socket down cleanly.
        {
            socketChannel.close();
            key.cancel();
            return;
        }
        int commandLength = readBuffer.getShort(0);
        if (commandLength >= COMMAND_BUFF_LEN - 2) {
            throw new RuntimeException("commandLength >= COMMAND_BUFF_LEN - 2");
        }
        if (commandLength > readBuffer.position()) {
            return; // zpráva není celá
        }
        readBuffer.flip();
        byte[] req
                = Arrays.copyOfRange(readBuffer.array(), 2, 2 + commandLength);
        readBuffer.position(2 + commandLength);
        readBuffer.compact();
        threadPool.execute(new ClientTask(req, socketChannel));
        // new ClientTask(req, socketChannel).run();
    }

}

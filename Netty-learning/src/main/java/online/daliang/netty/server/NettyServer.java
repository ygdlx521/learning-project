package online.daliang.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {
    private final static int port = 8088;

    public static void main(String[] args) {
        new NettyServer().start();
    }

    private void start(){
        final ServerHandler serverHandler = new ServerHandler();

        NioEventLoopGroup masterLoopGroup = new NioEventLoopGroup();
        NioEventLoopGroup slaveLoopGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(
                masterLoopGroup,slaveLoopGroup
        ).channel(
                NioServerSocketChannel.class
        ).localAddress(
                new InetSocketAddress(port)
        ).childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(serverHandler);
            }
        });


        try {
            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            masterLoopGroup.shutdownGracefully();
            slaveLoopGroup.shutdownGracefully();
        }


    }


}

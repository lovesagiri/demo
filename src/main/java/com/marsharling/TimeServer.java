package com.marsharling;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/20 16:12
 * @Version 1.0
 **/
public class TimeServer {

    public void bind(int port) throws Exception{
        //用于客户端的的链接
        EventLoopGroup bossgroup = new NioEventLoopGroup();
        //用于网络io的读写
        EventLoopGroup workergroup = new NioEventLoopGroup();
        try{
            //这是一个辅助类，帮我们快速的又边界的使用nio
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(bossgroup,workergroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChildChannelHandler());

            ChannelFuture cf = sb.bind(port).sync();

            cf.channel().closeFuture().sync();
        }finally {
            bossgroup.shutdownGracefully();
            workergroup.shutdownGracefully();
        }

    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ByteBuf dellimiter = Unpooled.copiedBuffer("$_".getBytes());
            socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,dellimiter));
            socketChannel.pipeline().addLast(new StringDecoder());
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }
    public static void main( String[] args ) throws Exception {
        //System.out.println( "Hello World!" );
        int port = 8080;
        new TimeServer().bind(port);
    }
}

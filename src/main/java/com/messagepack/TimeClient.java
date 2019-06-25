package com.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/20 16:56
 * @Version 1.0
 **/
public class TimeClient {

    public void  connect (int port, String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bs = new Bootstrap();
            bs.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast("msg encoder",new MessagePackEncoder());
                            socketChannel.pipeline().addLast("msg decoder",new MessagePackDecoder());
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            ChannelFuture cf = bs.connect(host,port).sync();
            cf.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }

    public static void main( String[] args ) throws Exception {
        int port = 8088;
        new TimeClient().connect(port,"127.0.0.1");
    }


}

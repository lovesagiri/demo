package com.msgpack;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/26 10:19
 * @Version 1.0
 **/
public class EchoServer {
    public void bind(int port) throws Exception{
        // 配置服务端的ＮＩＯ线程
        NioEventLoopGroup groupBig = new NioEventLoopGroup();
        NioEventLoopGroup groupSmall  = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(groupBig,groupSmall)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //注意：1、此处利用netty解决粘包时，自定义的编解码器，一定要放在"最后"
                            socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0,2,0,2));
                            //自定义编解码器
                            socketChannel.pipeline().addLast("msgpack decode", new MsgpackDecoder());
                            socketChannel.pipeline().addLast("msgpack encode", new MsgpackEncoder());
                            //2、也可以编码与解码乱序 例如
                            /*socketChannel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                            //自定义编解码器
                            socketChannel.pipeline().addLast("msgpack encode", new MsgpackEncoder());
                            socketChannel.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0,2,0,2));
                            //自定义编解码器
                            socketChannel.pipeline().addLast("msgpack decode", new MsgpackDecoder());*/
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            // 绑定端口，同步等待成功
            ChannelFuture future = bootstrap.bind(port).sync();
            // 等待服务端监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            // 退出，释放资源
            groupBig.shutdownGracefully();
            groupSmall.shutdownGracefully();
        }
    }

    public static void main(String[] args)throws Exception {
        int port = 9090;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        new EchoServer().bind(port);
    }
}
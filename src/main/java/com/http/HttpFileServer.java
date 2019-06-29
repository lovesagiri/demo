package com.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @Description http文件服务器启动类
 * @Author by yangzhengyang
 * @Date 2019/6/26 15:59
 * @Version 1.0
 **/
public class HttpFileServer {

    private  final static String  DEFAULT_URL = "src/com/phei/netty/";

    public void run(final int port,final String url)throws Exception{
        EventLoopGroup boosgoup = new NioEventLoopGroup();
        EventLoopGroup workgoup = new NioEventLoopGroup();
        try{
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(boosgoup,workgoup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast("http-decoder",
                                new HttpRequestDecoder());
                        socketChannel.pipeline().addLast("http-aggre",
                                new HttpObjectAggregator(65536));
                        socketChannel.pipeline().addLast("http-encoder",
                                new  HttpResponseEncoder());
                        socketChannel.pipeline().addLast("http-chunked",
                                new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast("httpfilehandler",
                                new HttpFileServerHandler(url));                    }
                });
            ChannelFuture cf = sb.bind("192.168.1.102",port).sync();
            cf.channel().closeFuture().sync();
        }finally {
            boosgoup.shutdownGracefully();
            workgoup.shutdownGracefully();
        }
    }
    public static void main(String[] args) throws Exception{
        int port = 8080;
        String url = DEFAULT_URL;
        new HttpFileServer().run(port,url);
    }
}

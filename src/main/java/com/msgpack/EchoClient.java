package com.msgpack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/26 10:23
 * @Version 1.0
 **/
public class EchoClient {
    private final String host;
    private final int port;
    private final int sendNumber;

    public EchoClient(String host, int port, int sendNumber) {
        this.host = host;
        this.port = port;
        this.sendNumber = sendNumber;
    }

    public void run()throws Exception{
        // 配置客户端NIO线程组
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //注意：1、此处利用netty解决粘包时，自定义的编解码器，一定要放在"最后"
                            //此处解决粘包的原理是：首先利用netty的编解码器先划分完整消息报，然后再利用自定义的编解码器
                            //去转译消息，所以就产生了顺序问题
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
                            socketChannel.pipeline().addLast(new EchoClientHandler(sendNumber));

                        }
                    });
            // 发起异步连接操作
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 等待客户端链路关闭
            future.channel().closeFuture().sync();
        }finally {
            // 优雅退出
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        int port = 9090;
        if (args != null && args.length > 0){
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }
        new EchoClient("localhost", port,50).run();
    }
}

package com.marsharling;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/20 17:09
 * @Version 1.0
 **/
public class TimeClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(TimeClientHandler.class.getName());

   // private final ByteBuf firstmessage;
    private int counter;
    private String message ="sagiri"+"$_";

    public TimeClientHandler(){
       /* byte[] request = "sagiri".getBytes();
        firstmessage = Unpooled.buffer(request.length);
        firstmessage.writeBytes(request);*/
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++)
        ctx.writeAndFlush(Unpooled.copiedBuffer(message.getBytes()));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       /* ByteBuf buf = (ByteBuf) msg;
        byte[] request = new byte[buf.readableBytes()];
        buf.readBytes(request);
        String body = new String(request,"UTF-8");*/
       String body = (String) msg;
       System.out.println("客户端收到的第："+ ++counter + "  条消息！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("出错了====="+ cause.getMessage());
        ctx.close();
    }

}

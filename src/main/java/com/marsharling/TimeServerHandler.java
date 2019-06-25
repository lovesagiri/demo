package com.marsharling;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/20 16:34
 * @Version 1.0
 **/
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        System.out.println("time server receivc msg " + body);
        String curtime = body.equalsIgnoreCase("sagiri") ? "03.27" : "00:00";
        curtime = curtime +"$_";
        ByteBuf response = Unpooled.copiedBuffer(curtime.getBytes());
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }


}

package com.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/20 16:34
 * @Version 1.0
 **/
public class TimeServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        List<Object> list = (List<Object>) msg;
        System.out.println("服务器收到的消息：" + list);
        ctx.writeAndFlush(list);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务器收到的消息：" + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }


}

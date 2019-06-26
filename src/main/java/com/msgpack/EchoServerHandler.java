package com.msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/26 10:21
 * @Version 1.0
 **/
public class EchoServerHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 打印接收到的消息
        List<Object> list = (List<Object>)msg;
        System.out.println(list);
        System.out.println("服务端收到客户端发来的消息是：" + list);
        ctx.writeAndFlush(list);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

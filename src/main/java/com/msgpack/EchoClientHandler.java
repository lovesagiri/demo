package com.msgpack;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * @Description TODO
 * @Author by yangzhengyang
 * @Date 2019/6/26 10:24
 * @Version 1.0
 **/
public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 获取一个填充好数据的数组
        User[] user = User();
        // 遍历这个数组，
        for (User user1 : user) {
            ctx.writeAndFlush(user1);
        }
    }

    private User[] User(){
        // 创建一个user数组
        User[] users = new User[sendNumber];
        User user = null;
        for (int i = 0; i < sendNumber; i++) {
            user = new User();
            user.setAge(i);
            user.setName("mqs" + i);
            users[i] = user;

        }
        return users;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到服务端的返回数据: " + msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

package com.messagepack;

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

    private int sendnum = 10;

    public TimeClientHandler(){
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        User[] users = arrayUser();
        for (User user:users){
            ctx.writeAndFlush(user);
        }
    }

    private User[] arrayUser() {
       User[]  users = new User[this.sendnum];
       User user = null;
       for (int i=0;i<this.sendnum;i++){
         user = new User();
         user.setName("用户名字：-》"+i);
         user.setAge(i);
         users[i] = user;
       }
       return users;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       System.out.println("客户端收到："+ msg);
       ctx.write(msg);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warning("出错了====="+ cause.getMessage());
        ctx.close();
    }

}

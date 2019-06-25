package com.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * @Description MessagePack 编码器 负责将object 编码成二进制字节
 * @Author by yangzhengyang
 * @Date 2019/6/25 17:36
 * @Version 1.0
 **/
public class MessagePackEncoder extends MessageToByteEncoder<Object> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        //首先将对象利用MessagePack编码成字节数组
        MessagePack messagePack = new MessagePack();
        byte[] row = messagePack.write(o);
        //将字节数组写入缓冲区
        byteBuf.writeBytes(row);
    }
}

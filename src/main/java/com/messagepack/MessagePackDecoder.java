package com.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * @Description  MessagePack 解码器
 * @Author by yangzhengyang
 * @Date 2019/6/25 17:43
 * @Version 1.0
 **/
public class MessagePackDecoder extends MessageToMessageDecoder<ByteBuf> {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //首先创建一个byte数组中间变量 用于存储对象的字节形式
       final byte[] array ;
       final int length = byteBuf.readableBytes();
        array = new byte[length];
        //将数据从缓冲区读入array 变量
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,length);
        MessagePack messagePack = new MessagePack();
        //将数据解码并加入list中
        list.add(messagePack.read(array));
    }
}

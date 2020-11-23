package com.xcl.nio.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.nio.ByteBuffer;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/23/16:09
 * @Description:
 */
public class NettyServerHandler  extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx="+ctx);
       ByteBuf byteBuf =  (ByteBuf)msg;
        System.out.println("客户端发送消息是：" +byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址是："+ ctx.channel().remoteAddress());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello ,客户端~ o(=•ェ•=)m",CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}

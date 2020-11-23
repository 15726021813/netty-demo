package com.xcl.nio.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @Auther: 徐长乐
 * @Date: 2020/11/23/16:19
 * @Description:
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(" client+"+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,server: o(=•ェ•=)m喵", CharsetUtil.UTF_8));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务器回复的消息："+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址："+ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();

    }
}

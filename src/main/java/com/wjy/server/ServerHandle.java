package com.wjy.server;

import java.net.SocketAddress;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandle extends ChannelInboundHandlerAdapter {

	private ChannelId id = null;
	private SocketAddress address = null;

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		Channel c = ctx.channel();
		id = c.id();
		address = c.localAddress();
		System.out.println("客户端" + id + "已连接 - " + address);
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("客户端" + id + "消息等待中。。。");
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("客户端" + id + "：" + msg);
		ctx.writeAndFlush(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
		cause.printStackTrace();
	}

}

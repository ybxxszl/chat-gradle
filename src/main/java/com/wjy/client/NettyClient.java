package com.wjy.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {

	private static final String HOST = "127.0.0.1";
	private static final Integer PORT = 8888;

	public static void main(String[] args) {

		NioEventLoopGroup group = new NioEventLoopGroup();

		try {

			Bootstrap b = new Bootstrap();

			b.group(group);
			b.channel(NioSocketChannel.class);
			b.handler(new ChannelHandle());

			ChannelFuture channelFuture = b.connect(HOST, PORT).sync();

			channelFuture.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}

	}

}

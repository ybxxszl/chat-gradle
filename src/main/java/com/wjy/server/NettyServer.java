package com.wjy.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {

	private static final Integer PORT = 8888;

	public static void main(String[] args) {

		// 接收已进来的连接
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		// 处理已进来的连接
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		System.out.println("监听端口：" + PORT);

		try {

			// 创建服务器
			ServerBootstrap b = new ServerBootstrap();

			// 设置服务
			b.group(bossGroup, workerGroup);
			// 使用NioServerSocketChannel接收已进来的连接
			b.channel(NioServerSocketChannel.class);
			// 设置日志级别
			b.handler(new LoggingHandler(LogLevel.ERROR));
			// 设置处理已进来的连接的方法
			b.childHandler(new ChannelHandle());
			// 设置存储已进来的连接的空间大小
			b.option(ChannelOption.SO_BACKLOG, 1024);
			// 开启心跳保活机制
			b.childOption(ChannelOption.SO_KEEPALIVE, true);

			// 等待连接
			ChannelFuture channelFuture = b.bind(PORT).sync();

			channelFuture.channel().closeFuture().sync();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}

	}

}

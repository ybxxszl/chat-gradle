package com.wjy.client;

import java.nio.charset.Charset;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChannelHandle extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {

		// 编码转String
		channel.pipeline().addLast(new StringEncoder(Charset.forName("UTF-8")));
		// 解码转String
		channel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));

		channel.pipeline().addLast(new ClientHandle());

	}

}

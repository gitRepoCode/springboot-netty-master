package com.gj.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author Gjing
 *
 * netty服务初始化器
 **/
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

//        //解码格式
//        socketChannel.pipeline().addLast(new StringDecoder(Charset.forName("UTF-8")));
//        //添加编解码
//        socketChannel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
//
//
//
//       socketChannel.pipeline().addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
//       socketChannel.pipeline().addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));

        //发送消息格式
        socketChannel.pipeline().addLast(new MyEncoder());
        //接受消息格式
        socketChannel.pipeline().addLast(new MyDecoder());

        socketChannel.pipeline().addLast(new NettyServerHandler());
    }
}

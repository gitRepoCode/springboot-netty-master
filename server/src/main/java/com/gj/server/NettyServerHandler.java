package com.gj.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Gjing
 *
 * netty服务端处理器
 **/
@Slf4j
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 客户端连接会触发
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("Channel active......");
    }

    /**
     * 客户端发消息会触发
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        log.info("服务器收到消息: {}", msg.toString());
        analysis(msg.toString());
        ctx.write("68265416");
        ctx.flush();
    }

    /**
     * 发生异常触发
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void analysis(String s){
        log.info("帧头：" + s.substring(0,2));
        log.info("帧长度：" + s.substring(2,6));
        log.info("协议版本：" + s.substring(6,12));
        log.info("地址：" + s.substring(12,28));
        log.info("报文生成时间：" + s.substring(28,44));
        log.info("控制码：" + s.substring(44,46));
        log.info("帧序号：" + s.substring(46,50));
        log.info("备用字段：" + s.substring(50,54));
        log.info("命令码：" + s.substring(54,56));
        log.info("用户数据长度：" + s.substring(56,60));
        log.info("用户数据：" + s.substring(60,60+Integer.valueOf(s.substring(56,60),16)*2));
        log.info("校验和：" + s.substring(s.length()-4,s.length()-2));
        log.info("帧尾：" + s.substring(s.length()-2));
    }
}

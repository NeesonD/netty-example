package com.neeson.netty;

import com.neeson.netty.core.codc.RequestDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * Create by neeson on 2018/4/6
 */
@Component
public class Server {

    public void start(){
        //引导类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //创建boss与work线程池
        NioEventLoopGroup bossThreadPool = new NioEventLoopGroup();
        NioEventLoopGroup workThreadsPool = new NioEventLoopGroup();

         try {
             //设置线程池
             bootstrap.group(bossThreadPool,workThreadsPool);

             //设置channel工厂
             bootstrap.channel(NioServerSocketChannel.class);

             bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 protected void initChannel(SocketChannel socketChannel) throws Exception {
                     socketChannel.pipeline().addLast(new RequestDecoder());
                     socketChannel.pipeline().addLast(new Respo)
                 }
             })
         }
    }
}

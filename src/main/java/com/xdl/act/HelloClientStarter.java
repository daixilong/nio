package com.xdl.act;

import java.io.IOException;

import org.tio.client.AioClient;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.intf.ClientAioHandler;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Aio;
import org.tio.core.Node;

public class HelloClientStarter {
  //服务器节点
	public static Node serverNode = new Node(Const.SERVER, Const.PORT);
	
//handler, 包括编码、解码、消息处理
	public static ClientAioHandler aioClientHandler = new HelloClientAioHandler();
	
//事件监听器，可以为null，但建议自己实现该接口，可以参考showcase了解些接口
	public static ClientAioListener aioListener = null;
	
	
//断链后自动连接的，不想自动连接请设为null
	private static ReconnConf reconnConf = new ReconnConf(5000L);
	
//一组连接共用的上下文对象
	public static ClientGroupContext clientGroupContext = new ClientGroupContext(aioClientHandler, aioListener, reconnConf);
	
	public static AioClient aioClient = null;
	public static ClientChannelContext clientChannelContext = null;
	
	
	private static void send() throws Exception {
		HelloPack packet = new HelloPack();
		packet.setBody("hello world".getBytes(HelloPack.CHARSET));
		Aio.send(clientChannelContext, packet);
	}
	
	public static void main(String[] args) throws Exception {
		clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
		aioClient = new AioClient(clientGroupContext);
		clientChannelContext = aioClient.connect(serverNode);
		send();
	}
}

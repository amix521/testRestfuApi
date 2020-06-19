package org.amix.controller;

import java.io.IOException;
import java.util.HashSet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@ServerEndpoint("/{sid}")
@Component
public class WebSocketServer {

	static Logger log = LoggerFactory.getLogger(WebSocketServer.class);

	private static int count = 0;

	private static HashSet<WebSocketServer> websocketSet = new HashSet<WebSocketServer>();

	private Session cstSession;

	private String cstSid = "";

	@OnOpen
	public void onOpen(Session session, @PathParam("sid") String sid) {

		this.cstSession = session;
		this.cstSid = sid;

		websocketSet.add(this);
		count += 1;
		
		log.info("用户<"+this.cstSid+">已连接，当前连接人数【"+count+"】");
	}

	@OnClose
	public void onClose() {
		websocketSet.remove(this);
		count -= 1;
		log.info("用户<"+this.cstSid+">已断开，当前连接人数【"+count+"】");
	}

	@OnError
	public void onError(Throwable error) {
		log.error("发生异常！！！");
		error.printStackTrace();
	}

	/**
	 * 收到客户端消息
	 * 
	 * @param msg
	 */
	@OnMessage
	public void onMessage(String msg) {
		log.info("接收到用户<"+this.cstSid+">消息-->"+msg+"<--end");
		try {
			broadcastMessage(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 给客户端消息
	 * @param msg
	 * @throws IOException
	 */
	public void sendMessage(String msg) throws IOException {
		this.cstSession.getBasicRemote().sendText(msg);
		log.info("向用户<"+this.cstSid+">发送消息-->"+msg+"<--end");
	}

	/**
	 * 群发消息
	 * @param msg
	 * @throws IOException
	 */
	public static void broadcastMessage(String msg) throws IOException {
		log.info("开始广播消息-->"+msg+"-->");
		for (WebSocketServer conn : websocketSet) {
			try {
				conn.sendMessage(msg);
			} catch (Exception e) {
				continue;
			}
		}
		log.info("-->广播结束<--end");
	}

}

package org.amix.controller;



import org.amix.model.StompMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
 
/**
 * 
 * @ClassName: ReciveClientController
 * @Description: 测试浏览器向后台应用推送的消息
 * @author niugang
 * @date 2018年11月6日
 */
@Controller
public class StompServer {
 
	private Logger logger = LoggerFactory.getLogger(StompServer.class);
	/**
	 * 接收客户端发送的消息
	 * @param content    
	 * @date 2018年11月6日
	 * @throws
	 */
	@MessageMapping("receive")
	@SendTo("/topic/what")
	public StompMsg receiveMessage(StompMsg msg){
		logger.info("browser send message content [{}]",msg.getMsg());
		return msg;
	}
}
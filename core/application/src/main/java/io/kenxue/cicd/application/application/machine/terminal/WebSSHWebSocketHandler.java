package io.kenxue.cicd.application.application.machine.terminal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


/**
* WebSSH的WebSocket处理器
*/
@Component
public class WebSSHWebSocketHandler implements WebSocketHandler{

    @Autowired
    private WebSSHService webSSHService;
    private Logger logger = LoggerFactory.getLogger(WebSSHWebSocketHandler.class);

    /**
     * @Description: 用户连接上WebSocket的回调
     * @Param: [webSocketSession]
     * @return: void
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用初始化连接
        webSSHService.initConnection(webSocketSession);
    }

    /**
     * @Description: 收到消息的回调
     * @Param: [webSocketSession, webSocketMessage]
     * @return: void
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        if (webSocketMessage instanceof TextMessage) {
            logger.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY), webSocketMessage);
            //调用service接收消息
            webSSHService.recvHandle(((TextMessage) webSocketMessage).getPayload(), webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {
            //后期上传文件
        } else if (webSocketMessage instanceof PongMessage) {
            //pong信息
        } else {
            logger.error("Unexpected WebSocket message type: {}", webSocketMessage);
        }
    }

    /**
     * @Description: 出现错误的回调
     * @Param: [webSocketSession, throwable]
     * @return: void
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        logger.error("数据传输错误");
    }

    /**
     * @Description: 连接关闭的回调
     * @Param: [webSocketSession, closeStatus]
     * @return: void
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("用户:{}断开webssh连接", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用service关闭连接
        webSSHService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

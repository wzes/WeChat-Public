package com.wzes.wechat.service;

import com.wzes.wechat.dao.TextMessage;
import com.wzes.wechat.util.WechatMessageUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author Create by xuantang
 * @date on 11/1/17
 */

@Service
public class WechatService {

    private static Logger log = LogManager.getLogger(WechatService.class); //日志输出

    private static int MESSAGE = 0;
    public static String processRequest(HttpServletRequest request) {
        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        log.info(map);
        String fromUserName = map.get("FromUserName");
        String toUserName = map.get("ToUserName");
        String msgType = map.get("MsgType");
        String responseMessage = "success";
        if (WechatMessageUtil.MESSAGE_TEXT.equals(msgType)) {// 文本消息
            TextMessage textMessage = new TextMessage();
            textMessage.setMsgType(WechatMessageUtil.MESSAGE_TEXT);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(System.currentTimeMillis());
            // 朱世儒
            if(fromUserName.equals("ovUTEsy50ENpO8ryr54yBQB4WLOY")){
                if(MESSAGE == 0){
                    try {
                        String s = new String(("我可能是喜欢上你了，所以你那天的朋友圈还算数吗？" +
                                "我可以去追你吗？——来自一个程序员的表白").getBytes(), "iso8859-1");
                        textMessage.setContent(s);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    MESSAGE++;
                }else {
                    try {
                        String s = new String(("这是一条可爱的消息～～～～请耐心等待～～～" +
                                "已通知群主～～～～").getBytes(), "iso8859-1");
                        textMessage.setContent(s);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                textMessage.setToUserName("ovUTEsy50ENpO8ryr54yBQB4WLOY");
                responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
            }
            // Derek
            else if(fromUserName.equals("ovUTEs8RJ7wJUPm_9YI0htjWnkbs")){
                try {
                    String s = new String("Go to coding!".getBytes(), "iso8859-1");
                    textMessage.setContent(s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                textMessage.setToUserName("ovUTEs8RJ7wJUPm_9YI0htjWnkbs");
                responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
            }
            // 自己
            else if(fromUserName.equals("ovUTEs2DfbeVY1RaJTzLWZ2mCORU")){
                try {
                    String s = new String(("别给自己发了～～～～～").getBytes(), "iso8859-1");
                    textMessage.setContent(s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // textMessage.setContent("thanks");
                textMessage.setToUserName("ovUTEs2DfbeVY1RaJTzLWZ2mCORU");
                responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
            }else {
                try {
                    String s = new String(("这是一条可爱的消息～～～～请耐心等待～～～" +
                            "已通知群主～～～～").getBytes(), "iso8859-1");
                    textMessage.setContent(s);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // textMessage.setContent("thanks");
                textMessage.setToUserName(fromUserName);
                responseMessage = WechatMessageUtil.textMessageToXml(textMessage);
            }
        }
        log.info(responseMessage);
        return responseMessage;

    }
}

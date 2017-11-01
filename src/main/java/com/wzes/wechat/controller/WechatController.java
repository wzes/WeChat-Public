package com.wzes.wechat.controller;


import com.wzes.wechat.service.WechatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author Create by xuantang
 * @date on 11/1/17
 */
@RestController
public class WechatController {

    @Autowired
    WechatService wechatService;

//    @GetMapping(value = "/wechat")
//    public String getMessage(@RequestParam String signature,
//                             @RequestParam String timestamp,
//                             @RequestParam String nonce,
//                             @RequestParam String echostr){
//
//        String token = "wzescxt";
//        String[] arr = new String[] { timestamp, nonce, token };
//        Arrays.sort(arr);
//
//        StringBuilder sb = new StringBuilder();
//        for (String str : arr){
//            sb.append(str);
//        }
//
//        String s = DigestUtils.sha1Hex(sb.toString());
//
//        if(signature.equals(s)){
//            return echostr;
//        }else{
//            return echostr;
//        }
//    }

    /**
     * 接收来自微信发来的消息
     *
     * @param out
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void wechatServicePost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String responseMessage = wechatService.processRequest(request);
        out.print(responseMessage);
        out.flush();
    }
}

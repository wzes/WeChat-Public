# WeChat-Public

### token 验证
```$xslt
@GetMapping(value = "/wechat")
    public String getMessage(@RequestParam String signature,
                             @RequestParam String timestamp,
                             @RequestParam String nonce,
                             @RequestParam String echostr){

        String token = "wzescxt";
        String[] arr = new String[] { timestamp, nonce, token };
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (String str : arr){
            sb.append(str);
        }
        // 使用commons-codec加密验证
        String s = DigestUtils.sha1Hex(sb.toString());

        if(signature.equals(s)){
            return echostr;
        }else{
            return echostr;
        }
    }
```
### 拦截
```$xslt
 @ResponseBody
    @RequestMapping(value = "/wechat", method = RequestMethod.POST)
    public void wechatServicePost(PrintWriter out, HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String responseMessage = wechatService.processRequest(request);
        out.print(responseMessage);
        out.flush();
    }
```
### 处理请求
```$xslt
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
         
            // 自己
            if(fromUserName.equals("ovUTEs2DfbeVY1RaJTzLWZ2mCORU")){
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
```
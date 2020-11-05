package com.xiaomi.cs.handler;



/**
 * @author l
 * @create 2020-10-28-20:36
 */
//public class SocketHandler  implements IWsMsgHandler {
//
//    @Override
//    public HttpResponse handshake(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
//        return httpResponse;
//    }
//
//    @Override
//    public void onAfterHandshaked(HttpRequest httpRequest, HttpResponse httpResponse, ChannelContext channelContext) throws Exception {
//           System.out.println("连接成功");
//    }
//
//    @Override
//    public Object onBytes(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
//        return null;
//    }
//
//    @Override
//    public Object onClose(WsRequest wsRequest, byte[] bytes, ChannelContext channelContext) throws Exception {
//        System.out.println("关闭连接");
//        return null;
//    }
//
//    @Override
//    public Object onText(WsRequest wsRequest, String s, ChannelContext channelContext) throws Exception {
//        System.out.println("接收文本消息:" + s);
//        return "success";
//    }
//}

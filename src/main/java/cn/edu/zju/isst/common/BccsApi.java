package cn.edu.zju.isst.common;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;



/* @Description 2015-01-08 19:32:30 by Deskid

    1. messageType means Message(0) and Notification(1), default is 0
        Message type need to be handled by the client to be shown in user customized style.
         while Notification is automatically handled by the system.
         here we only use the Notification
    2. IOS and android Message cannot be pushed in same request,so We have both pushAnderoid and pushIOS-like function
    3. We only use the userID to push the message to special user. Since the userID here is unique and device connected.
        so save the channelId in case user  have more than one Device.
    4. //TODO Tag based groupCast, we cannot setTag on the server end for the tagged group users(No such api except use php SDK =__=)
       //TODO so we need to maintain a table saving the map between user and tag info, and set tag on client when Baidu push service is binding.
     */

public class BccsApi {


    protected final static int ANDROID_TYPE = 3;
    protected final static int IOS_TYPE = 4;
    protected final static int MessageType_Notification = 1;

    protected final static int Developer_DeployStatus = 1;
    protected final static int Production_DeployStatus = 2;

    protected final static String apiKey = "xxxxxx";
    protected final static String secretKey = "xxxxx";
    protected final static ChannelKeyPair channekKeyPair = new ChannelKeyPair(apiKey, secretKey);
    protected final static BaiduChannelClient channelClient = new BaiduChannelClient(channekKeyPair);

    public static int pushAndroidBroadcastMessage(String messageKey, String title, String description) {
        PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
        request.setMsgKey(messageKey);

        request.setDeviceType(ANDROID_TYPE);
        request.setMessageType(MessageType_Notification);

        request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");
        return pushBroadcastMessage(request);
    }

    public static int pushAndroidUnitcastMessage(String messageKey, String title, String description, String userId) {
        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setMsgKey(messageKey);
        request.setDeviceType(ANDROID_TYPE);
        request.setMessageType(MessageType_Notification);
        request.setUserId(userId);

        request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");
        return pushUnicastMessage(request);
    }

    public static int pushIOSBroadcastMessage(String messageKey, String title, String description) {
        PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
        request.setMsgKey(messageKey);

        request.setDeviceType(IOS_TYPE);
        request.setMessageType(MessageType_Notification);

        request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");
        return pushBroadcastMessage(request);
    }

    public static int pushIOSUnitcastMessage(String messageKey, String title, String description, String userId) {
        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
        request.setMsgKey(messageKey);
        request.setDeviceType(IOS_TYPE);
        request.setMessageType(MessageType_Notification);
        request.setUserId(userId);

        request.setMessage("{\"title\":\"" + title + "\",\"description\":\"" + description + "\"}");
        return pushUnicastMessage(request);
    }

    public static int pushBroadcastMessage(PushBroadcastMessageRequest request) {
        try {
            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
            return response.getSuccessAmount();
        } catch (ChannelClientException e) {
            e.printStackTrace();
            return 0;
        } catch (ChannelServerException e) {
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return 0;
        }
    }


    public static int pushUnicastMessage(PushUnicastMessageRequest request) {
        try {
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);
            return response.getSuccessAmount();
        } catch (ChannelClientException e) {
            e.printStackTrace();
            return 0;
        } catch (ChannelServerException e) {
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return 0;
        }
    }


//    public static int pushAndroidUnicastMessage() {
//        PushUnicastMessageRequest request = new PushUnicastMessageRequest();
//        request.setDeviceType(3); // device_type => 1: web 2: pc 3:android
//        // 4:ios 5:wp
//        request.setChannelId(4345298425522975173l);
//        request.setUserId("1146537899289570071");
//
//        request.setMessageType(1);
//        request.setMessage("{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}");
//
//        return pushUnicastMessage(request);
//    }

    public static void main(String[] args) {
        // int status = pushAndroidBroadcastMessage("message1", "hello world", "测试消息.");
        // int status = pushAndroidUnicastMessage();
        // System.out.println("status: " + status);
    }
}

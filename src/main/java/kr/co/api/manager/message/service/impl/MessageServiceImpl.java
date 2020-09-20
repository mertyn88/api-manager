package kr.co.api.manager.message.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import kr.co.api.manager.db.chat.model.ChatDetailModel;
import kr.co.api.manager.message.model.MessageSettingModel;
import kr.co.api.manager.message.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final String FIRE_BASE_URL = "https://fcm.googleapis.com/fcm/send";
    private final String DB_INSERT_URL = "http://localhost:8080/chat/insertMyChatDetail";


    public <T> int getPostUrl(T model, String targetUrl, String authorization) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        if(authorization != null){
            conn.setRequestProperty("Authorization", "key=" + authorization);
        }
        conn.setRequestProperty("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        conn.setDoOutput(true);

        String jsonData = new ObjectMapper().writeValueAsString(model);
        OutputStream os = conn.getOutputStream();
        os.write(jsonData.getBytes());
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        log.debug("\nSending 'POST' request to URL : {}", url);
        log.debug("Post parameters : {}", jsonData);
        log.debug("Response Code : {}", responseCode);

        return responseCode;
    }

    @Override
    public Integer sendMessage(MessageSettingModel messageSettingModel) throws IOException {

        int responseCode = getPostUrl(messageSettingModel, FIRE_BASE_URL, "AAAAHjkhH_E:APA91bGuDoU6hDxqlsDwpor5jR0ariodMxZe0mx8m9KISRHFGu9FMLO7G4ajBCkSZrBpVPhQsmR4WYRQmdUPkL7eXwB1MmpWhLNaKHftz4rGZ4xNSXq7uNtxqBt2CNS-7Ixs0CyFcDh0");

        /** 성공 했을 경우 **/
        if(responseCode == HttpStatus.OK.value()){
            // 메세지 저장
            ChatDetailModel chatDetailModel = new ChatDetailModel();
            chatDetailModel.setChatSeq(messageSettingModel.getChatSeq());
            chatDetailModel.setChatSourceMessage(messageSettingModel.getData().getChatSourceMessage());
            chatDetailModel.setChatTargetMessage(messageSettingModel.getData().getChatTargetMessage());

            return getPostUrl(chatDetailModel, DB_INSERT_URL, null);
        } else {
            // 실패시
            return responseCode;
        }

        /*
        결과 json이 필요하면 사용한다.
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // print result
        System.out.println(response.toString());
        */
    }
}

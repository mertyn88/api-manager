package kr.co.api.manager.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class HttpUtil {

    public static final String FIRE_BASE_URL = "https://fcm.googleapis.com/fcm/send";
    public static final String DB_INSERT_URL = "http://localhost:8080/chat/insertMyChatDetail";
    public static final String SEND_MASSAGE_ACCESS_TOKEN = "AAAAHjkhH_E:APA91bGuDoU6hDxqlsDwpor5jR0ariodMxZe0mx8m9KISRHFGu9FMLO7G4ajBCkSZrBpVPhQsmR4WYRQmdUPkL7eXwB1MmpWhLNaKHftz4rGZ4xNSXq7uNtxqBt2CNS-7Ixs0CyFcDh0";


    public static <T> int getPostUrl(T model, String targetUrl, String authorization) throws IOException {
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
}

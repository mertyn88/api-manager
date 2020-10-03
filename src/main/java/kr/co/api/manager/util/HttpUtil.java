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

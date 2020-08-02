package kr.co.api.core.exception.handler;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    // 내부에서 발생한 에러 구분을 위해 meta 정보를 입력 - 입력내용 확인 필요
    @Value("${spring.application.name}")
    private String metaInformation;

    private HttpHeaders getDefaultHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON_UTF8, StandardCharsets.UTF_8);
        httpHeaders.setContentType(mediaType);
        return httpHeaders;
    }


    private static String getErrorMsgReplace(String description, String msg, String msg2) {
        try {
            String retMsg;

            if (StringUtils.isBlank(msg)) {
                retMsg = description;
            } else {
                retMsg = description.replace("%d", msg);
            }

            if (!StringUtils.isBlank(msg2)) {
                retMsg = retMsg.replace("%s", msg2);
            }

            return retMsg;
        } catch(Exception e) {
            return StringUtils.isBlank(msg) ? description : "(" + msg + ")" + description;
        }
    }
}

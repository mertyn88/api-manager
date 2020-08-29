package kr.co.api.core.properties;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "elastic", ignoreInvalidFields = true)
public class ElasticProperties {

    private String host;
    private int port;
    private String username;
    private String password;
    private int size;
    private int timeOut;
    private String aggsFields;
    private String indices;
}

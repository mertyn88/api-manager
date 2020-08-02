package kr.co.api.manager.auth.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.auth.model.AccessTokenModel;
import kr.co.api.manager.db.test.model.Test;
import kr.co.api.manager.db.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/token/check")
    @ApiOperation(value = "Auth 토큰 테스트", response = Integer.class)
    public DataResponse insertTest(@Valid @RequestBody AccessTokenModel accessTokenModel) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", "wnY5ZPhnO37T-W57Ypy23fMhlcY8QxqbvPqtBwo9dVoAAAFzlT8mwQ");

        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getForEntity(BASE_URL + "/{name}/{country}", Employee.class, params);
        ResponseEntity responseEntity = restTemplate
                .getForEntity("http://localhost:8080/oauth/check_token", Map.class, params);

        System.out.println(responseEntity.toString());

        return DataResponse.builder().data(responseEntity).build();
    }
}

package com.example.securityoauth2.token;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 여러 앱을 지원하려면 callback, refreshToken 매서드를 새로 만들어서 사용
 */
@RestController
@Log4j2
@RequestMapping("/oauth2")
@SessionAttributes("tokenDto")
public class Oauth2Controller {

    @Autowired
    private Gson gson;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Oauth_TokenRepository repository;


    @GetMapping("/callback")
    public void showEmployees(String code, Model model, HttpServletResponse resp) throws IOException {
        String credentials = "lowlow:admin"; // 클라이언트 ID, secret
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        // Header 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Authorization", "Basic " + encodedCredentials);

        // Param 설정
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", "http://localhost:8082/oauth2/callback");

        // Header와 Param을 HttpEntity에 담기
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // 토큰데이터 발급받은 후 저장
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8082/oauth/token", request, String.class);
        Oauth_TokenDto tokenDto = gson.fromJson(response.getBody(), Oauth_TokenDto.class);
        model.addAttribute("tokenDto", tokenDto);

        resp.sendRedirect("/oauth2/token/save");
    }


    /**
     * 토큰값 저장
     */
    @GetMapping(value = "/token/save")
    public Oauth_Token saveToken(@ModelAttribute("tokenDto") Oauth_TokenDto tokenDto) {

        // 로그인 정보 검색
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();

        // 토큰 DB 등록
        Oauth_Token token = Oauth_Token.builder()
                .token(tokenDto.getAccess_token())
                .refresh_token(tokenDto.getRefresh_token())
                .username(username)
                .build();

        Oauth_Token savedToken = repository.save(token);
        return savedToken;
    }
}
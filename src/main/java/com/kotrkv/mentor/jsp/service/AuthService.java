package com.kotrkv.mentor.jsp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private OAuth20Service service;

    public String authorizationUrl() {
        final String clientId = "144102236584-tb6qf5284uvq4t8b2oqbeec8k0lenq6b.apps.googleusercontent.com";
        final String clientSecret = "t6YxWs1rEq5nhxHjFrtXKmKf";

        service = new ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .defaultScope("profile")
                .callback("http://localhost:8080/auth/google")
                .build(GoogleApi20.instance());
        return service.getAuthorizationUrl();
    }

    private Optional<OAuth2AccessToken> accessToken(String code) {
        try {
            return Optional.of(service.getAccessToken(code));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String responseBody(String code) {
        OAuthRequest request = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v3/userinfo");
        service.signRequest(accessToken(code).get(), request);
        try {
            Response response = service.execute(request);
            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String authUserName(String code) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = null;
        try {
            node = mapper.readTree(responseBody(code));
            return node.path("name").asText();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

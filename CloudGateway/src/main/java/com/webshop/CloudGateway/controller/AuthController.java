package com.webshop.CloudGateway.controller;

import com.webshop.CloudGateway.dto.AuthResponse;
import com.webshop.CloudGateway.dto.AuthenticationResponse;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    Base64.Decoder decoder = Base64.getUrlDecoder();
    @GetMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@AuthenticationPrincipal  OAuth2User oidcUser, Model model,
                                                        @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client){

        AuthenticationResponse authenticationResponse = AuthenticationResponse.builder()
                .userId((String) oidcUser.getAttributes().get("email"))
                .accessToken(client.getAccessToken().getTokenValue())
                .refreshToken(client.getRefreshToken().getTokenValue())
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .authorityList(oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .build();

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);
    }

    @GetMapping("/oidc-principal")
    public ResponseEntity<AuthResponse> getOidcUserPrincipal(
            @AuthenticationPrincipal OAuth2User oidcUser, @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client, Model model)
    {

        String accesToken = client.getAccessToken().getTokenValue();
        String[] chunks = accesToken.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        AuthResponse authResponse = AuthResponse.builder()
                .userId(oidcUser.getName())
                .accessTokenHeader(header)
                .accessTokenPayLoad(payload)
                .accessToken(client.getAccessToken().getTokenValue())
                .sub(oidcUser.getAttributes().toString())
                .expiresAt(client.getAccessToken().getExpiresAt().getEpochSecond())
                .authorityList(oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .refreshToken(client.getRefreshToken().getTokenValue())
                .build();
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}

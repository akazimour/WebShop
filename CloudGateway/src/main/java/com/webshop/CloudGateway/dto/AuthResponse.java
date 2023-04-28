package com.webshop.CloudGateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String userId;

    private String accessTokenHeader;
    private String accessTokenPayLoad;
    private String accessToken;
    private String sub;
    private String refreshToken;
    private Long expiresAt;
    private Collection<String> authorityList;
}

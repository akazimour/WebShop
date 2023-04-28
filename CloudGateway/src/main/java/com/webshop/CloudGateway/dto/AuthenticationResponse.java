package com.webshop.CloudGateway.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationResponse implements Serializable {

    private String userId;
    private String accessToken;
    private String refreshToken;
    private Long expiresAt;
    private Collection<String> authorityList;
}

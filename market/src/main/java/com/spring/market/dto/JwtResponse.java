package com.spring.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Токен отправляется клиенту в виде json
@Data
@AllArgsConstructor
public class JwtResponse {
    private String token;
}

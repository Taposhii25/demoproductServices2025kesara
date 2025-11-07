package com.scaler.demoproductservices2025kesara.util;

import com.scaler.demoproductservices2025kesara.dtos.Userdto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthUtil {
    private static RestTemplate restTemplate;

    public AuthUtil(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public static Userdto validateToken(String token) {
        //logic to validate token
        //make a http call to user-service
        ResponseEntity<Userdto> responseEntity = restTemplate.getForObject(
                "http://localhost:8080/users/validate/token",
                ResponseEntity.class
        );
        if(responseEntity != null){
            return responseEntity.getBody();
        }
        return null;
    }
}

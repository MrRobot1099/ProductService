package com.project.productservice.commons;

import com.project.productservice.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {

    RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // TODO: Implement the user authentication methods here
    public UserDTO validateToken(String token) {
        ResponseEntity<UserDTO> response =  restTemplate.postForEntity("http://localhost:8080/user/validate/" + token,null, UserDTO.class);
        if (response.getBody() == null) {
            //Token is invalid.
            return null;
        }
        return response.getBody();
    }
}

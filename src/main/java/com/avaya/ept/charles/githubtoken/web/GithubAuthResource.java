package com.avaya.ept.charles.githubtoken.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class GithubAuthResource {

    private Logger logger = LoggerFactory.getLogger(GithubAuthResource.class);

    @Value("${client_id}")
    private String clientId;

    @Value("${client_secret}")
    private String clientSecret;

    private static String ACCESS_URL;

    @Resource
    private RestTemplate restTemplate;

    public GithubAuthResource() {

    }

    @PostMapping("/token")
    public ResponseEntity<Object> retrieveTokenByCode(@RequestBody String code) {
        logger.info("Request token using code: {}", code);

        if(ACCESS_URL == null){
            ACCESS_URL = "https://github.com/login/oauth/access_token?" +
                    "client_id=" + clientId +
                    "&client_secret=" + clientSecret + "&code=";
        }
        try{
            Object res = this.restTemplate.postForObject(ACCESS_URL + code, null, Object.class);
            return ResponseEntity.ok(res);
        } catch(Exception e){
            logger.error("Request has error", e);
            return ResponseEntity.ok(null);
        }
    }
}

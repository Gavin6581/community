package com.gavin.community.controller;

import com.gavin.community.dto.AccessTokenDto;
import com.gavin.community.dto.GithubUser;
import com.gavin.community.provider.GithubProvicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvicer githubProvicer;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id("cbb52752643354f86f78");
        accessTokenDto.setClient_secret("6bc05f190e027ea6f1a2fd2df89a510da0a49025");
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDto.setState(state);
        String accessToken = githubProvicer.getAccessToken(accessTokenDto);
        GithubUser user = githubProvicer.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}

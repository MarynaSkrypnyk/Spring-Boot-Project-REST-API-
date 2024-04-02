package org.example.chinesfoodcafe.controller;

import org.example.chinesfoodcafe.service.LogOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LogOutController {

    @Autowired
    private LogOutService catImageService;
    @GetMapping("/logOut")
    public String logout(@RequestHeader("x-api-key") String apiKey) throws IOException, InterruptedException {
        return catImageService.logOut(apiKey);

    }
}

package com.openapi.openapigendemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test-app")
public class TestController {

    @GetMapping("/status")
    public String testApp(){
        return "application is deployed successfully !!";
    }
}

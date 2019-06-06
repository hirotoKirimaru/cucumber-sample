package com.example.demo.restapi;

import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class defultRestController {

    @RequestMapping("/")
    public String hogeApi() {
        return "hogehoge";
    }
}

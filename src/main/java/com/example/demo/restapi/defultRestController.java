package com.example.demo.restapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
public class defultRestController {

    @RequestMapping("/")
    public String hogeApi(@Validated Param param) {
        System.out.println(param.getPiyo());
        return "hogehoge";
    }

    @Data
    @AllArgsConstructor
    private static class Param {
        @NotNull
        private String piyo;
    }
}

package com.devcrutch.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        int i = 0/ (1-1);
        return "Hello World";
    }
}

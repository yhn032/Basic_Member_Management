package com.boot.member.controller;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "home";
    }
}

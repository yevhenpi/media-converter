package ua.pidopryhora.mediaconverter.auth.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
public class PublicController {

    @GetMapping("/public")
    public String getResponse(){

        return "Public access";
    }
}

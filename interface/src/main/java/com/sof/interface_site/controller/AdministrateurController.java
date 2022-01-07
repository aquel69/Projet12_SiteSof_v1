package com.sof.interface_site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdministrateurController {

    @RequestMapping(value = "/Newsletter", method = RequestMethod.GET)
    public String envoiEmail(){
        System.out.println("Newsletter");
        return "Newsletter";
    }

}

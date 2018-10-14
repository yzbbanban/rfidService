package com.yzb.rfid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brander on 2018/10/14
 */
@Controller
public class WebController {

    @RequestMapping("/uType")
    public String userType() {
        return "userType";
    }

    @RequestMapping("/uVersion")
    public String userVersion() {
        return "updateVersion";
    }
}

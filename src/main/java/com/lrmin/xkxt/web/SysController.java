package com.lrmin.xkxt.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author lirongmin
 * @date 2020/5/22 0022
 */
@Controller
public class SysController {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}

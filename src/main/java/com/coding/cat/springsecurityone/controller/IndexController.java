package com.coding.cat.springsecurityone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
  @GetMapping({"","/"})
  public String Index(){
    return "index";
  }
}

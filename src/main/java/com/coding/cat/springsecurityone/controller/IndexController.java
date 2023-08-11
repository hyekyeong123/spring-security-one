package com.coding.cat.springsecurityone.controller;

import com.coding.cat.springsecurityone.model.User;
import com.coding.cat.springsecurityone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

  @Autowired private UserRepository userRepository;
  @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;

  @GetMapping({"","/"})
  public String index(){
    return "index";
  }

  @GetMapping("/user")
  @ResponseBody
  public String user(){
    return "user";
  }

  @GetMapping("/admin")
  @ResponseBody
  public String admin(){
    return "admin";
  }

  @GetMapping("/manager")
  @ResponseBody
  public String manager(){
    return "manager";
  }

  // SecurityConfig 파일 생성 하기 전까지는 스프링 시큐리티가 해당 주소를 낚아챔
  @GetMapping("/login")
  public String loginView(){
    return "login";
  }

  @GetMapping("/join")
  public String joinView(){
    return "join";
  }

  @PostMapping("/joinAction")
  public String joinAction(User user){
    System.out.println(user);
    user.setRole("USER");

    // 비밀번호 암호화
    String rawPassword = user.getPassword();
    String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
    user.setPassword(encodedPassword);

    // 회원가입
    userRepository.save(user);
    return "redirect:/login";
  }
}

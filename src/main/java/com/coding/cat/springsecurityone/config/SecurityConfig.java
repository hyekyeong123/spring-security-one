package com.coding.cat.springsecurityone.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록
public class SecurityConfig {

  // 해당 메서드의 리턴되는 메서드를 IOC로 등록해준다.
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable)

      .authorizeHttpRequests((authorizeRequests) ->
        authorizeRequests
          .requestMatchers("/user/**").authenticated()
          .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
          .requestMatchers("/admin/**").hasRole("ADMIN")
          .anyRequest().permitAll()
      )

      // 폼 로그인 방식(인증이 안된 경우 자동으로 login 페이지로 리다이렉트함)
      .formLogin(login ->
        login
        .loginPage("/login")
        .loginProcessingUrl("/loginAction")
        .defaultSuccessUrl("/")
        // .usernameParameter("username2")
      );
    return http.build();
  }
}

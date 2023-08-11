
package com.coding.cat.springsecurityone.config.auth;
import com.coding.cat.springsecurityone.model.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;

public class PrincipalDetails implements UserDetails {

  private User user;

  public PrincipalDetails(User user){
    this.user = user;
  }

   // 해당 User의 권한을 리턴
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collectors = new ArrayList<>();
    collectors.add(()->{return user.getRole();});
    return collectors;
  }

  @Override
  public String getPassword() {
    return user.getPassword();
  }

  @Override
  public String getUsername() {
    return user.getUsername();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
/*
 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
 로그인 진행이 완료가 되면 시큐리티 session을 만들어준다.(spring security의 인메모리 세션저장소인 SecurityContextHolder)
 오브젝트 => Authentication 타입의 객체
 Authentication 안에 User 정보가 있어야함
 User 오브젝트 타입은 UserDetails 타입 객체

 즉 Security Session 안에 Authenticaiton 안에 UserDetail가 있음
*/
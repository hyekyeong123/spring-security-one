package com.coding.cat.springsecurityone.config.auth;

import com.coding.cat.springsecurityone.model.User;
import com.coding.cat.springsecurityone.repository.UserRepository;
import javax.swing.Spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// 시큐리티 설정에서 loginProcessingUrl("/login")으로 설정하였기때문에
// login 요청이 오면 자동으로 UserDetailService 타입으로 IOC 되어 있는 loadUserByUsername 함수가 실행

@Service
public class PrincipalDetailsService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // 실제 유저가 존재하고 있는지 유효성 검사
    User userEntity = userRepository.findByUsername(username);
    if(userEntity != null){
      return new PrincipalDetails(userEntity);
      /*
      1. 방금 받은 username과 password를 조합해서 UsernamePasswordAuthenticationToken 인스턴스를 만듭니다.
      2. 이 토큰은 검증을 위해 AuthenticatoinManager의 인스턴스로 전달
      3. AuthenticationManager는 인증에 성공하면 Authenticaiton 인스턴스를 리턴
      4. 이 Authenticaiton을 SecurityContenxtHodler.getContext().setAuthentication()에 set
      */
    }
    return null;
  }
}

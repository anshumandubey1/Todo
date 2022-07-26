package com.anshuman.todo.security;

import com.anshuman.todo.controller.TaskController;
import com.anshuman.todo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomDetailsService implements OAuth2UserService {

  @Autowired
  private UserRepository userRepository;

  private final Logger logger = LoggerFactory.getLogger(CustomDetailsService.class);


  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    logger.info(userRequest.getAccessToken().toString());
    logger.info(userRequest.getAdditionalParameters().toString());
    logger.info(userRequest.getClientRegistration().toString());
//    var user = userRepository.findByEmail(username).orElseThrow();
//    return new User(user.getEmail(), user.getPassword(), user.getGrantedAuthoritiesList());
    return null;
  }
}

package com.example.securityoauth2.config;

import com.example.securityoauth2.owner.OwnerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private OwnerProvider ownerProvider;

    @Override
    //하위 정적자원들에 대해 보안구성에서 제외한다.
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/favicon.ico");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations()); //기본 설정된 모든 정적파일들
    }

    @Override
    //요청의 대한 설정
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll() // 누구나 요청가능
                .mvcMatchers(HttpMethod.GET, "/api/**").anonymous() // 누구나 요청가능
                .antMatchers("/test/test1").hasAnyRole("ADMIN") // ADMIN 만 요청가능
                .antMatchers("/test/test2").hasAnyRole("USER")  // USER 만 요청가능
                .anyRequest().authenticated() // 나머지는 인증된 사용자만 요청가능
                .and().formLogin().permitAll() // 로그인 인증은 누구나 요청가능
                .and().logout().permitAll(); // 로그아웃 인증은 누구나 요청가능

        http.csrf().disable(); // csrf 미사용
    }

    @Override
    // Owner 대한 설정
    public void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
        authenticationMgr.authenticationProvider(ownerProvider);
    }
}
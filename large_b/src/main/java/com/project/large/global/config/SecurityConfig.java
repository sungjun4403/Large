package com.project.large.global.config;


import com.project.large.jwt.*;
import com.project.large.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String WEB_URL = "https://localhost.com";

    private final CorsFilter corsFilter;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    @Bean
    public BCryptPasswordEncoder encodePwd() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .addFilter(corsFilter);

        http.authorizeRequests()
                .antMatchers()
                .authenticated()
                .anyRequest().permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .and().headers().frameOptions().sameOrigin();

        http.addFilterBefore(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtExceptionFilter(), JwtRequestFilter.class);
    }

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
        return jwtExceptionFilter;
    }

    @Bean
    public JwtRequestFilter jwtRequestFilter() {
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(memberRepository, jwtService);
        return jwtRequestFilter;
    }
}


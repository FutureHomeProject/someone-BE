package com.example.someonebe.config;

import com.example.someonebe.jwt.JwtAuthFilter;
import com.example.someonebe.jwt.JwtUtil;
import com.example.someonebe.security.CustomAccessDeniedHandler;
import com.example.someonebe.security.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration                                       //스프링의 구성을 나타내는 어노테이션
@RequiredArgsConstructor                             //Lombok라이브러리를 사용하여 생성자를 자동생성, final필드를 초기화한다.
@EnableWebSecurity                                   //웹보안 활성화
@EnableGlobalMethodSecurity(securedEnabled = true)   //메서드 수준의 보안을 활성화, @Secured 어노테이션을 사용할수 있게 한다.
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    //빈 생성 메서드
    @Bean
    public PasswordEncoder passwordEncoder() {      //'BCryptPasswordEncoder'를 반환하는 메서드, 이 객체는 password의 암호화를 처리

        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {  //정적 리소스에 대한 보안처리를 무시하는 설정을 하는 메서드
        return (web) -> web.ignoring()
//                .requestMatchers(PathRequest.toH2Console())   //h2 database
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/houses/{boardid}").permitAll()
                .antMatchers(HttpMethod.GET, "/houses").permitAll()
                .antMatchers(HttpMethod.GET, "/products/{productid}").permitAll()
                .antMatchers(HttpMethod.GET, "/products").permitAll()
            .antMatchers("/test/**").permitAll()
                .antMatchers("/users/signup", "/users/login","/users/confirm-email", "/users/confirm-nickname").permitAll()
                .antMatchers(HttpMethod.GET, "/products/{productid}/reviews/write").permitAll()
                .anyRequest().authenticated();

        http.cors();
        http.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
        // 내장 기본 로그인 사용
//        http.formLogin();
        // Custom 로그인 페이지 사용
//        http.formLogin().loginPage("/api/user/login-page").permitAll();
        // 접근 제한 페이지 이동 설정
//        http.exceptionHandling().accessDeniedPage("/api/user/forbidden");

        // 401 Error 처리, Authorization 즉, 인증과정에서 실패할 시 처리
        http.exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint);

        // 403 Error 처리, 인증과는 별개로 추가적인 권한이 충족되지 않는 경우
        http.exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);

        return http.build();
    }

    // cors 설정
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:3000");
        configuration.addExposedHeader(JwtUtil.AUTHORIZATION_HEADER);
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        configuration.validateAllowCredentials();

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

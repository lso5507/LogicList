package swlee.logiclist.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/** Spring Security 설정 클래스
 * Override-> Bean등록 형식으로 변경됨. WebSecurityConfigurerAdapter(Deprecated)
 */
@Configuration
@EnableWebSecurity
@Slf4j
public class SpringSecurityConfig {
    @Autowired
    private MyAuthenticationProvider myAuthenticationProvider;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http
                .authenticationProvider(myAuthenticationProvider)
                .authorizeRequests()
                /**
                 * image-upload,image - AWS 테스트용
                 */
                    .antMatchers("/view/test","/view/login*","/view/signup","/view/todo_data","/view/todo","/image-**","/image","/view/main","/css/**","/js/**","/plugin/**","/fonts/**")// /view/main 인증 없이 접근가능
                    .permitAll()
                    .anyRequest().authenticated() // 그 외 요청은 권한 필요
                .and()
                    .formLogin()
                    .loginPage("/view/login")
                    .usernameParameter("username") //아이디
                    .passwordParameter("password") //패스워드
                    .loginProcessingUrl("/view/login")
                    .defaultSuccessUrl("/view/main")
                    .failureUrl("/status/404")
                    .permitAll()
                .successHandler( // 로그인 성공 후 핸들러
                        new AuthenticationSuccessHandler() { // 익명 객체 사용
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                log.info("authentication.getName()=={},authentication.getCredentials()=={},authentication.getPrincipal()=={}", authentication.getName(),authentication.getCredentials(),authentication.getPrincipal());
                                response.sendRedirect("/view/main");
                            }
                        })
                .failureHandler( // 로그인 실패 후 핸들러
                        new AuthenticationFailureHandler() { // 익명 객체 사용
                            @Override
                            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                                log.info("exception: {}", exception.getMessage());
                                response.sendRedirect("/view/login?error=failed");
                            }
                        })
                .and()
                    .logout().logoutSuccessUrl("/view/main").logoutUrl("/view/logout")
                .and()
                    .csrf().disable();
      /*          .authenticationManager(new AuthenticationManager() {
                    @Override
                    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                        log.info("authenticate::{}",authentication.getPrincipal());
                        return authentication;
                    }
                });*/


        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**");
//    }
@Bean
public static PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
}

}
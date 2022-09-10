package swlee.logiclist.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;


/** Spring Security 설정 클래스
 * Override-> Bean등록 형식으로 변경됨. WebSecurityConfigurerAdapter(Deprecated)
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig  {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http
                .authorizeRequests()
                    .antMatchers("/view/main","/css/**","/scripts/**","/plugin/**","/fonts/**")// /view/main 인증 없이 접근가능
                    .permitAll()
                    .anyRequest().authenticated() // 모든 URL 인증 필요
                .and()
                    .formLogin()
                    .loginPage("/view/login")
                    .loginProcessingUrl("/loginProc")
                    .usernameParameter("id") //아이디
                    .passwordParameter("pw") //패스워드
                    .defaultSuccessUrl("/view/main", true)
                    .permitAll()
                .and()
                    .logout();
        return http.build();
    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/static/js/**","/static/css/**","/static/img/**","/static/frontend/**");
//    }

}
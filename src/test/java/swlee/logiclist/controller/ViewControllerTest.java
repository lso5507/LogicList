package swlee.logiclist.controller;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import swlee.logiclist.security.SpringSecurityConfig;

import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Import(SpringSecurityConfig.class)

public class ViewControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @DisplayName("RumtimeExceptionTest")
//    @Test
//    public void testException() throws Exception {
//        mockMvc.perform(get("/view/test"))
//                .andExpect(
//                        // assert로 예외를 검사하는 람다 사용
//                        (rslt) -> assertTrue(rslt.getResolvedException().getClass().isAssignableFrom(RuntimeException.class))
//
//                );
//    }
    @DisplayName("로그인 성공테스트")
    @Test
    public void testLogin() throws Exception {
        // 로그인 요청 전송
        mockMvc.perform(formLogin("/view/login").user("root").password("1111"))
                .andExpect(authenticated().withUsername("root"))
                .andExpect(authenticated().withAuthorities(
                        Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/main"))
                .andReturn();

        // 로그인 요청이 성공한 경우, 반환된 Authentication 객체에서 인증된 사용자의 정보를 가져올 수 있음
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            assertThat(authentication.getName()).isEqualTo("root");
            assertThat(authentication.getAuthorities()).isIn(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @DisplayName("로그인 실패테스트_패스워드불일치")
    @Test
    public void testLogin_failed() throws Exception {
        // 로그인 요청 전송
        MvcResult result = mockMvc.perform(post("/view/login")
                        .param("username","root")
                        .param("password","1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/login?error=failed"))
                .andReturn();

    }

}

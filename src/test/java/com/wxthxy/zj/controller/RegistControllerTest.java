package com.wxthxy.zj.controller;

import com.wxthxy.zj.service.LoginService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegistControllerTest {

    private MockMvc mockMvc;
    private LoginService loginService;

    @Before
    public void setUp() {
        RegistController controller = new RegistController();
        loginService = Mockito.mock(LoginService.class);
        ReflectionTestUtils.setField(controller, "service", loginService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldRejectWhenPasswordTooShort() throws Exception {
        mockMvc.perform(post("/zj/regist")
                        .param("username", "tom")
                        .param("password", "a@12345")
                        .param("userid", "1001")
                        .param("identity", "student"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("密码长度不能低于8位")));

        verify(loginService, never()).doRegist(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void shouldRejectWhenPasswordHasNoSpecialCharacter() throws Exception {
        mockMvc.perform(post("/zj/regist")
                        .param("username", "tom")
                        .param("password", "abc12345")
                        .param("userid", "1001")
                        .param("identity", "student"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("密码需要包含特殊字符")));

        verify(loginService, never()).doRegist(anyString(), anyString(), anyString(), anyString());
    }

    @Test
    public void shouldCallServiceWhenPasswordIsValid() throws Exception {
        when(loginService.doRegist("tom", "abc@1234", "1001", "student")).thenReturn("注册成功");

        mockMvc.perform(post("/zj/regist")
                        .param("username", "tom")
                        .param("password", "abc@1234")
                        .param("userid", "1001")
                        .param("identity", "student"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("注册成功")));

        verify(loginService, times(1)).doRegist("tom", "abc@1234", "1001", "student");
    }
}

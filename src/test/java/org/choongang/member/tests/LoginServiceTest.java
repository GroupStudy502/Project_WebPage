package org.choongang.member.tests;

import com.github.javafaker.Faker;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.choongang.global.exceptions.BadRequestException;
import org.choongang.member.controllers.RequestJoin;
import org.choongang.member.controllers.RequestLogin;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.LoginService;
import org.choongang.member.services.MemberServiceProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.only;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@DisplayName("로그인 기능 테스트")
public class LoginServiceTest {

    private LoginService loginService;
    private Faker faker;
    private RequestJoin form;
    private SqlSession dbSession;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @BeforeEach
    void init() {
        loginService = MemberServiceProvider.getInstance().loginService();
        JoinService joinService = MemberServiceProvider.getInstance().joinService();
        faker = new Faker(Locale.ENGLISH);
        dbSession = MemberServiceProvider.getInstance().getSession();

        form = new RequestJoin();
        form.setEmail(System.currentTimeMillis() + faker.internet().emailAddress());
        form.setPassword(faker.regexify("\\w{8}").toLowerCase());
        form.setConfirmPassword(form.getPassword());
        form.setUserName(faker.name().fullName());
        form.setTermsAgree(true);

        joinService.process(form);

        given(request.getSession()).willReturn(session);
    }

    RequestLogin getData() {
        RequestLogin lForm = new RequestLogin();
        lForm.setEmail(form.getEmail());
        lForm.setPassword(form.getPassword());
        return lForm;
    }

    @Test
    @DisplayName("로그인 성공시 예외가 발생하지 않음")
    void successTest() {
        assertDoesNotThrow(() -> {
            loginService.process(getData());
        });

        // 로그인 처리 완료시 HttpSession - setAttribute 메서드가 호출 됨
        then(session).should(only()).setAttribute(any(), any());
    }

    @Test
    @DisplayName("필수 입력 항목(이메일, 비밀번호) 검증, 검증 실패시 BadRequestException 발생")
    void requiredFieldTest() {
        assertAll(
                () -> requiredEachFieldTest("email", false, "이메일"),
                () -> requiredEachFieldTest("email", true, "이메일"),
                () -> requiredEachFieldTest("password", false, "비밀번호"),
                () -> requiredEachFieldTest("password", true, "비밀번호")
        );
    }

    void requiredEachFieldTest(String name, boolean isNull, String message) {

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            RequestLogin form = getData();
            if (name.equals("password")) {
                form.setPassword(isNull ? null : "   ");
            } else { // 이메일
                form.setEmail(isNull ? null : "   ");
            }

            loginService.process(form);
        }, name + " 테스트");

        String msg = thrown.getMessage();
        assertTrue(msg.contains(message), name + ", 키워드:" + message + " 테스트");
    }

    @Test
    @DisplayName("이메일로 회원이 조회되지 않는 경우 BadRequestException 발생")
    void memberExistTest() {
        // 잘못된 이메일 설정
        RequestLogin lForm = getData();
        lForm.setEmail("invalid" + lForm.getEmail());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            loginService.process(lForm);
        });

        String message = thrown.getMessage();
        assertTrue(message.contains("이메일 또는 비밀번호"));
    }

    @Test
    @DisplayName("비밀번호 검증 실패시 BadRequestException 발생")
    void passwordCheckTest() {
        // 잘못된 비밀번호 설정
        RequestLogin lForm = getData();
        lForm.setPassword("invalid" + lForm.getPassword());

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            loginService.process(lForm);
        });

        String message = thrown.getMessage();
        assertTrue(message.contains("이메일 또는 비밀번호"));
    }

    @AfterEach
    void destroy() {
        // dbSession.rollback();
    }
}

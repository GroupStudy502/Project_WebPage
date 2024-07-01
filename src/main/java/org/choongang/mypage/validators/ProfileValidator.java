package org.choongang.mypage.validators;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Component;
import org.choongang.global.exceptions.AlertException;
import org.choongang.global.exceptions.UnAuthorizedException;
import org.choongang.global.validators.RequiredValidator;
import org.choongang.global.validators.Validator;
import org.choongang.member.MemberUtil;
import org.choongang.mypage.controllers.RequestProfile;

@Component
@RequiredArgsConstructor
public class ProfileValidator implements Validator<RequestProfile>, RequiredValidator {

    private final MemberUtil memberUtil;

    @Override
    public void check(RequestProfile form) {

        // 로그인 상태인지 여부 체크
        checkTrue(memberUtil.isLogin(), new UnAuthorizedException());

        /* 필수 항목 검증 S */
        String password = form.getPassword();
        String confirmPassword = form.getConfirmPassword();
        String userName = form.getUserName();

        int status = HttpServletResponse.SC_BAD_REQUEST;

        if (password != null && !password.isBlank()) {
            checkRequired(confirmPassword, new AlertException("비밀번호를 확인하세요.", status));

            /* 비밀번호 자리수(8자리 이상) 체크  */
            checkTrue(password.length() >= 8, new AlertException("비밀번호는 8자리 이상 입력하세요.", status));

            /* 비밀번호와 비밀번호 확인 여부 체크 */
            checkTrue(password.equals(confirmPassword), new AlertException("비밀번호가 일치하지 않습니다.", status));
        }

        checkRequired(userName, new AlertException("회원명을 입력하세요.", status));
        /* 필수 항목 검증 E */





    }
}

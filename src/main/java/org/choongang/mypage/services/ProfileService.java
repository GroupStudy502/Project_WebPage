package org.choongang.mypage.services;

import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Service;
import org.choongang.member.MemberUtil;
import org.choongang.mypage.controllers.RequestProfile;
import org.choongang.member.entities.Member;
import org.choongang.member.mappers.MemberMapper;
import org.choongang.mypage.validators.ProfileValidator;
import org.mindrot.jbcrypt.BCrypt;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final MemberUtil memberUtil;
    private final ProfileValidator validator;
    private final MemberMapper mapper;

    public void process(RequestProfile form) {
        validator.check(form);

        Member member = memberUtil.getMember(); // 로그인한 회원 정보

        // 비밀번호 변경 S
        String password = form.getPassword();
        if (password != null && !password.isBlank()) {
            String hash = BCrypt.hashpw(password, BCrypt.gensalt(12));
            member.setPassword(hash);
        }
        // 비밀번호 변경 E

        // 회원 이름 변경
        member.setUserName(form.getUserName());
        mapper.modify(member);
    }
}

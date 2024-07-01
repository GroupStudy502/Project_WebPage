package org.choongang.mypage.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.PostMapping;
import org.choongang.global.config.annotations.RequestMapping;
import org.choongang.mypage.services.ProfileService;

import java.util.List;

@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final ProfileService profileService;
    private final HttpServletRequest request;


    @GetMapping
    public String info(HttpServletRequest request) {

        request.setAttribute("addCss", List.of("mypage"));
        return "mypage/info";
    }

    @PostMapping
    public String infoPs(RequestProfile form) {

        profileService.process(form);

        String script = "parent.location.reload();"; // 회원정보 수정 후 페이지 새로고침
        request.setAttribute("script", script);

        return "commons/execute_script";
    }
}
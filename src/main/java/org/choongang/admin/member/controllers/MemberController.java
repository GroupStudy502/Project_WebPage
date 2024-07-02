package org.choongang.admin.member.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.admin.service.MemberInfoService;
import org.choongang.global.ListData;
import org.choongang.global.config.annotations.Controller;
import org.choongang.global.config.annotations.GetMapping;
import org.choongang.global.config.annotations.RequestMapping;
import org.choongang.member.entities.Member;
import org.choongang.member.services.LoginService;

import java.util.List;

@Controller
@RequestMapping("/admin/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberInfoService infoService;

    @GetMapping("/list")
    public String index(MemberSearch search, HttpServletRequest request) {

        ListData<Member> data = infoService.getList(search);
        request.setAttribute("items", data.getItems());
        request.setAttribute("pagination", data.getPagination());
        request.setAttribute("addCss", List.of("admin/list"));

        return "admin/member/list";
    }

    @GetMapping("/total")
    public String total(HttpServletRequest request) {

        // 오늘 로그인해서 접속 방문한 사람 통계하기.
        return null;
    }

    @GetMapping("/edit")
    public String edit(HttpServletRequest request) {
        request.setAttribute("addCss", List.of("admin/edit"));

        return "admin/member/edit";
    }

}

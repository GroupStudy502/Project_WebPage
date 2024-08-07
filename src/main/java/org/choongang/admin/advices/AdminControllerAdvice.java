package org.choongang.admin.advices;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.choongang.global.Interceptor;
import org.choongang.global.config.annotations.ControllerAdvice;
import org.choongang.global.config.annotations.ModelAttribute;
import org.choongang.global.exceptions.UnAuthorizedException;
import org.choongang.member.MemberUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@ControllerAdvice("org.choongang.admin")
public class AdminControllerAdvice implements Interceptor {

    private final MemberUtil memberUtil;
    private final HttpServletRequest request;

    //관리자인지 먼저 확인 아닐 경우 Exception
    @Override
    public boolean preHandle() {
        if (!memberUtil.isAdmin()) {
            //throw new UnAuthorizedException(); 개발 완료 후 주석 해제
        }

        return true;
    }

    /**
     * 서브 메뉴 전체
     *
     * @return
     */
    @ModelAttribute
    public Map<String, List<String[]>> subMenusAll() {
        Map<String, List<String[]>> menus = new HashMap<>();

        /* 게시판 관리 서브 메뉴 S */
        List<String[]> boardMenus = new ArrayList<>();
        boardMenus.add(new String[] {"게시판 관리", "/admin/board"});

        menus.put("board", boardMenus);
        /* 게시판 관리 서브 메뉴 E */

        /* 회원 관리 서브 메뉴 S */
        List<String[]> memberMenus = new ArrayList<>();
        memberMenus.add(new String[] {"회원 목록", "/admin/member/list"});

        menus.put("member", memberMenus);
        /* 회원 관리 서브 메뉴 E */

        return menus;
    }

    /**
     * 주 메뉴 코드 - /admin/주메뉴 코드
     * @return
     */
    @ModelAttribute
    public String menuCode() {
        String uri = request.getRequestURI().replace(request.getContextPath(), "");
        Pattern pattern = Pattern.compile("^/admin/([^/]+)/?");
        Matcher matcher = pattern.matcher(uri);

        return matcher.find() ? matcher.group(1) : "";
    }

    @ModelAttribute
    public List<String[]> subMenus() {
        Map<String, List<String[]>> menus = subMenusAll();

        String code = menuCode();

        return menus.get(code);
    }
}
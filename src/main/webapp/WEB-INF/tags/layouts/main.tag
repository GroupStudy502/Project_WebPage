<%@ tag body-content="scriptless" %>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="layout" tagdir="/WEB-INF/tags/layouts" %>
<%@ taglib prefix="util" tagdir="/WEB-INF/tags/utils" %>
<%@ attribute name="title" %>
<fmt:setBundle basename="messages.commons" />
<c:url var="cssUrl" value="/css/" />
<c:url var="jsUrl" value="/js/" />
<c:url var="homeUrl" value="/" />
<c:url var="searchUrl" value="/board/search" />
<c:url var="logoUrl" value="/images/logo.png" />
<c:url var="profileUrl" value="/images/profileimg.png" />

<layout:common title="${title}">
    <jsp:attribute name="header">
        <section class="site-top">
            <div class="layout-width inner">
                <div class="left">
                    <a href="${homeUrl}">
                        <i class="xi-home-o"></i>
                        <fmt:message key="홈" />
                    </a>
                </div>
                 <style>
                     div {
                          text-align: center;

                          }
                 </style>
                <div class="right">
                    <util:guestOnly>
                        <a href="<c:url value='/member/join' />">
                            <i class="xi-user-plus-o"></i>
                            <fmt:message key="회원가입" />
                        </a>
                        <a href="<c:url value='/member/login' />">
                            <i class="xi-log-in"></i>
                            <fmt:message key="로그인" />
                        </a>
                    </util:guestOnly>
                    <util:memberOnly>
                       <c:if test="${myProfile != null}">
                           <img src="${myProfile.frontImage}" width="50">
                       </c:if>
                        <fmt:message key="LOGIN_MSG">
                            <fmt:param>${loggedMember.userName}</fmt:param>
                        </fmt:message>
                        <a href="<c:url value='/mypage' />">
                            <fmt:message key="마이페이지" />
                        </a>
                        <a href="<c:url value='/member/logout' />">
                            <fmt:message key="로그아웃" />
                        </a>

                       <a href="<c:url value='/admin' />" target="_self">

                                     <fmt:message key="사이트_관리" />
                                      <i class="xi-lock"></i>
                                        </a>
                    </util:memberOnly>

                </div>
            </div>
        </section>
        <section class="logo-search">
            <div class="layout-width inner">

                <div class="top">
                    <form class="search-box" method="GET" action="${searchUrl}" autocomplete="off">
                        <input type="text" name="keyword" placeholder="<fmt:message key='검색어를_입력하세요.' />">
                        <button type="submit">
                            <i class="xi-search"></i>
                        </button>
                    </form>
                </div>

                <div class="bottom">
                    <a href="${homeUrl}" class="logo">
                        <img src="${logoUrl}" alt="<fmt:message key='로고' />">
                    </a>
                </div>
            </div>
        </section>
        <nav>
            <div class="layout-width inner">
                <a href="<c:url value='/board' />" target="_self">
                자유게시판
                </a>
                <a href="<c:url value='/pokemon' />" target="_self">
                포켓몬 도감
                </a>
                <a href="<c:url value='/pick' />" target="_self">
                포켓몬 뽑기
                </a>
                <a href="https://pokerogue.net" target="_blank">
                포켓몬 게임
                </a>
            </div>
        </nav>
    </jsp:attribute>

    <jsp:attribute name="footer">
        <div class='layout-width'>
            <div class="footer_logo"><img src="${logoUrl}" alt="로고"></div>
            <div class='footer_wrap'>
                <div class="footer_left">
                    <ul>
                        <li><a href="https://github.com/GroupStudy502/Project_WebPage">Team502 &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/0taelove">&nbsp;Otaelove &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/hjchoirr">hjchoirr &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/jek730">jek730 &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/decl5th">decl5th &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/sswum">sswum &nbsp;&nbsp;&nbsp;|</a></li>
                        <li><a href="https://github.com/lionjeongin">lionjeongin</a></li>
                    </ul>

                </div>
                <div class="footer_right">
                    <div class="sns">
                        <ul>
                        <li><a href="https://www.facebook.com/PokemonCoKr"><img src="./images/icon_faceb.png" alt="facebook 이미지"></a></li>
                        <li><a href="https://www.instagram.com/pokemon_korea_official/?igshid=OGQ5ZDc2ODk2ZA%3D%3D"><img src="./images/icon_insta.png" alt="instagram 이미지"></a></li>
                        <li><a href="https://www.youtube.com/user/PokemonKoreaInc"><img src="./images/icon_youtube.png" alt="youtube 이미지"></a></li>
                        <li><a href="https://www.pokemonstore.co.kr/"><img src="./images/icon_pokemon.png" alt="pokemon 이미지"></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="copyText">
            <p class="copy">This Pokémon encyclopedia project was created by GroupStudy502.</p>
            <p class="copy">Developed in Room 502, 5th Floor, at the Choongang Academy.</p>
            <p class="copy">Stay tuned for more updates and features!</p>
            <p class="copy"><br>&copy; 2024 포켓몬 도감 Project_WebPage. All right reserved.</p>
            <p class="copy">Visit our team&apos;s GitHub repository for our latest projects and contributions.</p>
            </copy>
    </jsp:attribute>
    <jsp:attribute name="commonCss">
        <link rel="stylesheet" type="text/css" href="${cssUrl}main.css">
    </jsp:attribute>
    <jsp:attribute name="commonJs">
        <script src="${jsUrl}main.js"></script>
    </jsp:attribute>
    <jsp:body>
        <jsp:doBody />
    </jsp:body>
</layout:common>
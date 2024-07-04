# 기능 명세서

## 🎇 회원

### ✅ 공통

- 권한

    - ADMIN / MEMBER 인가.
    - 가입 시 일반 회원(Member)

### ✅ 로그인

- 비밀번호는 암호화(hashing) 과정을 거쳐 DB에 저장.

### ✅ 회원가입

- ID(이메일) : 영문과 숫자 조합으로 4자리 이상 16자리 이하 제한, 중복 여부 확인??
- PW : 영문, 숫자, 특문 조합으로 8자리이상 16자리 이하 제한 ??
- 회원명 : 한글 입력, 2자리 이상 30자리 이하, 공백 입력 제한 ??

## 🎇 메인 페이지

### ✅ 상단 우측
- 회원가입 -> 회원가입 페이지 이동
- 로그인 -> 로그인 페이지 이동
    - 로그인 이후 프로필 표시 및 로그아웃 표시

### ✅ 메뉴 베너

- 자유게시판 페이지로 이동
- 포켓몬 도감 조회 페이지로 이동
- 관리 페이지 페이지로 이동

### ✅ 메인 페이지

- 포켓몬 뽑기
    - 메인 중앙에 포켓몬 이미지 생성 -> 버튼 클릭 -> 로그인 페이지 이동 -> 로그인 -> 메인페이지이동
    - 로그인 상태 -> 포켓몬 뽑기 버튼 클릭 -> 팝업 창 생성 -> 뽑은 포켓몬 확인 -> 프로필 이미지 설정
    - 포켓몬 리스트 중 택1 -> 프로필 사진으로 설정할 수 있도록 기능

## 🎇 마이페이지
- 나의 프로필
    - 띠부씰 뽑기 혹은 나의 띠부씰에서 설정한 프로필 이미지 조회
- 나의 띠부씰
    - DB(MY_POKEMON 테이블)에 저장된 포켓몬 목록(띠부씰 뽑기 결과) 조회
    - 프로필 이미지로 변경, 선택삭제, 전체삭제 기능
- 회원정보 수정
    - DB(MEMBER 테이블)에 저장된 회원정보(이메일, 비밀번호, 비밀번호 확인, 이름, 회원 구분) 조회
    - 회원명 및 비밀번호 수정 기능

## 🎇 자유게시판

### ✅ 게시글

- '글쓰기'로 게시글 작성
- 게시제목으로 조회.

### ✅ 파일 업로드 & 다운로드 ????

- 이미지 파일 업로드 가능하도록 구현.(multipart/form-data)????

## 🎇 포켓몬 도감 페이지

### ✅ 도감 조회

- 포켓몬 api 적용
- 검색으로 포켓몬 조회.
- https://www.pokemonkorea.co.kr/pokedex 참조

## 🎇 관리자 페이지

### ✅ 회원 관리

- 회원 전체 조회
- 회원 삭제

### ✅ 게시판 관리

- 게시글 전체 리스트 조회
    - 게시글 삭제

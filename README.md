# missionGame


------------------------------
카카오 Oauth 2.0 로그인 구현
 - 프론트에서 카카오 로그인 페이지로 바로 요청.
 - 로그인 성공시 인가코드 받음. 
 - 리다이렉트된 프론트 페이지 created 될때 바로 스프링 서버에 인가코드를 스프링에게 줌.
 - 스프링은 받은 인가코드로 access_token 요청함.
 - access_token 받고 그걸로 유저 정보 요청함.
 - 요청한 유저 정보로 DB에 있는 회원인지 확인함.
    - 있다면 DB에 저장되있는 jwtFakeToken 가져옴.
    - 없다면 DB에 새로 insert함. ( 닉네임, 프로필이미지, access_token, refresh_token, jwtFakeToken, kakaoId ), 이때 jwtFakeToken 생성함.
 - jwtFakeToken 프론트에게 줌.
 - 응답 받은 프론트는 jwtFakeToken 로컬 스토리지에 저장하고, vuex 로그인 상태로 만들고, 로그인 성공 메세지 띄우고 홈으로 감.

 - 로그아웃 시에는 로컬 스토리지에 키 jwtFake 삭제함. vuex 로그아웃 상태로 만듬. 
 
 - 로그인되면 메인 화면에 프로필 이미지와 닉네임이 나옴. 메인 화면 갈때마다 로컬 스토리지 jwtFake 키 값으로 스프링에 요청 보냄.
 - 요청 받으면 jwtFake로 DB에 저장되있는 accessToken으로 바꾸고 이 accessToken 이용해서 스프링에서 카카오에 유저 정보 요청하고 응답받아서 다시 프론트로 주는 것. 
 
 개선점
   - 프론트에서 인가코드 요청하는 URL로 바로 요청함. (백엔드로 로그인 요청 후 다시 프론트로 인가코드 요청하는 주소로 리다이렉트 하고 싶음.)
     -> 이거 때문에 프론트엔드 코드에 앱 키랑 리다이렉트 주소가 드러남..
   - 로그인 성공하면 홈으로 갈때 스프링에서 리다이렉트하라고 요청해서 프론트에서 바뀌도록 하고 싶음.
      -> 둘다 스프링에서 리다이렉트 하려면 CORS 문제 생겨서 그럼. 이거 해결해야 가능.
      -> Spring Security 문제인지, CORS 설정 문제인지..
      
   - jwtFake 토큰 jwt 토큰으로 바꿀 것

   - access token이 만료되면 refresh token으로 다시 얻어야 하는데 이러한 로직 없음.
   - https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api#refresh-token 

--------------------------------

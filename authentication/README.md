![Untitled-5-01](https://gitlab.com/devjun10/member-management/uploads/8043a4940eea1b629042477b66a1ac98/member-management-01.png)
<div align="center">

💁 회원 인증 서버<br>

`# 회원` `# 등급` `# 회원관리` `# 효율적 조회`

`# 관리` `# 인증` `# 인가`

</div>
<br/>

## 💻 프로그래밍 요구사항

&nbsp;&nbsp;&nbsp;&nbsp; - 테스트 커버리지는 70% 이상을 유지한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 가능한 모든 값들은 값 객체로 포장한다.<br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 롬복은 가능한 사용하지 않는다.  <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 롬복을 사용한다면 @RequiredArgsConstructor정도만 사용한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 회원가입은 스프링 시큐리티를 사용해 구현한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 인증/인가는 방식은 쿠키/세션 및 토큰 중 자유롭게 선택한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - OAuth2를 사용할 경우 Google, Github 중 하나를 선택한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 조회를 효율적으로 할 수 있도록 다양한 방법을 고민한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 운영에 필요한 각종 지표를 수집해 모니터링한다. <br/>

<br/>
<br/>
<br/>
<br/>
<br/>

## 🔖 기능 요구사항

&nbsp;&nbsp;&nbsp;&nbsp; - 관리자는 회원의 서비스 이용유무를 선택할 수 있다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 해외 아이피는 차단한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 회원의 로그인 시간, 아이피 등을 수집한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 일정량의 사용자 요청만 받을 수 있도록 유량을 제어(Rate Limiter)한다. <br/>
&nbsp;&nbsp;&nbsp;&nbsp; - 모든 요청과 응답은 로그를 남겨 확인할 수 있도록 한다. <br/>

<br/>
<br/>
<br/>
<br/>
<br/>
<br/>

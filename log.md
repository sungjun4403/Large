<h1>Log</h1>

<h3>1. 기획</h3>
2022/10/20 ~ 2022/10/31 (띄엄띄엄) <br/>

<br/>

- 프레임워크 선정 (프론트: Vue, 백: Spring)
- 주요 기능 기획 (높은 자유도, 자동완성, 깃헙 코드 블럭 따오기(blackbox 벤치마크), 유저 행동 분석, 깃헙 로그인)
- 프로젝트 규모 설정 (웹, 웹앱, 안드로이드 앱)
- 화면 스케치

<br/>

* * * *

<h3>2. post 구현 (스프링 친해지기)</h3>
2022/11/01 ~ 2022/11/05 <br/>

<br/>

- 스프링 CRUD 경험
- 포스트 구현

<br/>

* * * *

<h3>3. Vue 세팅 (Vue 친해지기)</h3>
2022/11/06 ~ 2022/11/07 <br/>

<br/>

- .vue 작동 원리
- 홈화면 구성
<img width="300" alt="Screenshot 2022-11-08 at 23 25 32" src="https://user-images.githubusercontent.com/96364048/200944150-f6fb21bc-4c2e-4522-a558-53a2c225d2a9.png" >

- 프론트-백 프록시 연결

<br/>

* * * *

<h3>4. 프로젝트 identitiy 설정, NavBar 디자인 및 기능 구현</h3>
2022/11/08 ~ 2022/11/09 <br/>

<br/>

- 프로젝트 폰트 설정 : Roboto Serif
<img width="500" src="https://user-images.githubusercontent.com/96364048/200946757-ba70ca5e-cfd1-41f7-8713-79ae0f1be71e.png">

- 프로젝트 아이콘 그림 
<img width="200" src="https://user-images.githubusercontent.com/96364048/200947285-c344d15e-ca34-49ff-a1f8-d2052770b31b.png">

- 프로필 아이콘, 설정 아이콘, 이슈 리포트 아이콘 그림
- 프론트-백 프록시 연결 
- NavBar 디자인 및 기능 구현
<img width="500" alt="Screenshot 2022-11-10 at 6 19 21" src="https://user-images.githubusercontent.com/96364048/200945217-1500835d-3a63-4feb-954f-d42b9cfff850.png">

<br/>

페이지 최상단에서 일정 수준 이상 스크롤시 NavBar 드러나도록 기능 구현.

~~~javascript
var ScrollUp = 0;

addEventListener("mousewheel", e => {
    const direction = e.deltaY > 0 ? "Scroll Down" : "Scroll Up";;
    if (direction == "Scroll Up" && window.scrollY == 0) {
        if (document.getElementById('NavBar').style.display == 'none' && ScrollUp > 3){
            document.getElementById('NavBar').style.display = 'inline'
            ScrollUp = 0;
        }
        ScrollUp = ScrollUp + 1;
    }
    if (direction == "Scroll Down") {
        ScrollUp = 0;
    }    
});
~~~

<br/>

NavBar를 평상시 화면에 보이지 않도록 한 이유는 본 프로젝트 컨셉인 '높은 자유도의 개발자 블로그 플랫폼' 과도 연결된다. 고정된 영역을 차지하지 않음으로서 유저가 본 서비스의 전체 화면에 대한 구성 권한을 가진다. 

<br/>

* * * *

<h3>5. Github 로그인 구현 </h3>
2022/11/10 ~ 2022/11/11 <br/>

<br/>

- Github 로그인은 카카오처럼 Oauth2.0 쓰기가 애매해서 <a href="https://somuchthings.tistory.com/130">Spring OAuth 없이 Spring Boot로 Github OAuth 사용하기</a> 참고해서 했음
- 로그인 과정은 
1. OAuth 로그인 화면에서 로그인 후 
2. 헤더에 토큰을 받기 위한 토큰 인가 코드 받아와서 
3. api로 코드 보낸 뒤 유저 정보 접근 위한 토큰 받기
4. 토큰으로 유저 프로필 받아오기 
- 결과적으로 로그인시 인가 가능한 정보는 

<img width="500" alt="Screenshot 2022-11-10 at 6 19 21" src="https://user-images.githubusercontent.com/96364048/203679824-a4eec106-5ca8-43ca-a6dc-934f35f144e6.png">

<br/>

위 정보들 중 다음 요소들을 DB에 저장한다

- login : UNIQUE한 깃헙 아이디. 유저 식별에 사용된다
- avatar_url : 깃헙 내 유저의 프로필 사진
- name : 이름 
- company : 회사 
- blog : 깃헙에 추가로 등록된 블로그
- location : 거주지. 시, 구 단위로 저장되어 있음 
- email : 깃헙에 추가로 등록된 이메일. 깃헙 가입시 비밀번호 복구용으로 입력하는 이메일과 상이함
- bio : 상태 메시지 
- updated_at : 가장 최근 깃헙 업데이트 시간. 커밋, 에딧, 이슈 풀링시 갱신되는 시간

이중 compnay, location, email, bio 대부분 등록되어있지 않았다. 오히려 README.md에 더 잘나와 있는 듯 하여 해당 정보는 서비스 가입시 DB에 저장하기는 하나 추가 입력할 수 있도록 한다. 

*_url 포맷의 요소는 따로 저장하지 않는다. 레포지토리, 이벤트, 팔로우와 구독 등 서비스에 필요한 요소이지만 형식화 되어 있어 필요시 만들어 사용이 가능하다. (DB 저장 이유 X)

<br/>

* * * *

<h3>6. 스프링 친해지기</h3>
2022/11/12 ~ 2022/11/16<br/>

- 프로젝트 관련해서 뭘 하진 않았다. 일단 스프링 자체를 너무 몰라서 한주 쉬면서 인강 들었다. 

https://www.inflearn.com/course/스프링부트-JPA-활용-1 

<br/>

* * * *

<h3>6. Member 구현 </h3>
2022/11/17 ~ 2022/11/23 <br/>

- 최초 깃헙 로그인시 유저 정보가 저장되는 DB 테이블

<br/>

* * * *

<h3>7. JWT 구현</h3>
2022/11/19 ~ 2022/11/20<br/>

- 로그인 관련 기능 수행할 JWT 토큰 구현
- 로그인 시 멤버 정보들을 사용하여 AccessToken과 RefreshToken을 생성하여 헤더에 싣는다. 

<br/>

* * * *

<h3>8. real-time html renderer 구현1</h3>
2022/11/21 ~ 2022/11/23<br/>

- 높은 자유도의 기술 블로그인 본 서비스는 화면 구성 또한 유저가 주도권을 가진다. 블로그 설계와 포스트 입력 및 등록 모두 html, css, js로 지원하기 때문에 유저가 html 코드를 입력하며 화면을 확인하기 위해 실시간 렌더링 툴을 제공해야 한다. JSRender, RenderKid 등의 js 렌더링 라이브러리 등을 사용하려 하였으나 렌더링 결과에 대한 화면 설계, 렌더링에 들어가는 요소에 대한 입력 방법과 같은 복잡한 조건들로 인해 렌더링 라이브러리의 문법을 공부해야해 포기하고 다음 코드로 매우 간단하게 구현하였다. 

<br/>

~~~html
<textarea id="inpt" cols=50 rows=50 oninput="reload()"></textarea>
<div id="Render"></div>
~~~

~~~javascript
function reload() {
    document.getElementById('Render').innerHTML = document.getElementById('inpt').value
}
~~~

<br/>

<img width="900" src="https://user-images.githubusercontent.com/96364048/203693526-e4b184c2-1112-4302-8a0f-16bd06feec90.gif">

<br/>

실시간 렌더링 시연. oninput으로 한 문자 입력시마다 렌더링되는 것을 확인할 수 있다. 또한 태그 내 style에 text-decoration:none도 작동한다. 

- 태그 내 sytle의 속성은 정상적으로 적용되나 아래와 같은 입력은 지원되지 않았다. (textarea 입력 가정) 

<br/>

~~~html
<!-- this works -->
<a href="https://google.com" style="text-decoration:none">GOOGLE</a>

<!-- this doesn't  -->
<a href="https://google.com" id="google">GOOGLE</a>
<style>
    #google {
        text-decoration : none
    }
</style>
~~~

<br/>

스타일 태그 분리시 작동하지 않는 이유는 2가지로 예측해 볼 수 있다.
1. 태그 안 스타일 태그 렌더링 불가 

<br/>

~~~html
<div id="Render">
    <a href="https://google.com" id="google">GOOGLE</a>
    
    <!-- this doesn't  -->
    <style>
        #google {
            text-decoration : none
        }
    </style>
</div>
~~~

<br/>

2. textarea.value를 .innerHTML로 대입하는 과정에서 텍스트 컴파일러 문제 발생 

<br/>

~~~html
<div id="Render">
    <!-- might be  -->
    <a href="https://google.com" id="google">GOOGLE</a>&nbsp;<style>&nbsp;#google {&nbsp;text-decoration : none&nbsp;}&ensp;</style>
</div>
~~~

<br/>

엔터키나 스페이스가 대입 과정에서 이상한 코드로 치환된 것이 아닌가 하는 의심. 

<br/>

아마 전자일 듯 싶다. 

<br/>

<code><style></code>로 시작하는 코드는 #Render에 넣지 않고 다른 스타일에 주입하거나 css 파일을 따로 만들어 대입하면 될 듯 하다. (JS도 같은 방법으로 할 예정)

결론적으로 가장 큰 문제는 js로 렌더링하는 코드가 아니라 __서버의 렌더링 머신을 사용하는 방법이기 때문에__ 유저가 렌더링을 요청할 때마다 쓰레드에 부하가 발생된다는 것이다. 제한된 DB 접근과 간단한 html 렌더링 수준이더라도 과도한 렌더링 요청으로 인해 __서버 성능에 큰 영향을 끼칠수 있다__. 렌더링 조건을 완화하거나 제한적인 조건을 걸어 부하를 줄여야 한다. 
    
<br/>

* * * *

<h3>9. 프로젝트 구체화</h3>
2022/11/22<br/>
   
<br/>
    
일부 구현된 포스트, 멤버, JWT를 제외한 기능들의 구현 방법을 구체화하였다
- 댓글 기능 : <a href="https://github.com/apps/giscus">Gitcus api</a>
 
<br/>
 
<img width="775" src="https://user-images.githubusercontent.com/96364048/203699798-0a6e551a-656b-41e1-9c90-a52b5920b63a.png">
    
<br/>
    
- Markdown Renderer : <a href="https://docs.github.com/en/rest/markdown#render-a-markdown-document">Github official Markdown API</a>

- 코드 검색 방법 : 주석과 주석 위치를 담는 리스트만 DB에 저장. 검색시 DB에서 주석 검색 후 위치 찾아 Github API로 코드 받아와 paste. DB에 모든 코드를 저장하는 것보다는 느리지만 검색부터 API로 하는 것보다 매우 효율적. 검색은 매우 빠르고 paste는 조금 느리지만 최선일듯.
    
- 코드 검색 후 paste 범위 선정 : 아래 깃헙 코파일럿 시연 처럼 검색하여 블로그 작성중 바로 코드 삽입할 수 있도록 할 예정. 사진 예시로 설명

<img width="600" src="https://user-images.githubusercontent.com/96364048/203704749-7c869ac5-355a-4611-8575-7d9d101de32b.gif">
    
<br/>
    
아래 코드가 퍼블릭 레포지토리에 있을 때 
    
<img width="600" src="https://user-images.githubusercontent.com/96364048/203704354-761db70d-b89f-4326-826f-a4bc579af583.png">
    
<br/>

    
아래와 같이 <code>// 검색할 주석 내용 </code>을 입력 시 주석 및 첫 엔터까지가 우선 import 될 코드로 선정. 밑의 코드는 옵션으로 보여지며 추가로 삽입 가능
    
<img width="600" src="https://user-images.githubusercontent.com/96364048/203705401-a5197381-dd69-4d3c-83ce-413c3ff8f578.png">    

<br/>
    
- 게시물 검색 


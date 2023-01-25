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

<br/>

- 프로젝트 관련해서 뭘 하진 않았다. 일단 스프링 자체를 너무 몰라서 한주 쉬면서 인강 들었다. 

https://www.inflearn.com/course/스프링부트-JPA-활용-1 

<br/>

* * * *

<h3>6. Member 구현 </h3>
2022/11/17 ~ 2022/11/23 <br/>

<br/>

- 최초 깃헙 로그인시 유저 정보가 저장되는 DB 테이블

<br/>

* * * *

<h3>7. JWT 구현</h3>

2022/11/19 ~ 2022/11/20<br/>

- 로그인 관련 기능 수행할 JWT 토큰 구현
- 로그인 시 멤버 정보들을 사용하여 AccessToken과 RefreshToken을 생성하여 헤더에 싣는다. 

<br/>

* * * *

<h3>8. real-time html renderer 구현 1</h3>
2022/11/21 ~ 2022/11/23<br/>

<br/>

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

엔터키나 스페이스가 대입 과정에서 엔터키나 띄어쓰기가 이상한 코드로 치환된 것이 아닌가 하는 의심. 

<br/>

아마 전자일 듯 싶다. 

<br/>

<code><style></code>로 시작하는 코드는 #Render에 넣지 않고 다른 스타일에 주입하거나 css 파일을 따로 만들어 대입하면 될 듯 하다. (JS도 같은 방법으로 할 예정)

결론적으로 가장 큰 문제는 js로 렌더링하는 코드가 아니라 __서버의 렌더링 머신을 사용하는 방법이기 때문에__ 유저가 렌더링을 요청할 때마다 쓰레드에 부하가 발생된다는 것이다. 제한된 DB 접근과 간단한 html 렌더링 수준이더라도 과도한 렌더링 요청으로 인해 __서버 성능에 큰 영향을 끼칠수 있다__. 렌더링 조건을 완화하거나 제한적인 조건을 걸어 부하를 줄여야 한다. 
    
<br/>

* * * *

<h3>9. 프로젝트 기능 구체화</h3>
2022/11/22 ~ 2022/11/23<br/>
   
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
    
- 게시물 검색 바 : 기본 서비스 완성 후 구현 예정
- 요소 자동완성 : 기본 서비스 완성 후 구현 예정
- 추천 게시물 : 기본 서비스 완성 후 구현 예정. medium 처럼 다른 유저의 게시물도 추천해줄지, tistory처럼 자기 게시물 내에서 추천할지도 선택할 수 있도록 설계 예정
- 유저 행동 분석 : 유저 템플릿으로 이름 변경. 아래 IntelliJ의 template 처럼 유저가 포맷을 지정해 놓고 단축키로 호출 할 수 있도록 할 예정. 일기장, 데일리 개발 로그 같은 포스트에서 자주 쓰이는 포맷을 불러올 수 있도록 하기 위함
    
<br/>
    
<img width="600" src="https://user-images.githubusercontent.com/96364048/203707707-86479749-68b7-47bb-b0db-55877a055b6b.png">

<br/>

* * * *

<h3>10. Member Edit 기능 구현</h3>
2022/11/23<br/>
    
- 멤버 수정 기능 구현. github 로그인시 받아오는 정보 중 blog, email은 gitBlog, gitEmail로 refactor하였고 위 두 요소들은 본 서비스에서 수정하지 못하도록 함. __블로그와 이메일은 추가 입력__ 단계에서 입력 지원. memberToken, gitID은 식별에 사용되는 UNIQUE한 요소이기 때문에 수정 불가로 지정. __profileImg, name, company, location, bio는 수정 가능__ 

- 멤버 수정 기능 구현 중 이슈: 
1. urlmapping에는 <code>@GetMapping("/{gitID}")</code> 식으로 사용 불가능 ("/member/{gitID}")으로 사용 가능
2. @Transactional 어노테이션 뺴먹지 않기
3. 멤버 수정 기능은 정상적으로 작동하는데 postMan으로 확인시 404 not found 상태로 보여짐. 모든 기능 정상적으로 작동하고 URL 매핑까지 정상적으로 작동하여 주소까지 잘 찾아가지만 status는 404... 에러 메세지도 안뜨는 퓨어한 404... (아직 해결 X)
    
<br/>
    
* * * * 
    
<h3>11. real-time Renderer 구현 2 </h3>

2022/11/24 ~ 2022/11/29<br/>
    
- css가 먹히지 않았던 이슈를 해결하기 위해 documents.styleSheets[0].inserRule(String CSSRules)를 통해 해결... 한줄 알았으나 insertRule 이기 떄문에 속성 추가는 되는데 변경은 안되었던 것...
- 해결하기 위해 최초의 빈 styleSheet 객체를 deepClone하여 추가된 속성을 초기화하고 다시 밀어넣는 식을 사용하여 하였으나, 객체 특성상 deepClone 실패 (deepClone 사용하기 위해 require 사용하는 것도 어려웠음)
- 결국 방식을 아예 바꿔 documents.write()를 사용하려 했으나 어디서 막힌지 모르는 보안 정책상 무작성 스크립트를 떄려넣는 것은 실패
- 그 대안으로 생각난게 txt에 작성해서 렌더 전 html로 확장자 변경
- require, import 차이를 몰라서 확장자 변경을 위한 툴 불러오기도 실패
- CommonJS, ES module 차이부터 node js 기초까지 공부하면서 했으나 결국 원래 사용하려던 라이브러리는 작동 안됨. 결국 child_process 라는 모듈로 command line 명령어로 확장자 변경에는 성공. 
- 파일 작성만 하면 되는데 vi는 파일에 들어가는 느낌이라 inline command line 명령어를 찾던지 해야할 듯. filepath, path, fs같은 node 파일 모듈은 실패. 이 역시 보안 이슈일 듯
    
<br/>
    
* * * * 
    
<h3>12. Git 코드 검색 기능 구현 1</h3>
    
2022/11/30<br/>
    
- 유저의 퍼블릭 레포 코드를 전부 저장할 수 없기 때문에 검색 위해 필요한 주석과 그 위치 정보만 저장할 예정
- 결과적으로 DB에 저장될 주석 리스트는 <code>['주석 내용', '파일 경로', '주석 위치 (몇번째 줄, 어디부터)']</code> 형태 
- [https://](https://raw.githubusercontent.com/[username]/[repo_name]/main/[filename]) 에서 코드 파일 받아올 수 있음. 결과는 아래

<br/>
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/204974708-8775dda7-5c33-406e-9ef7-e9a30fff983c.png">

<br/>

- 줄마다 리스트에 저장까지 성공. 언어마다 주석이 다르기 떄문에 (#, //, ''') 파일 확장자 명으로 먼저 코드 언어를 감지 한 후 각 줄에서 주석을 검색
- 한 언어에서도 여러 확장자가 있기 때문에 (ex. python -> .py, .pyc(compiled), .ipynb(jupiter)) HashMap으로 <code>{Java=[java], CSS=[css], HTML5=[html], Javascript=[js], Python=[py, pyc, ipynb]}</code>로 저장. 
- 한 언어에서 주석 종류도 다양하기 때문에 <code>{Java=[//], CSS=[/*+*/], HTML5=[<!--+-->], Javascript=[//], Python=['''+''', #]}</code> 형태로 저장. 언어 분류까지 구현. 주석 검색은 Git 코드 검색 구현 2에서. 
    
<br/>
    
* * * * 
    
<h3>13. Git 코드 검색 기능 구현 1</h3>
    
2022/12/01 ~ 2022/12/03<br/>
    
- file.forEach안에 주석 for문, 이중 for문으로 주석 검색. 단일 주석 (비대칭 일회형 주석 ex. #, //) 검색 우선 구현
- <code>[gitID, annotation, filepath, annotationStartsAt, annotationEndsAt]</code>형태로 정보 조합하여 저장. DB 저장까지 구현 완료
- AnnotationRepository에서 List<Annotation> findByFilepath 구현. NPE대신 빈 리스트 반환 (Optional<List<String>> 포맷은 사용 불가)
- 비단일 주석 (''' + ''''), 비대칭 주석 (<!-- + -->)은 따로 구현 
- 깃헙 커밋시 코드도 변경되기 때문에 커밋 감지하여 Annotation table, Member.updated_at 갱신할 메서드도 필요 (Sync 기능)
- 문자열 안의 주석 (ex. "#") 같은 주석 문자는 제외해야함
- 주석 내용 중복 처리도 해야함
- 아래 DB 저장 예시 
    
<br/>
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/205903891-93abee52-2595-4d08-bbcc-f4f35965d083.png">
    
<br/>
    
* * * * 
    
<h3>14. Git 코드 검색 기능 수정</h3>
    
2022/12/04<br/>
    
- 주석으로 본인 레포에서 코드 검색해서 따오는 기능을 전면 수정.
- 일단 코드 내에서 주석 자체를 찾을 수가 없음. 그 이유는 다음과 같음
1. 단일 주석은 Line By Line 으로 찾을 수 있다. 그러나 비단일 주석은 줄단위 검색에서 발견 할 수 없다. 파일 단위 검색으로 찾아야 한다.
2. 주석 안 주석을 걸러낼 수 없다. 커맨드 + 슬래쉬로 주석처리를 하다보면 중복 주석이 생긴다. (// // 주석 내용 어쩌구)
3. 문자열 안 주석을 걸러낼 수 없다. 이게 기능 수정의 가장 큰 이유이다. 파이썬에서 "#" 도 주석으로 검색되는데 문자열이 줄단위 검색에서 걸러지지 않기 때문에 주석인지 문자열인지 판별이 불가능하다.
4. 한 파일 안에서 주석 케이스가 너무 다양하다. html 안에서 태그마다 주석 종류가 다르다. <body> 태그에서는 비단일 비대칭형 <!-- + -->, <script> 태그에서는 //,<style> 태그에서는 /* + */ 
   
불가능은 아닌듯 하지만 난이도 상 이상의 알고리즘 문제로 보인다. 
그리고 다른 것 보다도. 

<h3>굳이 주석으로 검색해야 하나?</h3>
    
command + F로 생짜 검색 때리면 안되나? 그게 더 유용할 듯 싶어서 기획부터 뒤집기로 했다. 

<h3>파일 내 통짜 검색으로 변경</h3>    

<br/>
    
* * * * 
    
<h3>15. Template 기능 구현 1</h3>
    
2022/12/05<br/>
    
- 템플릿 DB 구현, createTemplate 구현
- 아래 DB 예시 

<br/>
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/205904825-88a05e06-c6d9-4ceb-84a3-bb6aaf391d1e.png">

<br/>
     
* * * * 
    
<h3>16. Vue routing 1</h3>
    
2022/12/06~<br/>
    
- vue routing
- router/index.js
    
<br/>
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/206902006-a3d53091-a3f9-4e26-bb6a-15f9a31b1d7b.png">
    
<br/>
    
- main.js에서 router 사용할 component에 <code>import router from './router'</code>, <code>createApp(NavBar).use(router).mount('#NavBar')</code>  

- github application 설정에서 callback uri를 localhost:3000/afterlogin으로 수정. 로그인 후 아래 화면으로 이동하여 백에 인가 코드 (path varible : code) 전달 후 /:gitID (ex. localhost:3000/sungjun4403)으로 이동
    
    - Git 유저 정보에 접근하는 code를 노출하는 것이 위험해 보일 수 있으나 어차피 일회성 코드라서 상관 X
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/206903288-c9db7731-8917-45f1-b4e9-23bf0f295ff7.png">

<br/>
    
- <h3>Issues</h3>    
    
    - router-link 태그에서 에서 to="/경로 어쩌구 저쩌구"에 변수 대입이 안됨. { name: "" param: "" }. name도 안먹고 param에 동적할당하는 방법도 모르겠.
    
    - created, beforeMount, mounted 에서 실행되는 메서드가 두번씩 실행됨. VM숫자숫자 먼저, Vue 머신이 두번째. 아래는 추정 원인
    
<br/>
    
<img width="700" alt="" src="https://user-images.githubusercontent.com/96364048/206902558-0445bf7e-ac44-4c8a-a991-5077f42ac1a6.png">
    
<br/>
    
* * * * 
    
<h3>17~20. 이것 저것</h3>
    
2022/12/10~29<br/>
    
- 업데이트를 까먹었다. 로그 업데이트는 ~~귀찮아서 안~~ 까먹고 못했고, 깃 커밋은 진짜 까먹었다. 기획 수정이든 코딩이든 매일 하긴 했다. 
- 위 이슈는 해결했고 axios 사용해서 front - back 통신, jwt 구현해 놓은 것들 사용, edit_profile, edit_template등 여러가지 화면 만들고 기능 구현했다. (디자인은 아직) 
- UserNavBar는 UserSideBar로 대체 되었다. 
- Vue 전반에 대한 이해를 좀 하려고 하긴 했는데,, mounted 두번 실행되는 것은 아직도 해결 못했다. 
- Git Commit 감지 대신 직접 sync를 채택했다. Flask도 사용해보았는데 감지할 방법이 없었다.
- Git Last Commited at은 자체 status 버튼으로 대체한다. 
- jwt 헤더에 실어 나른 뒤 SecurityUtils.getLoginedUserGitId()로 token이 valid한지 확인하고 gitID를 추출하도록 하였다. 
- 로그인시 AccessToken을 LocalStorage에 저장하도록 하였고 권한 확인이 필요할 때에는 위 방법으로 확인하도록 하였다. 로그아웃 시에는 LocalStroage를 비우도록 하였다. 

<br/>
    
* * * * 
    
<h3>21~22. 이것 저것</h3>
    
2023/01/03~13<br/>
    
- UserHome 구현 (getAllPostsByGitID) 
- template 프론트 구현 진짜 완료
- 기타 등등 
    
<img width="700" alt="" src="https://user-images.githubusercontent.com/96364048/214343961-942acd40-8552-4d39-97f3-a21b88576153.png">

<br/>
    
* * * * 
    
<h3>23. 회원 탈퇴 기능 구현</h3>
    
2023/01/13<br/>
    
- Member 삭제, Template, Comment, Post는 cascade
    
~~~java
    @Transactional
    public void deleteNCascade(String gitID) {
        postRepository.deleteByGitID(gitID);
        commentRepository.deleteByGitID(gitID);
        templateRepository.deleteByGitID(gitID);

        Member toDeleteMember = memberRepository.findByGitID(gitID).orElseThrow();

        memberRepository.delete(toDeleteMember);
    }
~~~

<br/>
    
* * * * 
    
<h3>24. StatusIcon 구현 1</h3>
    
2023/01/10~13<br/>
    
- 페이스북 활동중, discord 프로필 밑 활동 아이콘 같은 역할
- js moment.js 라이브러리 활용. 
- 아래 아이콘은 post, template, profile, comment 등 여러 곳에서 재사용할 예정. 해당 component에 변수로는 IconHeight, time등이 있음 

<img style="float:left" width="400" src="https://user-images.githubusercontent.com/96364048/214347350-71749522-9426-494a-b9cc-3f91b3f4dedd.png">&ensp;
<img style="float:right" width="300" src="https://user-images.githubusercontent.com/96364048/214347373-6959e851-d039-43e6-867a-66c504b2c51f.png">

a few seconds ago / a minute ago

<br/>
    
~~~javascript
    setIconColorNTitle() {
        const fromNow = moment(this.time).fromNow()
        var i = -1
        this.IconTitle = fromNow
        if (fromNow.split(" ")[2] == "seconds" || fromNow.split(" ")[2] == "second") {
            i = 0
        }
        if (fromNow.split(" ")[1] == "minutes" || fromNow.split(" ")[1] == "minute") {
            i = 1
        }
        if (fromNow.split(" ")[1] == "hours" || fromNow.split(" ")[1] == "hour") {
            i = 2
        }
        if (fromNow.split(" ")[1] == "days" || fromNow.split(" ")[1] == "day") {
            i = 3
        }
        if (fromNow.split(" ")[1] == "months" || fromNow.split(" ")[1] == "month") {
            i = 4
        }
        if (fromNow.split(" ")[1] == "years" || fromNow.split(" ")[1] == "year") {
            i = 5
        }

        const StatusIcon = document.getElementById("StatusIcon")        
        StatusIcon.style.backgroundColor = '#' + colors[i]
    }, 
~~~

- 현재는 초, 분, 시, 일, 월, 연 단위로 일단 쪼갬. moment(this.time).fromNow() 시 "a few seconds ago", "5 minutes ago"등에서 시간 단위를 추출하여 아이콘 색상 설정. 컬러는 현재 7개로 지정하였지만 gradient로 변경할 예정 (r, g, b에 ++, --)
    
<br/>
    
* * * * 
    
<h3>25. UserHome 구현 2</h3>
    
2023/01/15<br/>

- Post summary 구현. title, body, author, published time fromNow(), (StatusIcon 추가예정), (rep. Image 대표 사진), (authorProfileImg)
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/214347341-2f25ebe6-6a33-45cc-953d-5bf50f5988f2.png">

<br/>
    
* * * * 
    
<h3>26. Post 구현 2</h3>
    
2023/01/18~19<br/>

- Post.vue 내의 commponent 구성
    - StatusIcon
    - NameNProfileImg
    - Post
    - Comments
    
- ifComment에 따라 Comment component 
    
<img width="700" src="https://user-images.githubusercontent.com/96364048/214481259-99728033-c884-4679-89bf-7d5bee94ece7.png">
    
~~~html
    <div id="PostStatusIcon">
        <StatusIcon :IconHeight='IconHeight' :time='LastModifiedDate' />
    </div>

    <br><br>

    <div id="PostNameNImg">
        <NameNImg :profileImg='profileImg' :bio='bio' :authorGitID='authorGitID' />
    </div>

    <br>

    <div id="PostPost">
        {{post.title}} by {{post.gitID}} <br>
        Created at {{post.createDate}} 
        Last Modidied at {{post.lastModifiedDate}} <br>
        {{post.body}}
        {{post}}            
    </div>

    <br><br><br><br><br>

    <div id="PostComments" v-if="post.ifComments == true">
        <Comments />
    </div>

    <div id="PostCommentsNotAllowed" v-if="post.ifComments == false">
        comments are not allowed in this post
    </div> 
~~~


    
    
    

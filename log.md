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

* * * *

<h3>5. Vue Routing</h3>
2022/11/10 <br/>

<br/>

- 

<br/>

* * * *


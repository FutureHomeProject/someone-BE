# ![image](https://user-images.githubusercontent.com/108318494/228595508-2de7b3a7-15de-454b-afb9-eec4ca6b7822.png)

<br><br>

### 🔍 프로젝트 기간 및 주요 기능

---
2023.03.24 ~ 2023.03.30
1. 회원가입 & 로그인
   - 사이트의 기능을 이용하기 위해 회원가입과 로그인이 필요합니다.
2. 집들이 게시글
   - 다른 사람과 인테리어를 공유합니다.
3. 집들이 댓글
   - 다른 사람의 인테리어를 주제로 소통할 수 있습니다.
4. 상품 페이지
   - 인테리어 소품을 찾아볼 수 있습니다.
5. 상품 댓글
   - 각 상품에 대한 의견을 다른 사람과 나눌 수 있습니다.

<br><br>

### 🤔️ 클론코딩 이렇게하니 편했어요

---
![image](https://user-images.githubusercontent.com/108318494/228607727-1ba1e877-6a7b-4b0d-ac35-ed9095796648.png)
1. 사이트 분석
   - 사이트에 어떤 기능들이 있는지 하나하나 눌러보고 경험해보기.
2. 해보고 싶은 기능 
   - 사이트를 분석하고 각자 해보고싶은 기능 적기.
3. 기능 정리
   - 구현할 기능 정리.
4. FE와 BE 각각 순서 정리.
   - 포지션별 개발 순서 정하기.
5. 완료시 추가할 기능 정리.
   - 완성하고 시간이 남았을 때 대비.

<details>
<summary>코드 컨벤션</summary>

1. 구글 코드 컨벤션 적용.
   - (따라하기)[https://velog.io/@injoon2019/IntelliJ%EC%97%90-Google-Java-Style-Guide-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0]

2. **클래스의 첫 글자는 대문자로 시작하며 UpperCamelCase 사용.**
```agsl
    class BoardCommentController // 카멜 케이스
```

3. **변수의 첫 글자는 소문자로 시작하며 LowerCamelCase 사용.**
```agsl
    String phoneNumber; // 카멜 케이스
```

4. **패키지는 소문자로만 구성.**
```agsl
   controller, service, repository 등등.
```

5. **메서드의 첫 글자는 소문자로 시작하며 각 단어의 첫 글자는 대문자인 LowerCamelCase 사용.**
```agsl
   public checkEmail() {}
```

6. **기능별 메서드 위에 간단한 설명 주석 작성.**
</details>

<details>
<summary>깃허브 관련 컨벤션</summary>

1. **깃허브 projects 탭을 이용한 기능별 티켓 발행.**

2. **커밋 메시지 형식 통일**
```agsl
    1. 구현 기능 커밋
    feat: 제목

    내용을 입력합니다.
    
    2. 수정
    fix: 제목

    내용을 입력합니다.
```

3. **사용하지 않는 패키지 제거 후 커밋 & 푸쉬**

</details>

<BR><BR>

### 🛠️기술 스택

---
![image](https://user-images.githubusercontent.com/108318494/228596001-e7d6ee87-d92f-4ef3-b09a-feba5a517643.png)
![image](https://user-images.githubusercontent.com/108318494/228596040-b7e8a8d7-0c30-4a4d-a245-b343d733415c.png)
![image](https://user-images.githubusercontent.com/108318494/228596040-b7e8a8d7-0c30-4a4d-a245-b343d733415c.png)
![image](https://user-images.githubusercontent.com/108318494/228596195-7fdda7fa-2d91-48d8-a572-3cde970eb5cd.png)
![image](https://user-images.githubusercontent.com/108318494/228596226-a6fa92de-2c47-4b30-95fc-c7a8b332511d.png)
![image](https://user-images.githubusercontent.com/108318494/228596258-e5daceaf-a067-4eaa-97d8-9183ab880ba6.png)
![image](https://user-images.githubusercontent.com/108318494/228596301-1b6c4592-cfdf-4846-90d8-b38abfe490c4.png)

<br><br>

### DB 다이어그램

---
![image](https://user-images.githubusercontent.com/108318494/228600929-dbe5fea2-e726-4e10-99a7-da3d19113092.png)

<br><br>

### ERD

---
![image](https://user-images.githubusercontent.com/108318494/228600684-14d747a2-3c7e-4d48-bbd4-4312323fc1a7.png)

<br><br>

### API

---
[API 설계 - 노션](https://www.notion.so/7f7781f4293b4bf7bbfd06a5f6da3185?v=b3b4faa474e94fd89ee5b20c37965193)

<br><br>

### TroubleShooting

---
<details>
<summary>DB의 연관관계.</summary>

- 문제
  - 프로젝트 설계시 각 DB들의 연관관계를 이해하는데 어려움이 있었다.
- 시도 & 해결
  - 다이어그램을 직접 그려보며 각 테이블이 어떤 컬럼을 갖고 있으며 외래 키를 가져야 하는지 파악.
  - 다이어그램을 기반으로 ERD를 다시 한번 작성. 
- 알게된 것
  - 1:N  N:1 등 N쪽에서 외래키를 갖고 주인이 되어야 한다.
  - 양방향은 실무에서 많이 안쓰는데 이유는 코드가 더 복잡해지기 때문이다. 
  - 한쪽이 변경되면 다른 쪽도 업데이트가 필요한지 확인해야 하는데 이 과정에서 불필요한 데이터베이스 쿼리가 발생할 수 있으며 속도 또한 느려진다.

</details>

1. 프로젝트 설계시 각 DB들의 연관관계를 이해하는데 어려움. 
   - 다이어그램을 직접 그려보며 테이블의 연관관계를 이해할 수 있었음.

# 11주차 wil

## 학습 내용

### API 문서화?
- API의 사용 설명서
- 프론트엔드와 협업할 때 공유

### How to API 문서화

1. `build.gradle`에 Spring doc 의존성을 추가 => API 문서 생성
	```gradle
	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0'
	```
2. Swagger-ui를 사용, API 문서에 swagger 디자인 적용
	```
	// 웹 브라우저에서 접속
	http://localhost:8080/swagger-ui/index.html
	```

### How to 프로젝트 배포?
1. 로컬에서 애플리케이션 빌드, jar 파일 생성
```zsh
	./gradlew clean // Gradle 캐시 및 빌드 디렉토리 삭제
	./gradlew build // Gradle 빌드
```
2. jar 파일 서버로 이동
3. 서버에서 jar 파일 실행
```zsh
	java -jar build/libs/todoapi-0.0.1-SNAPSHOT.jar
```

## 느낀 점
완성한 프로젝트를 배포하고 나니 뿌듯함과 동시에 스터디의 마지막이 실감난다.

앞으로 백엔드 스터디에서 배운 내용을 바탕으로 Spring Security와 같은 심화 개념을 공부할 계획이다.

한 학기 동안 유익하고 알찬 내용으로 이끌어 주신 스터디 멘토님께 감사하다는 말씀 드리고 싶다.

## 배포 후 swagger-ui 스크린샷

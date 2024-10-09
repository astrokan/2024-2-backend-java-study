# 4주차 학습 내용

---

## DB 설계
문제 상황은 크게 개체(엔티티)와 그 사이의 관계로 나타낼 수 있다.
- 개체(Entity) : 문제 상황을 구성하는 요소
- 관계(Relationship): 개체와 개체 사이의 관계

ER Model은 다음과 같이 DB로 구현할 수 있다.
- 개체 → 테이블
- 관계 → 테이블 or 외래키
- 속성 → 테이블 컬럼

개체와 개체는 사이의 관계는 아래와 같은 종류가 있다.
- 다대일 (N : 1)
- 일대다 (1 : N)
- 일대일 (1 : 1)
- 다대다 (N : M)

ERD Cloud에서 관계를 설정할 때는 보통 비-식별 관계를 선택한다.
- 식별 관계 : 관계 대상의 PK를 자신의 PK로도 사용하는 것
- 비-식별 관계 : 관계 대상의 PK를 자신의 FK로만 사용하는 것

## JPA
- Java Persistence API
- 데이터베이스에서 읽어온 데이터를 자바 객체로 매핑하는 자바의 표준 기술 (ORM)
- 엔티티(Entity)는 자바와 데이터베이스가 소통하는 단위
- 테이블의 데이터 하나(레코드)는 엔티티 객체 하나로 매핑된다.
- 엔티티 클래스를 정의하면, JPA가 엔티티 클래스 정의를 보고 테이블을 생성하는 SQL을 알아서 작성하고 실행한다.<br>
  -> JPA를 사용함으로써 SQL을 작성하는 시간을 줄일 수 있다.

---

## JPA 실습

### 의존성 추가
- build.gradle에 JPA와 H2 데이터베이스 의존성을 추가한다.
- 의존성 정보가 바뀌면 반드시 gradle을 다시 로드한다.

#### Spring Data JPA 의존성 추가
```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
```

#### h2 database 의존성 추가
```
implementation 'com.h2database:h2'
```

### DB 연결 정보 추가
- resources/application.properties 파일에 DB 접속 정보를 작성한다.
- 편의를 위해 이 파일의 확장자를 yml(application.yml)로 바꾼다.

**application.yml**
```yaml
spring:
  application:
    name: todo-api
  
  datasource:
    url: jdbc:h2:mem:todo;MODE=MYSQL
  
  h2:
    console:
      enabled: true
    
  jpa:
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQL8Dialect
```

### 관리자 콘솔 접속
- 어플리케이션을 실행하고, localhost:8080/h2-console 에 접속
- JDBC URL에 application.yml 파일에 작성한 URL 입력


### 엔티티 클래스
#### @Entity
- @Entity 어노테이션으로 이 클래스가 엔티티라는 것을 명시
  
#### @Id
- @Id 어노테이션으로 PK 필드에 이 필드가 PK라는 것을 명시
  
#### @GeneratedValue
- id 값은 보통 데이터를 생성할 때마다 자동으로 1씩 늘어난다.
- @GeneratedValue를 사용하면 id 값을 자동으로 생성한다.
- 이때 strategy는 IDENTITY로 설정한다. (키 값 결정을 DB에 위임)
  
#### @Column
- ERD에서 설계했던 Column 이름과 타입을 맞추기 위해,
필드에 @Column 으로 이름과 타입을 명시한다.

#### 외래키 컬럼
- 외래키 컬럼을 나타낼 때는 Long 타입의 외래키 필드 대신,
해당 엔티티 타입의 엔티티 객체를 필드로 가지도록 설계한다.

- 만약 외래키를 직접 저장한다면 연관된 데이터가 필요할 때,
외래키로 데이터를 조회하는 코드를 직접 작성해야 한다.

- 엔티티로 저장하면 테이블을 만들 때 외래키를 만들어주고,
연관된 데이터가 필요할 때, 자동으로 join 쿼리가 실행되면서
연관된 데이터를 얻는다.

- 외래키 필드에는 2가지 어노테이션을 지정해주어야 한다.
  - FK 컬럼 정보를 명시하는 어노테이션 (컬럼 이름 등)
    - @JoinColumn
  - 해당 외래키로 생기는 연관관계 종류를 나타내는 어노테이션
    - @ManyToOne @OneToOne @OneToMany @ManyToMany

- @JoinColumn은 외래키 컬럼 정보를 나타낸다.


#### 엔티티 연관관계
- 연관관계 종류를 나타내는 어노테이션에는 fetch 속성이 있다.
- 이 속성으로 연결된 엔티티를 언제 가져올지 명시할 수 있다.
- fetch type에는 EAGER, LAZY 2가지가 있는데, LAZY를 사용하자.
  - EAGER : 즉시 로딩, Todo 객체 정보를 가져올 때 연결된 User 객체의 모든 정보를 함께 한번에 가져온다.
  - LAZY : 지연 로딩, Todo 객체 정보를 가져올 때 연결된 User 객체의 정보는 필요할 때 가져온다.

#### 엔티티 생성자
- **alt+insert**키(window) 또는 **cmd+n**(mac)키를 눌러 id를 제외한 필드에 대한 생성자를 추가한다.(id 값은 자동으로 생성된다.)
- @NoArgsConstructor
  - JPA는 엔티티 객체를 다룰 때 public 또는 protected의 인자 없는 생성자가 필요하다.
  - @NoArgsConstructor를 사용하여 인자 없는 생성자를 만든다.
  - 이때 access 속성을 통해 접근 제한자를 protected로 설정한다.
  - 추가로 엔티티 객체에 @Getter를 추가해 모든 필드에 getter를 만든다.

---

## ERD 스크린샷
![ERD 스크린샷](./erd_img.png)

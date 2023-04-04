# 가계 내역 관리 프로젝트

<br>

## 기술 스택

- 언어
  - Kotlin
  - open jdk 11
- 프레임워크
  - Spring boot 2.7
- 데이터베이스
  - Mysql:5.7
- 컨테이너 툴
  - Docker
  
<br>

## 아키텍처

- 클린 아키텍처
- 도메인 주도 설계 

### 패키지 구성
- 도메인 바운디드 컨텍스트
  - User
  - Account
- 공통 바운디드 컨텍스트
  - Common
  - 각 컨텍스트에서 공유

<br>

## 실행

> docker-compose up

<br>

## 코드 구성

### Api Controller

- 책임에 맞게 작은 Controller 로 나눔.
  - 테스트 작성에 용이
  - 유지 보수에 용이
  - 책임에 맞는 확장성 있는 객체
    - 네이밍에 신중

### Application Service

- 요구사항에 맞춘 Use Case 들을 Interface 로 정의
  - 확장성을 띄기 위해 presentation 과의 느슨한 결합
  - 인터페이스의 구현체는 service 패키지에 위치
- 도메인 모델이 자체적으로 수행하지 못하는 행위
  - 가계 목록 접근 행위
  - @Component Bean 객체를 정의하여 domain service 로서 활용


### Persistence

- persistence 계층은 JPA 를 사용
  - jpa repository 를 persistence 패키지에 위치
- Domain 모델은 JPA entity 를 사용
  - domain 패키지에 위치
  - 필요한 vo 객체도 동일 패키지에 위치
  - Domain entity 이므로 행위를 가진다.

### Infra
- 외부 api, library 관련 config
- 애플리케이션 내의 config 

<br>

## 상세 코드 구성

### DTO 모델
- presentation 에서 공유한다.
- request & response 의 책임
- use case interface 로서 추상화 했기 때문에, dto 객체도 동일하게 interface 로 정의

```kotlin
sealed interface UserResponse

data class JoinCompletionDto(
    val accessToken: String,
    val refreshTokenCookieValue: String,
) : UserResponse

data class SignOutDto(
    val accessCookie: ResponseCookie
) : UserResponse

```

```kotlin
interface UserJoinUseCase {

    fun signUpUser(userRequest: UserRequest) : UserResponse

}
```

- DTO 파라미터도 동일하게 interface 로 추상화
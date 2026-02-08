# 내일배움캠프 - Spring 입문 주차 챕터1

---

## 1. Gradle

> 빌드 자동화 도구로, Java 코드를 실행 가능한 JAR 파일로 만들어준다.

### 1.1 Gradle이란?

- **빌드 자동화 시스템**으로, 작성한 Java 코드를 설정에 맞게 자동으로 Build해 준다.
- **Build**: 소스 코드를 실행 가능한 결과물로 만드는 일련의 과정
- Gradle을 사용하면 Java 소스 코드를 간편하게 실행 가능한 **JAR 파일**로 만들 수 있다.

### 1.2 build.gradle

- Gradle 기반의 **빌드 스크립트** 파일
- 소스 코드 빌드 및 라이브러리 의존성 관리를 담당한다.
- **Groovy** 혹은 **Kotlin** 언어로 스크립트를 작성할 수 있다.

### 1.3 의존성 관리와 라이브러리

- **라이브러리**: 필요한 기능들이 모여있는 코드의 묶음
  - 개발자는 모든 기능을 직접 구현하지 않고, 미리 작성된 라이브러리를 사용하여 빠르게 구현할 수 있다.
- `dependencies` 블록에 외부 라이브러리를 작성하면, Gradle이 **Maven Repository** 등 외부 저장소에서 자동으로 다운로드한다.
  - **Maven Repository**: 라이브러리들을 모아둔 저장소
- 라이브러리 간 의존성도 자동으로 관리해 주므로 충돌 걱정 없이 개발에 집중할 수 있다.

---

## 2. Jackson

> JSON 데이터 구조를 처리해주는 라이브러리

### 2.1 Jackson이란?

- **Object → JSON String**, **JSON String → Object** 양방향 변환을 지원한다.
- Spring 3.0 이후로 Jackson 관련 API를 제공하여 JSON 데이터를 자동 처리해 준다.
- SpringBoot의 `starter-web`에서 기본(default)으로 Jackson 라이브러리를 포함하고 있다.
- 직접 JSON 데이터를 처리해야 할 때는 `ObjectMapper`를 사용한다.

### 2.2 Object → JSON (직렬화)

- `objectMapper.writeValueAsString(object)` 메서드 사용
- **요구 조건**: 변환 대상 Object에 **getter 메서드**가 필요

```java
@Test
@DisplayName("Object To JSON : get Method 필요")
void test1() throws JsonProcessingException {
    Star star = new Star("Robbie", 95);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(star);

    System.out.println("json = " + json);
}
```

### 2.3 JSON → Object (역직렬화)

- `objectMapper.readValue(jsonString, TargetClass.class)` 메서드 사용
  - 첫 번째 파라미터: JSON 타입의 String
  - 두 번째 파라미터: 변환할 Object의 class 타입
- **요구 조건**: 변환 대상 Object에 **기본 생성자** + **getter 또는 setter 메서드**가 필요

```java
@Test
@DisplayName("JSON To Object : 기본 생성자 & (get OR set) Method 필요")
void test2() throws JsonProcessingException {
    String json = "{\"name\":\"Robbie\",\"age\":95}";

    ObjectMapper objectMapper = new ObjectMapper();

    Star star = objectMapper.readValue(json, Star.class);
    System.out.println("star.getName() = " + star.getName());
}
```

---

## 3. HTTP 요청 데이터 처리

### 3.1 @PathVariable (경로 변수 방식)

> URL 경로에 데이터를 포함하여 전달하는 방식

- **요청 예시**: `GET http://localhost:8080/hello/request/star/Robbie/age/95`
- URL 경로에서 데이터를 받을 위치에 `{변수명}` 중괄호를 사용한다.
- 메서드 파라미터에 `@PathVariable` 애너테이션과 함께 변수명, 변수타입을 선언하면 데이터를 받아올 수 있다.

```java
@GetMapping("/star/{name}/age/{age}")
@ResponseBody
public String helloRequestPath(@PathVariable String name, @PathVariable int age) {
    return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
}
```

### 3.2 @RequestParam (쿼리 스트링 방식)

> URL 경로 마지막에 `?`와 `&`를 사용하여 데이터를 전달하는 방식

- **요청 예시**: `GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95`
- 메서드 파라미터에 `@RequestParam` 애너테이션과 함께 key 이름에 맞는 변수명, 변수타입을 선언하면 데이터를 받아올 수 있다.

```java
@GetMapping("/form/param")
@ResponseBody
public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
    return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
}
```

### 3.3 Form 태그 POST 방식

> HTML form 태그를 사용하여 POST 방식으로 HTTP 요청을 보내는 방식

- 데이터는 HTTP Body에 `name=Robbie&age=95` 형태(`application/x-www-form-urlencoded`)로 담겨 전달된다.

```html
<form method="POST" action="/hello/request/form/model">
  <div>이름: <input name="name" type="text"></div>
  <div>나이: <input name="age" type="text"></div>
  <button>전송</button>
</form>
```

```java
@PostMapping("/form/param")
@ResponseBody
public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
    return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
}
```

### 3.4 required 옵션

- `@RequestParam(required = false)` — 해당 값이 전달되지 않아도 오류가 발생하지 않는다.
- `@PathVariable(required = false)` — 동일한 옵션이 존재한다.
- 값을 전달받지 못한 변수는 **null**로 초기화된다.
- `@RequestParam`은 애너테이션 자체를 **생략 가능**하다.

### 3.5 @ModelAttribute (객체로 바인딩)

> QueryString 또는 Form 데이터를 객체로 바인딩하는 방식

- 해당 객체에 **Setter** 혹은 **오버로딩된 생성자**가 필요하다.
- `@ModelAttribute`는 생략 가능하다.
  - **SimpleValueType** → `@RequestParam`으로 판단
  - **객체 타입** → `@ModelAttribute`가 생략된 것으로 판단

### 3.6 @RequestBody (JSON 요청 본문)

> JSON 형식으로 데이터가 넘어올 때 사용하는 방식

- Jackson 라이브러리를 바탕으로 JSON 데이터를 Object로 변환하여 처리한다.

---

## 전체 예제 코드 (RequestController)

```java
package com.sparta.springmvc.request;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/hello/request")
public class RequestController {

    @GetMapping("/form/html")
    public String helloForm() {
        return "hello-request-form";
    }

    // GET http://localhost:8080/hello/request/star/Robbie/age/95
    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody
    public String helloRequestPath(@PathVariable String name, @PathVariable int age) {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    }

    // GET http://localhost:8080/hello/request/form/param?name=Robbie&age=95
    @GetMapping("/form/param")
    @ResponseBody
    public String helloGetRequestParam(@RequestParam(required = false) String name, int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }

    // POST http://localhost:8080/hello/request/form/param
    // Content-Type: application/x-www-form-urlencoded
    // Body: name=Robbie&age=95
    @PostMapping("/form/param")
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }
}
```

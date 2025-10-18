# Chapter3. 네이티브(Natives)

가장 많이 쓰는 네이티브들이다.

- String()
- Number()
- Boolean()
- Array()
- Object()
- Function()
- RegExp()
- Date()
- Error()
- Symbol()

> 자바스크립트는 원시 값을 감싸는 객체 래퍼, 즉 **네이티브 (String, Number, Boolean 등)를 제공**한다. 객체 래퍼에는 타입별로 쓸 만한 기능이 구현되어 있어 편리하게 사용할 수 있다. (예: `String#trim()`, `Array#concat()` 등)

> “abc” 같은 단순 스칼라 원시 값이 있을 때, 이 값의 length **프로퍼티**나 String.prototype에 정의된 **메서드를 호출**하면 자바스크립트는 자동으로 원시 값을 **‘박싱’**(해당되는 객체 래퍼로 감싼다)하여 필요한 프로퍼티와 메소드를 쓸 수 있게 도와준다.

- 네이티브는 생성자처럼 사용할 수 있지만 실제로 생성되는 결과물은 예상과는 다를 수 있다.

```jsx
var a = new String("abc");

typeof a; // "object" ... "String"이 아니다.

a instanceof String; // true

Object.prototype.toString.call(a); // "[object String]"
```

- 생성자의 결과는 원시 값 “abc”를 감싼 객체 래퍼
- typeof 연산자로 이 객체의 타입을 확인해보면 자신이 감싼 원시 값의 타입이 아닌 object의 하위 타입에 가까움

```jsx
console.log(a);
```

- 위 코드의 실행 결과는 브라우저마다 다름. 개발자 콘솔 창에 어떻게 직렬화하여 보여주는 편이 좋을지는 브라우저 개발자가 임의로 결정했으므로

## 3.1 내부 [[Class]]

> typeof 가 ‘object’인 값(배열 등)에는 `[[Class]]`라는 **내부 프로퍼티**(전통적인 클래스 지향(Class-oriented) 개념에서의 클래스라기보단 내부 분류법; Classification의 일부라고 보자)가 추가로 붙는다.
> 이 프로퍼티는 직접 접근할 수 없고, `Object.prototype.toString()` 라는 메소드에 값을 넣어 호출함으로써 존재 확인 가능하다.

```jsx
Object.prototype.toString.call([1, 2, 3]);
// "[object Array]"
Object.prototype.toString.call(/regex-literal/i);
// "[object RegExp]"
```

- 내부 `[[Class]]` 값이, 배열은 “Array”, 정규식은 “RegExp” 임을 알 수 있다.

```jsx
Object.prototype.toString.call(null);
// "[object Null]"
Object.prototype.toString.call(undefined);
// "[object Undefined]"

// Boxing process to Primitives
Object.prototype.toString.call("abc");
// "[object String]"

Object.prototype.toString.call(42);
// "[object Number]"

Object.prototype.toString.call(true);
// "[object Boolean]"
```

- ES6 이후 변경점
  🔍 비교 요약

| 항목                    | ES5 이전                 | ES6 이후                                 |
| ----------------------- | ------------------------ | ---------------------------------------- |
| 타입 판별 방식          | `[[Class]]` 기반         | `Symbol.toStringTag`로 커스터마이징 가능 |
| `toString()` 오버라이딩 | 프로토타입에서 수동 설정 | 클래스 내부에서 직접 정의 가능           |
| 타입 식별 유연성        | 고정된 문자열 반환       | 개발자가 원하는 타입 문자열 반환 가능    |

## 3.2 래퍼 박싱하기

- 원시 값엔 프로퍼티나 메소드가 없으므로 `.length` , `.toString()` 으로 접근하려면 원시 값을 객체 래퍼로 감싸줘야 함.
- 자바스크립트는 원시 값을 알아서 박싱(래핑)한다.

```jsx
var a = "abc";
a.length; // 3;
a.toUpperCase(); // "ABC"
```

- 오래전부터 브라우저는 알아서 박싱하는 흔한 경우를 스스로 최적화하기 때문에, 개발자가 직접 객체 형태로 ‘선 최적화(Pre-Optimize)하면 프로그램이 더 느려질 수 있다.

### 3.2.1 객체 래퍼의 함정

- false를 객체 래퍼로 감싸면 객체가 truthy 이기 때문에 if로 확인할 경우 true이다.
- 수동으로 원시 값을 박싱하려면 Object() 함수를 이용하자 (앞에 new 키워드는 없다)

## 3.3 언박싱

- 객체 래퍼의 원시 값은 `valueOf()` 메소드로 추출한다.

```jsx
var a = new String("abc");
var b = new String(42);
var c = new Boolean(true);
a.valueOf(); // "abc"
b.valueOf(); // 42
c.valueOf(); // true
```

- 암시적인 언박싱이 일어난다.

```jsx
var a = new String("abc");
var b = a + ""; // 'b'에는 암시적으로 언박싱된 원시 값 "abc"이 대입된다.

typeof a; // "object"
typeof b; // "string"
```

## 3.4 네이티브, 나는 생성자다

- 배열, 객체, 함수, 정규식 값은 리터럴 형태로 생성하는 것이 일반적이지만, 리터럴은 생성자 형식으로 만든 것과 동일한 종류의 객체를 생성한다(즉, 래핑되지 않은 값은 없다)
- 확실히 필요해서 쓰는 게 아니라면, 생성자는 가급적 쓰지 않는 편이 좋다.

### 3.4.1 Array()

- 웬만하면 생성자로 배열 객체를 만들지 말자
  - 배열 생성자에는 배열의 크기를 미리 정하는 기능이 있다.
  ```jsx
  var a = new Array(3);
  a.length; // 3
  a;
  ```

### 3.4.2 Object(), Function(), and RegExp()

- 이들의 생성자도 선택 사항이다. (어떤 분명한 의도가 아니라면 사용하지 않는 편이 좋다.)

### 3.4.3 Date() and Error()

- 네이티브 생성자 Date()와 Error()는 리터럴 형식이 없으므로 다른 네이티브에 비해 유용하다.
- error 객체의 주 용도는 현재의 실행 스택 콘텍스트를 포착하여 객체(자바스크립트 엔진 대부분이 읽기 전용 프로퍼티인 `.stack` 으로 접근 가능하다)에 담는 것이다. 이 실행 스택 콘텍스트는 함수 호출 스택, error 객체가 만들어진 줄 번호 등 디버깅에 도움이 될 만한 정보들을 담고 있다.
- error 객체는 보통 throw 연산자와 함께 사용한다.

```jsx
function foo(x) {
  if (!x) {
    throw new Error("x를 안 주셨어요!");
  }
  // ...
}
```

- Error 객체 인스턴스에는 적어도 message 프로퍼티는 들어 있고, type 등 다른 프로퍼티(읽기 전용)가 들어 있을 때도 있다.
- 사람이 읽기 편한 포맷으로 에러 메시지를 보려면 stack 프로퍼티 대신, 그냥 error 객체의 toString() 을 호출하는 것이 가장 좋다.

### 3.4.4 Symbol()

- 심볼을 직접 정의하려면 Symbol() 네이티브를 사용한다. Symbol()은 앞에 new 를 붙이면 에러가 나는, 유일한 네이티브 ‘생성자’다.

### 3.4.5 네이티브 프로토타입

- 내장 네이티브 생성자는 각자의 `.prototype` 객체를 가진다. (ex. `Array.prototype`, `String.prototype` 등)
  - `.prototype` 객체에는 해당 객체의 하위 타입별로 고유한 로직이 담겨 있다.
- 문자열 원시 값을 (박싱으로) 확장한 것까지 포함하여 모든 `String` 객체는 기본적으로 `String.prototype` 객체에 정의된 메소드에 접근할 수 있다.
- 프로토타입 위임(Delegation) 덕분에 모든 문자열이 이 메서드들을 같이 쓸 수 있다.
  여기서 “위임”이라는 표현은:
  - 객체가 직접 메서드를 갖고 있지 않아도
  - **자기 프로토타입에게 책임을 넘겨서**
  - 마치 자기 것이냥 메서드를 사용할 수 있다는 걸 의미한다.
    즉, “위임”은 단순한 편의가 아니라 **자바스크립트의 동작 원리**를 설명하는 핵심 용어
    🧠 비유로 이해해볼까?
  - `str.toUpperCase()`는 마치 “내가 직접 못하지만, 내 부모가 할 수 있으니까 대신 해줘”라고 말하는 것. 이게 **위임(delegation)**. 직접 가진 게 아니라 **빌려 쓰는 것**.
  - 자바스크립트는 프로토타입 기반 언어로 객체가 어떤 메서드를 호출할 때, 그 객체에 해당 메서드가 없으면 **자기 프로토타입 체인을 따라 올라가면서 메서드를 찾는 구조**
- 모든 함수는 `Function.prototype` 에 정의된 `apply(), call(), bind()` 메소드를 사용할 수 있다.

**_프로토타입은 디폴트다_**

- 변수에 적절한 타입의 값이 할당되지 않은 상태에서 `Function.prototype` → 빈 함수, `RegExp.prototype` → ‘빈(아무것도 매칭하지 않은)’ 정규식, `Array.prototype` → 빈 베열

## 3.5 정리하기

- 자바스크립트는 원시 값을 감싸는 객체 래퍼, 즉 네이티브를 제공한다.
- 객체 래퍼에는 타입별로 쓸 만한 기능이 구현되어 있어 편리하게 사용할 수 있다.
- 단순 스칼라 값이 있을 때, 이 값의 length 프로퍼티나 String.prototype에 정의된 메소드를 호출하면 자바스크립트는 원시 값을 ‘박싱’(해당되는 객체 래퍼로 감싼다)하여 필요한 프로퍼티와 메소드를 쓸 수 있게 도와준다.

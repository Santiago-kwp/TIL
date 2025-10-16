## 자바스크립트 함수



1 다음 중 함수를 정의하는 올바른 방법은?

A) function = myFunc() {}

B) function myFunc() {}

C) def myFunc() {}

D) var function myFunc() {}

ans : `B)`

---

2 함수를 호출(call)한다는 것은?

A) 함수를 복사한다

B) 함수를 삭제한다

C) 함수를 실행한다

D) 함수를 주석 처리한다

ans : `C)`

---

3 다음 중 함수의 반환값(return value)을 올바르게 사용하는 것은?

A) return x + y;

B) print(x + y);

C) echo(x + y);

D) output(x + y);

ans : `A)`

---

4 다음 중 함수의 매개변수(parameter)에 대한 설명으로 옳은 것은?

A) 함수 외부에서만 사용 가능하다.

B) 함수 내부에서 전달받은 값을 저장하는 변수이다.

C) 항상 숫자여야 한다.

D) return 문 뒤에서 선언해야 한다.

ans :  `B)`

---

5 다음 중 기본적인 함수 호출 형태는?

A) function()myFunc;

B) myFunc[];

C) myFunc();

D) function myFunc;

ans : `C)`

---

6 다음 중 함수의 실행이 종료되는 즉시 반환되는 키워드는?

A) stop

B) return

C) end

D) break

ans: `B)`

---

7 함수에서 반환값이 없을 때 반환되는 기본 값은?

A) null

B) 0

C) undefined

D) false

ans : `C)`

---

8 다음 중 익명 함수(Anonymous Function)를 정의한 것은?

A) function() { console.log("Hello"); }

B) function sayHello() { console.log("Hi"); }

C) var say = function sayHello() {};

D) function = hello() {};

ans : `A)`

---

9 함수 선언식(Declaration)과 함수 표현식(Expression)의 차이는?

A) 선언식은 호이스팅(hoisting)된다.

B) 표현식은 선언 전에 사용 가능하다.

C) 둘 다 동일하게 동작한다.

D) 표현식은 `return`이 불가능하다.

ans : `A)`

---

10  아래 함수의 결과는?

```jsx
function add(a, b){ return a + b; }
console.log(add(2, 3));
```

A) 23

B) 5

C) "2+3"

D) undefined

ans : `B)`

---

11 다음 코드의 출력값은?

```jsx
function greet(name){ return "Hi " + name; }
console.log(greet("Yumi"));
```

A) Hi + name

B) "Hi name"

C) Hi Yumi

D) undefined

ans : `C)`

---

12 다음 중 기본 매개변수를 사용하는 올바른 예시는?

A) function f(a=10, b){}

B) function f(a,b=10){}

C) function f(a,b==10){}

D) function f(a,b=10:){}

ans : `B)`

why : `A)` 의 경우 JavaScript에서는 **기본값이 있는 매개변수 뒤에 기본값 없는 매개변수를 둘 수 없음**

---

13 함수 내부에서 선언된 변수는 어떤 범위를 가진다?

A) 전역 범위

B) 지역 범위

C) 파일 전체

D) 함수 외부

ans : `B)`

---

14 다음 중 전역변수(global variable)의 올바른 사용은?

A) 함수 안에서만 사용된다.

B) 함수 외부에서 선언되어 모든 함수가 접근 가능하다.

C) return 뒤에서만 가능하다.

D) 블록 안에서만 유효하다.

ans : `B)`

---

15 아래 코드의 결과는?

```jsx
let x = 10;
function test(){
  let x = 5;
  console.log(x);
}
test();
```

A) 10

B) 5

C) undefined

D) 오류 발생

ans : `B)`

---

16 아래 코드의 결과는?

```jsx
function square(x){ return x*x; }
console.log(square());
```

A) NaN

B) undefined

C) 0

D) Error

ans: `A)`

---

17 화살표 함수(Arrow Function)를 정의한 올바른 예시는?

A) const add = (a,b) => a + b;

B) function add(a,b) => a + b;

C) add = (a,b) { return a + b; }

D) const add(a,b) -> { return a+b }

ans : `A)`

---

18 아래 코드의 결과는?

```jsx
const multiply = (a,b=2) => a*b;
console.log(multiply(5));
```

A) 10

B) 7

C) 5

D) Error

ans : `A)`

---

19 아래 중 함수 표현식에 대한 설명으로 올바른 것은?

A) 반드시 이름이 있어야 한다.

B) 선언 전에 호출해도 된다.

C) 변수에 할당할 수 있다.

D) return문을 쓸 수 없다.

ans : `C)`

---

20 아래 코드의 출력은?

```jsx
var say = function(){ return "Hello"; }
console.log(say()); 

```

A) Hello

B) undefined

C) say

D) Error

ans : `A)`

---

21 콜백 함수(Callback Function)란?

A) 호출되지 않는 함수

B) 함수의 인수로 전달되는 함수

C) 반환되는 함수

D) 전역함수

ans : `B)`

---

22 다음 중 콜백함수 사용의 예시는?

```jsx
function run(fn){ fn(); }
run( ? ) 

```

A) fn(){}

B) run();

C) function() { console.log("done"); }

D) call fn

ans: `C)`

---

23 아래 코드의 결과는?

```jsx
function outer(){
  function inner(){
    return "inside";
  }
  return inner();
}
console.log(outer());
```

A) outer

B) inner

C) inside

D) undefined

ans : `C)`

---

24 다음 중 즉시 실행 함수(IIFE)의 올바른 예시는?

A) function test()();

B) function(){}();

C) (function(){ console.log("run"); })();

D) run(){};

ans : `C)`

---

25 아래 코드의 출력은?

```jsx
(function(a,b){
  console.log(a+b);
})(3,4); 
```

A) 7

B) 34

C) undefined

D) Error

ans : `A)`

---

26 아래 코드의 결과는?

```jsx
function makeAdder(x){
  return function(y){
    return x+y;
  }
}
const add5 = makeAdder(5);
console.log(add5(2)); 
```

A) 2

B) 5

C) 7

D) undefined

ans : `D)`

---

27 클로저(Closure)의 특징은?

A) 함수 외부 변수를 수정할 수 있다.

B) 함수 내부의 변수를 외부에서 직접 접근할 수 있다.

C) 외부 함수의 변수를 기억하는 함수이다.

D) return이 없는 함수이다.

ans : `C)`

---

28 아래 코드의 출력은?

```jsx
function counter(){
  let num = 0;
  return function(){
    num++;
    return num;
  }
}
const c = counter();
console.log(c());
console.log(c());
```

A) 1,1

B) 0,1

C) 1,2

D) 2,3

ans : `C)`

---

29 다음 중 함수가 일급 객체(First-Class Object)라는 뜻이 아닌 것은?

A) 변수에 저장 가능

B) 매개변수로 전달 가능

C) 반환값으로 사용 가능

D) 반드시 이름을 가져야 함

ans : `D)`

---

30 setTimeout() 함수에 전달되는 첫 번째 인수는?

A) 시간

B) 함수

C) 문자열

D) 숫자

ans : `B)`

---

31 아래 코드의 출력은?

```jsx
function sayHi(){
  console.log(this);
}
sayHi(); 
```

A) 전역 객체(window)

B) undefined

C) null

D) 빈 객체

ans : `A)`

why : `this`  는 호출 방식에 따라 결정됨. 단독 호출 → 전역 객체 (브라우저 환경에서는 `window` 객체, Node.js 환경에서는 `global` 객체)

`obj.sayHi()` 처럼 객체의 메소드로 호출 → `this` 는 그 객체

---

32 화살표 함수에서 this는?

A) 새로운 this를 바인딩한다

B) 상위 스코프의 this를 사용한다

C) 항상 undefined이다

D) 전역 객체를 가리킨다

ans : `B)`

why : 화살표 함수에서의 `this` 는 자신만의 this를 가지지 않고, 정적으로 상위 스코프에서 고정됨.

---

33 map() 함수의 인수는?

A) 콜백 함수

B) 객체

C) 숫자

D) 배열

ans : `A)`

34  filter() 함수의 반환값은?

A) 조건을 만족하는 요소만 모은 새 배열

B) true/false

C) 객체

D) 문자열

ans : `A)`

---

35 아래 코드의 출력은?

```jsx
[1,2,3].map(x => x*2);
```

A) [2,4,6]

B) [1,2,3]

C) 6

D) undefined

ans: `A)`

---

36 아래 코드의 출력은?

```jsx
[1,2,3,4].filter(n => n%2===0);
```

A) [1,3]

B) [2,4]

C) [0,2,4]

D) [1,2,3,4]

ans : `B)`

---

37 아래 코드의 결과는?

```jsx
[1,2,3].reduce((acc,cur)=>acc+cur,0);
```

A) 6

B) [1,2,3]

C) undefined

D) Error

ans: `A)`

---

38 아래 코드의 출력은?

```jsx
const f = () => ({name:"Yumi"});
console.log(f());
```

A) undefined

B) {name:"Yumi"}

C) name:"Yumi"

D) Error

ans : `B)`

---

39 아래 코드의 출력은?

```jsx
const greet = (name="Guest") => "Hello " + name;
console.log(greet()); 
```

A) Hello Guest

B) Hello

C) undefined

D) Error

ans : `A)`

---

40 함수 안에서 return문이 없을 때 결과는?

A) null

B) undefined

C) 0

D) Error

ans : `B)`
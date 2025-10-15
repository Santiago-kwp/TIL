## 변수

**1.** 다음 중 재할당이 불가능한 선언은?

A) `var a = 1` B) `let a = 1` C) `const a = 1`

ans : `C`

**2.** 아래 코드의 출력결과는?

```jsx
console.log(x);
var x = 10;
```

ans : `undefined`

**3.** 아래 코드에서 에러가 나는 줄은? 이유는?

```jsx
1 console.log(y);
2 let y = 3;
```

ans : `1`번줄 ⇒ `Uncaught Referrence Error: y is not defined` : y가 아직 정의되지 않음. 인터프리터 방식으로 한줄씩 기계어로 번역하므로 2번줄에서 정의하여도 1번줄에서 에러가 남.

**4.** `const obj = { n: 1 };`

    obj 객체의 속성 n 의 값을 2로 변경하세요.

ans : `obj.n = 2`

**5.** 다음 중 올바른 식별자(변수명)가 아닌 것은?

A) `_count` B) `$value` C) `2nd` D) `camelCase`

ans : `C` (숫자로 시작할 수 없음)

**6.** 다음 코드의 결과?

```jsx
let a = 1;
a = a + 2;
console.log(a);
```

ans : `3`

---

# 문자열

**7.** 템플릿 리터럴로 “Hello, JS!”를 만들 올바른 코드는?

A) `'Hello, ${"JS"}!'` B) `Hello, ${"JS"}!` C) `"Hello, ${"JS"}!"`

ans : `B`

**8.** `"abc".length` 값은?

ans : `3`

**9.** `"Hello".toLowerCase()` 결과는?

ans : `‘hello’`

**10.** `"  hi  ".trim()` 결과는?

ans : `‘hi’`

**11.** `"a,b,c".split(",")` 결과는?

ans : `[‘a’, ‘b’, ‘c’]` (Array)

**12.** `"abc".includes("b")` 는 `true`?

ans : `true`

**13.** `"cat".replace("c","b")` 결과는?

ans : `‘bat’`

---

# 조건문

**14.** 아래 출력결과는?

```jsx
console.log(2 == '2', 2 === '2');
```

ans : `true false`

> `==` : 동등 연산자로 두 피연산자를 비교하기 위해 [느슨한 같음](https://developer.mozilla.org/ko/docs/Web/JavaScript/Guide/Equality_comparisons_and_sameness#loose_equality_using)(equal)을 사용함. (피연산자의 타입 숫자와 문자열이 경우 → [문자열을 숫자로 변환합니다.](https://developer.mozilla.org/ko/docs/Web/JavaScript/Reference/Global_Objects/Number#number_coercion))
> `===` : 일치 연산자(`===`; strict equal)로 동등 연산자와의 가장 두드러지는 차이점은 일치 연산자는 타입 변환을 시도하지 않는다는 것입니다.

**15.** 삼항연산자 결과는?

```jsx
const n = 5;
const r = n % 2 === 0 ? 'even' : 'odd';
```

ans : `console.log(r)` ⇒ `odd`

**16.** `switch` 기본형에서 일치 비교에 쓰이는 것은?

A) `==` B) `===` C) 둘 다

ans : `B` (일치연산자를 통한 엄격한 동등비교)

**17.** `&&` 단축 평가 결과:

```jsx
const ok = true && 'DONE';
```

ans : `console.log(ok)` ⇒ `DONE`
why: `&&` 연산자는 왼쪽에서 오른쪽으로 평가할 때 처음으로 만나는 [거짓 같은](https://developer.mozilla.org/ko/docs/Glossary/Falsy) 피연산자의 값을 반환합니다. 혹은 모두 [참 같은 값](https://developer.mozilla.org/ko/docs/Glossary/Truthy)이라면 마지막 피연산자의 값을 반환한다. 또한 Boolean 타입에서 빈 문자열만 `false` 이고 나머지는 `true` 이다. (The empty string `""` turns into `false`; other strings turn into `true`)

---

# Object

**18.** 속성 접근 결과?

```jsx
const user = { name: 'Ann', age: 17 };
console.log(user['name']);
```

ans : `Ann`

**19.** 키를 추가한 다음 코드의 결과는?

```jsx
const k = 'score';
const obj = {};
obj[k] = 100;
console.log(obj.score);
```

ans : `100`

**20.** 다음의 출력결과는?

```jsx
const a = { n: 1 };
const b = { n: 1 };
console.log(a === b);
```

---

ans : `false`

why : 두 피연산자가 모두 객체이면, 두 객체가 동일한 객체를 참조할 경우에만 `true`를 반환합니다.

# Array

**21.** `const arr = [1,2,3]; arr.push(4);` 이후 `arr`는?

ans : `arr = [1, 2, 3, 4]`

**22.** `pop()`이 반환하는 것은?

ans : `4`

**23.** `slice(1,3)`의 의미와 arr에 적용한 결과는?

ans : 원본 배열의 인덱스 1번부터 2번까지의 배열의 원소를 잘라서 새로운 배열로 반환하며, 원본 배열은 그대로 둔다. ⇒ `arr.slice(1,3)` ⇒ `[2,3]` 반환되며, `arr`의 배열은 변화 없음.

**24.** `splice(1,2)`의 의미와 arr에 적용한 결과는?

ans : 원본 배열의 인덱스 1번부터 시작하여 2개의 원소 즉, 1번과 2번 배열의 원소를 잘라서 삭제함. ⇒ 원본 배열의 변경이 일어남 ⇒ `arr.splice(1,2)` ⇒ `[2,3]` 이 반환 뒤 원본 배열에서 삭제되어 `arr = [1]` 으로 변경됨.

**25.** `map` 결과?

```jsx
[1, 2, 3].map((x) => x * 2);
```

ans : `[2, 4, 6]` 배열 반환

**26.** `filter` 결과?

```jsx
[1, 2, 3, 4].filter((x) => x % 2 === 0);
```

ans : `[2, 4]` 배열 반환

**27.** `includes` 사용한 결과는?

```jsx
[1, 2, 3].includes(2);
```

ans : `true`

**28.** `reduce` 결과는?

```jsx
[1, 2, 3].reduce((acc, cur) => acc + cur, 0);
```

ans : `6`

why : `reduce` 의 기본 문법 : `array.reduce(callback, initialValue)`

- `callback`: 누적 함수 `(accumulator, currentValue) => ...`
- `initialValue`: 누적 시작값 (선택적, 없으면 배열의 첫 번째 요소가 사용됨)
- 원소가 3개 이므로 초기 acc : 0 → 0번 인덱스의 원소를 합하여 acc : 1 → 1번 인덱스의 원소를 합하여 acc : 3 → 2번 인덱스의 원소를 합하여 acc : 6

# Loop

**29.** `for (let i=0; i<3; i++) console.log(i);` 출력?

ans :

```jsx
0;
1;
2;
```

**30.** `for...of`는 어떤 것을 순회할때 효과적인가?

ans : iterable (배열, 문자열, Map, Set 등)

**31.** `for...in`은 주로 무엇을 순회할때 효과적인가?

ans : 객체(object)의 열거 가능한 속성(key)

**32.** 다음 코드의 마지막 `sum의 결과값은`?

```jsx
let i = 1,
  sum = 0;
while (i <= 3) {
  sum += i;
  i++;
}
```

ans : `6`

**33.** `for...of` 출력 결과는?

```jsx
for (const ch of 'Hi') console.log(ch);
```

ans :

```jsx
H;
i;
```

**34.** 빈칸에 들어갈 키워드는 무었인가?

```jsx
const arr = [10, 20, 30];
for (const num ___ arr) {
  console.log(num);
}

```

ans : `of`

1. 아래 colors 배열의 요소값을 순서대로 출력하는 반복문을작성하세요

```jsx
const colors = ['red', 'green', 'blue'];
```

ans:

```jsx
colors.forEach((color) => {
  console.log(color);
});
```

1. 아래 str의 요소를 순회하여 출력하는 반복문을 작성하세요.

```jsx
const str = 'JS';
```

ans:

```jsx
for (const ch of str) {
  console.log(ch);
}
```

37. 배열 scores의 점수에 대한 총점과 평균을 구하여 출력하는 코드를 작성하세요.

```jsx
const scores = [90, 80, 70];
```

ans:

```jsx
// 총점과 평균
const total = scores.reduce((acc, cur) => acc + cur, 0);
const avg = total / scores.length;
console.log(`total : ${total} and avg : ${avg}`);
```

---

1. 배열 nums 의 요소값 출력을 2번째 인덱스의 값까지만 출력하는 코드를 작성하세요

```jsx
const nums = [1, 2, 3];
```

ans :

```jsx
for (let i = 0; i < nums.length; i++) {
  if (i == 2) break;
  console.log(nums[i]);
}

// forEach 방법
nums.forEach((num, index) => {
  if (index <= 1) {
    console.log(num);
  }
});
```

39. user 객체의 정보를 출력하는 반복문을 작성하세요.

```jsx
const user = { name: 'Yumi', age: 20 };
```

ans:

```jsx
for (const key in user) {
  console.log(`key : ${key}, value : ${user[key]}`);
}
```

40. arr 배열의 요소값을 forEach문을 이용하여 출력하세요.

```jsx
const arr = [1, 2, 3];
```

ans :

```jsx
arr.forEach((num) => {
  console.log(num);
});
```

**41.** `Map 이란`?

ans: javascript의 표준 내장 객체로, 키와 값을 한 쌍으로 가지며, 키의 원래 삽입 순서를 기억합니다. 모든 값(객체 및 [원시 값](https://developer.mozilla.org/ko/docs/Glossary/Primitive) 모두)은 키 또는 값으로 사용될 수 있고, iterable이다.

**42.** `Set`이란?

ans : javascript의 표준 내장 객체로, 원소의 중복을 허용하지 않는다. 모든 값은 원소로 포함될 수 있고, Set의 요소를 삽입 순서대로 순회할 수 있는 iterable이다.

**43.** 아래 `Map의 결과는?`

```jsx
const m = new Map();
const k = {};
m.set(k, 123);
console.log(m.get(k));
```

ans : `123`

**44.** 아래`Set의 결과는?`

```jsx
const s = new Set([1, 2, 2, 3]);
s.add(3);
console.log(s.size);
```

ans : `3`

why : 중복을 허용하지 않으므로 `s` 에는 `[1,2,3]` 만 원소로 포함됨.

**45.** `Map` 크기 확인 프로퍼티는?

ans : `size`

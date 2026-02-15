# 3. 파이썬 둘러보기

## 3.1 파이썬 자료 구조

### 3.1.1 상수와 리터럴

| 리터럴 종류     | 설명                              | 예시                                     |
| --------------- | --------------------------------- | ---------------------------------------- |
| 정수 리터럴     | 10진수, 2진수, 8진수, 16진수 정수 | `10`, `0b1010`, `0o12`, `0xA`            |
| 실수 리터럴     | 부동소수점 숫자                   | `3.14`, `2.5e-3`, `1.0`                  |
| 복소수 리터럴   | 실수부와 허수부를 가진 숫자       | `3+4j`, `1.5+2.5j`                       |
| 문자열 리터럴   | 작은따옴표, 큰따옴표로 표현       | `'hello'`, `"world"`, `'''multi line'''` |
| 불린 리터럴     | 참/거짓 값                        | `True`, `False`                          |
| None 리터럴     | 값이 없음을 나타냄                | `None`                                   |
| 리스트 리터럴   | 순서가 있는 변경 가능한 컬렉션    | `[1, 2, 3]`, `['a', 'b', 'c']`           |
| 튜플 리터럴     | 순서가 있는 변경 불가능한 컬렉션  | `(1, 2, 3)`, `('a', 'b')`                |
| 딕셔너리 리터럴 | 키-값 쌍의 컬렉션                 | `{'name': 'John', 'age': 30}`            |
| 집합 리터럴     | 중복 없는 요소의 컬렉션           | `{1, 2, 3}`, `{'a', 'b', 'c'}`           |

## 3.2 물리적/논리적 명령행

- 물리적 명령행 : 소스 코드에서 눈으로 보이는 한 행
- 논리적 명령행 : 파이썬 인터프리터 관점에서 한 행의 명령 단위를 의미함

## 3.3 연산자

### 3.3.1 기본 연산자와 우선순위

| 연산자                           | 명칭          | 설명                       | 예시 → 결과                                            | 우선순위  |
| -------------------------------- | ------------- | -------------------------- | ------------------------------------------------------ | --------- |
| `()`                             | 괄호          | 우선순위를 명시적으로 지정 | `(2 + 3) * 4` → `20`                                   | 1 (최고)  |
| `**`                             | 거듭제곱      | 지수 연산                  | `2 ** 3` → `8`                                         | 2         |
| `+x`, `-x`, `~x`                 | 단항 연산자   | 양수, 음수, 비트 NOT       | `-5` → `-5`, `~5` → `-6`                               | 3         |
| `*`, `/`, `//`, `%`              | 산술 연산자   | 곱셈, 나눗셈, 몫, 나머지   | `10 / 3` → `3.333...`, `10 // 3` → `3`, `10 % 3` → `1` | 4         |
| `+`, `-`                         | 덧셈, 뺄셈    | 이항 덧셈, 뺄셈            | `5 + 3` → `8`, `5 - 3` → `2`                           | 5         |
| `<<`, `>>`                       | 비트 시프트   | 왼쪽/오른쪽 비트 이동      | `4 << 2` → `16`, `16 >> 2` → `4`                       | 6         |
| `&`                              | 비트 AND      | 비트 단위 AND 연산         | `5 & 3` → `1`                                          | 7         |
| `^`                              | 비트 XOR      | 비트 단위 XOR 연산         | `5 ^ 3` → `6`                                          | 8         |
| `\|`                             | 비트 OR       | 비트 단위 OR 연산          | `5 \| 3` → `7`                                         | 9         |
| `==`, `!=`, `>`, `>=`, `<`, `<=` | 비교 연산자   | 값의 크기 비교             | `5 > 3` → `True`, `5 == 5` → `True`                    | 10        |
| `is`, `is not`                   | 동일성 연산자 | 객체의 동일성 비교         | `x is None` → `True/False`                             | 10        |
| `in`, `not in`                   | 멤버십 연산자 | 포함 여부 확인             | `3 in [1, 2, 3]` → `True`                              | 10        |
| `not`                            | 논리 NOT      | 논리 부정                  | `not True` → `False`                                   | 11        |
| `and`                            | 논리 AND      | 논리곱                     | `True and False` → `False`                             | 12        |
| `or`                             | 논리 OR       | 논리합                     | `True or False` → `True`                               | 13        |
| `=`, `+=`, `-=`, `*=`, `/=` 등   | 대입 연산자   | 값의 할당 및 복합 대입     | `x = 5`, `x += 3` → `x = 8`                            | 14 (최저) |

### 3.3.2 슬라이스(:) 연산자

> 열거형 객체의 일부를 잘라서 가져온다.

`열거형 객체[시작 인덱스:종료 인덱스:인덱스 증가폭]`

자주 사용 : `a[::-1]`

## 3.4 기본 명령문

### 3.4.1 조건문

```python
year = 2020

if (year % 4==0) and (year % 100 != 0): # 4년마다 윤년, 100년마다 윤년 아님
    print(year, "는 윤년입니다.")
elif year % 400==0:
    print(year, "는 윤년입니다.")
else:
    print(year, "는 윤년이 아닙니다.")
```

### 3.4.2 반복문

```python
n=3
while n>=0:
    m = input("Enter a integer: ")
    if int(m)==0: break
    n = n-1
else:
    print('4 inputs.')
```

### 3.4.3 순회하기

#### 1. range()를 사용한 인덱스 기반 순회

```python
numbers = [10, 20, 30, 40, 50]
total = 0

for i in range(len(numbers)):
    total += numbers[i]

print(f"리스트 원소합: {total}")  # 출력: 리스트 원소합: 150
```

#### 2. for ~ in으로 리스트 원소 직접 순회

```python
numbers = [10, 20, 30, 40, 50]
total = 0

for num in numbers:
    total += num

print(f"리스트 원소합: {total}")  # 출력: 리스트 원소합: 150
```

#### 3. enumerate()로 인덱스와 원소 함께 순회

```python
numbers = [10, 20, 30, 40, 50]
total = 0

for index, num in enumerate(numbers):
    total += num
    print(f"인덱스 {index}: {num}, 현재 합계: {total}")

print(f"\n최종 리스트 원소합: {total}")  # 출력: 최종 리스트 원소합: 150
```

## 3.5 함수와 라이브러리

### 3.5.1 함수

도형 넓이 구하기 함수

```python
import math

def calc_area(type, a, b=None, c=None):
    """
    다양한 도형의 넓이를 계산하는 함수

    Args:
        type: 도형 종류 ('rectangle', 'triangle', 'circle')
        a: 첫 번째 매개변수 (직사각형-가로, 삼각형-밑변, 원-반지름)
        b: 두 번째 매개변수 (직사각형-세로, 삼각형-높이)
        c: 세 번째 매개변수 (현재 미사용, 확장 가능)

    Returns:
        float: 계산된 넓이
    """
    if type == 'rectangle':
        if b is None:
            return "직사각형은 가로와 세로가 필요합니다."
        area = a * b
        print(f"직사각형 넓이: {a} × {b} = {area}")
        return area

    elif type == 'triangle':
        if b is None:
            return "삼각형은 밑변과 높이가 필요합니다."
        area = (a * b) / 2
        print(f"삼각형 넓이: ({a} × {b}) / 2 = {area}")
        return area

    elif type == 'circle':
        area = math.pi * (a ** 2)
        print(f"원의 넓이: π × {a}² = {area:.2f}")
        return area

    else:
        return f"지원하지 않는 도형 타입입니다: {type}"


# 사용 예시
print("=== 도형 넓이 계산 ===\n")

# 직사각형 넓이
rect_area = calc_area('rectangle', 5, 3)

# 삼각형 넓이
triangle_area = calc_area('triangle', 6, 4)

# 원의 넓이
circle_area = calc_area('circle', 7)

# 잘못된 타입
calc_area('pentagon', 5)
```

### 3.5.2 모듈(Module), 패키지

> 파이썬에서 모듈이란 함수나 변수 또는 클래스를 모아 놓은 "파일"이다.

### 연습문제

4. 슬라이스 연산자를 사용하는 방법을 자세히 설명하고,
   10개의 원소를 갖는 리스트를 선언하여 8번째 원소에서 2번쨰 원소까지 역순으로 가져오는 방법을 작성하시오.

```python
list(range(1,11))[7:1:-1]
```

5. 넘파이 함수를 이용해서 ndarray 행렬을 선언하는 함수들을 열거하고 각각의 예시를 적고 설명하시오

```python
import numpy as np
list1, list2 = [1, 2, 3], [4, 5, 6]
a, b = np.array(list1), np.array(list2)

c =  np.zeros((2, 3), int)   # 32비트 정수형
d = np.ones((3,4), np.uint8) # 부호없는 8비트 정수형
e = np.empty((1,5), float)   # 값없음 행렬 1행 5열, 64비트 실수
f = np.full(5,15, np.float32)# 원소값 15. 1차원 행렬, 32비트 실수형

```

6. 다차원 행렬을 1차원으로 변경하는 방법을 3가지 이상 예시하고 실행되는 방법을 설명하시오

```python
import numpy as np

np.random.seed(10) # 랜덤 값의 시드 설정
a = np.random.rand(2,3) # 균일분포 난수, 2행 3열 행렬
b = np.random.randn(3,2) # 평균 0, 표준편차 1의 정규분포 난수
c = np.random.rand(6)    # 균일분포 난수 - 1차원 행렬
d = np.random.randint(1, 100, 6) # 1 ~ 100 사이의 정수 난수 1차원 행렬

c = np.reshape(c, (2,3)) # 형태 변경 방법1 - reshape
d = d.reshape(2, -1)    # 형태 변경 방법2 - 두 번쨰 인수로 -1을 입력하면 열수를 자동으로 설정하도록 한다.
# 다만 행 x 열과 전체 원소 개수가 일치하지 않으면 에러가 발생한다.

print('a 형태:', a.shape, '\n', a)

print('다차원 객체 1차원 변환 방법')
print('a =', a.flatten())   # 다차원 ndarray 객체를 1차원 벡터로 변환
print('b =', np.ravel(b))   # 다차원 모든 객체를 1차원 벡터로 변환
print('c =', np.reshape(c, (-1, ))) # numpy의 reshape() 함수 사용
print('d =', d.reshape(-1,))    # ndarray 객체 내장 reshape() 함수 사용

```

8. numpy 행렬 자료형의 종류

- `numpy.int32`
- `numpy.float64`
- `numpy.int64`
- 정수형 (Integer): int8, int16, int32, int64 (부호 있는 정수)
- 부호 없는 정수형 (Unsigned Integer): uint8, uint16, uint32, uint64
- 실수형 (Floating Point): float16, float32, float64 (기본값)
- 복소수형 (Complex): complex64, complex128 (실수부/허수부 쌍으로 구성)
- 논리형 (Boolean): bool (True 또는 False)
- 기타: 문자열(string*, unicode*), 날짜/시간(datetime64) 등

9. 실수형 원소 10개를 갖는 ndarray 행렬을 선언해서 전체 원소의 합과 평균을 구하시오. 합과 평균은 소수점 둘째 자리까지 나타내시오

```python
import numpy as np
nd = np.random.rand(10)
round(nd.mean(), 2)
print("{:.2f}".format(nd.sum()))
```

10. 0 ~ 50 사이의 임의의 원소(정수형, 중복가능)를 500개 만들어서 가장 중복이 많이 나온 원소 3개를 원소값과 중복 횟수로 출력하시오.

```python
import numpy as np

class NumCnt:
   def __init__(self, num):
      self.num = num
      self.cnt = 0

    def addCnt(self):
        self.cnt+=1

    def __repr__(self): return f"NumCnt(num={self.num}, cnt={self.cnt})"

# 난수 생성
a = np.random.randint(0,50, 500)

# 0~50까지 객체 생성
num_objs = [NumCnt(i) for i in range(51)]

# 카운트 업데이트
for val in a:
    num_objs[val].addCnt()

# 가장 많이 나온 3개 찾기
top3 = sorted(num_objs, key=lambda x: x.cnt, reverse=True)[:3]

# 출력
for obj in top3:
    print(f"숫자 {obj.num} : {obj.cnt}회")

```

# 05. 기본 배열 연산(Operations on Arrays) 함수

## 5.1 기본 배열(Array) 처리 함수
> 영상처리 프로그래밍을 하다보면, 카메라에서 사용자의 얼굴을 검출해서 윈도우에 표시해야 한다. 이때, 보통 카메라가 사용자를 향하므로 윈도우에 표시되는 얼굴의 영상은 사용자의 실제 모습과 달리 좌우가 뒤바뀐 영상이 나타난다. 이런 경우 입력영상의 행렬 원소를 좌우로 뒤집도록 해서 문제를 간단히 해결할 수 있다.

`cv2.flip(src, flipCode[, dst])`
- 설명: 입력된 2차원 배열을 수직, 수평, 양축으로 뒤집는다.
`cv2.repeat(src, ny, nx[, dst])`
- 설명: 입력 배열의 반복된 복사본으로 출력 배열을 채운다.
`cv2.transpose(src[, dst])`
- 설명: 입력 행렬의 전치 행렬을 출력으로 반환한다.

<details>
<summary> `[, dst]` 의미 </summary>

예시

```python
import cv2
import numpy as np

img = np.ones((100,100), np.uint8)*255

# dst를 생략하면 새로운 배열 반환
flipped = cv2.flip(img, 1)

# dst를 지정하면 결과가 그 배열에 저장됨
dst = np.empty_like(img)
cv2.flip(img, 1, dst)
print(np.array_equal(flipped, dst))  # True

```

정리
[dst] → 선택적으로 줄 수 있는 인자

생략하면 OpenCV가 알아서 새 배열을 만들어 반환

지정하면 결과가 그 배열에 저장되어 메모리 재사용 가능
👉 즉, 책에서 [dst]라고 쓴 건 “이 인자는 있어도 되고 없어도 된다”는 뜻
</details>

## 5.2 채널 처리 함수
`cv2.merge(mv[, dst])`
- 설명: 여러 개의 단일채널 배열을 다채널 배열로 합성한다.
    - `mv`: 합성될 입력 배열 혹은 벡터, 합성될 단일채널 배열들의 크기와 깊이가 동일해야 함
    - `dst`: 입력 배열과 같은 크기와 같은 깊이의 출력 배열
`cv2.split(m[, mv])`
- 설명: 다채널 배열을 여러 개의 단일채널 배열로 분리한다.
    - `m`: 입력되는 다채널 배열
    - `mv`: 분리되어 반환되는 단일채널 배열들의 벡터
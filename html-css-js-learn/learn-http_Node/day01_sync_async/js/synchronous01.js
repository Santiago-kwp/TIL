function sleep(func, delay) {
  const delayUntill = Date.now() + delay;

  // delay 시간동안 반복문 실행 작업 => 블로킹
  while (Date.now() < delayUntill);

  // 전달한 func 함수 delay 시간 이후 호출
  func();
}

function foo() {
  console.log('foo');
}

function bar() {
  console.log('bar');
}

// 3초 이상 sleep 함수 실행
sleep(foo, 3000);

// bar 함수는 sleep 함수 실행이 종료되어야, 비로소 실행(= 3초 동안 블로킹)
bar();

function waitOneSecond(msg) {
  return new Promise((resolve, _) => {
    setTimeout(resolve(`${msg}`), 1000);
  });
} // 1초 대기하고 메시지 출력 함수

async function countOneToTen() {
  for (let i of [...Array(10).keys()]) {
    // 0부터 9까지 loop
    // 1초 대기 후에 RESULT에 결과값을 저장
    let result = await waitOneSecond(`${i + 1}초 대기 중 ...`);
    console.log(result);
  }
  console.log('complete');
}

countOneToTen();

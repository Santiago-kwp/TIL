async function myName() {
  return 'echo'; // Promise 객체로 감싸서 반환
}

console.log(myName()); // Promise { 'echo' }

async function showName() {
  const name = await myName(); // myName() 함수의 실행이 끝날 때까지 실행할 수 없음.
  console.log(name);
} // 이름을 출력하는 함수

console.log(showName()); // Promise { <pending> } "\n" echo 가 나옴
showName(); // echo 출력

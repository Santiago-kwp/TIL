// Promise 객체는 자바스크립트에서 비동기 실행을 동기화하는 구문으로 사용된다.
// Promise 객체는 처리한 결과를 반환한다.
// 약속 = (이행 | 거절 | 대기) 세 가지 상태를 가질 수 있는 객체이다.
// 이 코드는 미래의 어느 시점에 실행할 거야, 세 가지 상태를 가질 수 있다.

const DB = [];

function saveDB(user) {
  const oldDBSize = DB.length;
  DB.push(user);
  console.log(`save ${user.name} to DB`);
  return new Promise((resolve, reject) => {
    if (DB.length > oldDBSize) {
      resolve(user);
    } // 성공시 사용자 객체를 반환
    else {
      reject(new Error('SaveDB fail!'));
    }
  });
}

function sendEmail(user) {
  console.log(`email to ${user.email}`);
  return new Promise((resolve) => {
    resolve(user);
  });
}

function getResult(user) {
  return new Promise((resolve, reject) => {
    resolve(`success register ${user.name}`);
  });
}

function registerByPromise(user) {
  const result = saveDB(user).then(sendEmail).then(getResult);
  console.log(result); // 비동기 처리를 수행완료 전에 로그가 남으므로 Promise { <pending> } 이 출력됨.
  return result;
}

const myUser = { email: 'test@test.com', name: 'kiwoong', password: '1234' };

const result = registerByPromise(myUser);
result.then(console.log); // success register kiwoong

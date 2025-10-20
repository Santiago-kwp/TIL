function myWork(work) {
  return new Promise((resolve, reject) => {
    resolve(work.toUpperCase);
  });
}

function playGame(work) {
  return new Promise((resolve, reject) => {
    if (work === 'DONE') {
      resolve('Go Play Game');
    } else {
      reject(new Error("Don't"));
    }
  });
}

myWork('done').then(function (result) {
  playGame(result).then(function (val) {
    console.log(val);
  });
});

myWork('done').then(playGame).then(console.log);

// Promise 객체에 인수로 resolve, reject
// myWork('done').then(
//   function (value) {
//     console.log(value);
//   },
//   function (err) {
//     console.log(err);
//   }
// );

// // 위보다는 아래가 낫다
// myWork('done')
//   .then(function (value) {
//     console.log(value);
//   })
//   .catch(function (err) {
//     console.error(err);
//   });

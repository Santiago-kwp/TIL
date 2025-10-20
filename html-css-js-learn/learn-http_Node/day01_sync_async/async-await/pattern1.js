function myWork(work) {
  return new Promise((resolve, reject) => {
    if (work === 'done') {
      resolve('roll game!');
    } else {
      reject(new Error('working!'));
    }
  });
}

// Promise 객체에 인수로 resolve, reject
myWork('done').then(
  function (value) {
    console.log(value);
  },
  function (err) {
    console.log(err);
  }
);

// 위보다는 아래가 낫다
myWork('done')
  .then(function (value) {
    console.log(value);
  })
  .catch(function (err) {
    console.error(err);
  });

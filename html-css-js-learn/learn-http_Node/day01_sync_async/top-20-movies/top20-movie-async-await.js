const axios = require('axios'); // axios 라이브러리 임포트

async function getTop20Movies() {
  // async로 Promise 객체로 감싸서 반환한다.
  const url =
    'https://raw.githubusercontent.com/wapj/jsbackend/main/movieinfo.json';

  try {
    // 네트워크로 데이터를 받아오니까 await를 통해서 대기를 시킴
    const result = await axios.get(url);
    // 구조 분해 할당(destructuring assignment) 문법
    const { data } = result; // result 값에는 data 프로퍼티가 있다.

    // result는 Axios의 응답 객체이며, 보통 다음과 같은 구조를 가지고 있다.
    //     {
    //   data: ...,        // 서버에서 받은 실제 데이터
    //   status: 200,      // HTTP 상태 코드
    //   statusText: "OK", // 상태 메시지
    //   headers: {...},   // 응답 헤더
    //   config: {...},    // 요청 설정
    //   request: {...}    // 요청 객체
    // }

    if (!data.articleList || data.articleList.size == 0) {
      throw new Error('데이터가 없습니다.');
    }

    const movieinfos = data.articleList.map((article, idx) => {
      return { title: article.title, rank: idx + 1 };
    });

    for (let movieinfo of movieinfos) {
      console.log(`[${movieinfo.rank}위] ${movieinfo.title}`);
    }
  } catch (error) {
    throw new Error(err);
  }
}

getTop20Movies();

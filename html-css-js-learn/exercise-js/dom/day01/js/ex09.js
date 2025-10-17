// 이벤트 연결
window.onload = function () {
  // 버튼 객체를 선택한다.
  let button = this.document.getElementById('button');

  // 이벤트를 연결한다.
  button.onclick = function () {
    alert('click event');
  };
};

// 이벤트 연결
window.onload = function () {
  const button = document.getElementById('button');
  button.textContent = '버튼-★';

  // 이벤트를 연결한다.
  button.onclick = function () {
    button.textContent += '★';
  };
};

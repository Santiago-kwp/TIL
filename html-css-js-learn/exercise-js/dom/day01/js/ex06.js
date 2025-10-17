window.onload = function () {
  const header = this.document.getElementById('Header');

  header.innerHTML = '<i> i 태그입니다. </i><br/>';
  header.innerHTML += `&lt;i&gt;i 태그입니다. &lt;/i&gt;`;
  header.textContent = '<i>i 태그입니다.';

  // innerHTML와 textContent의 차이
  // 보안문제로 인해 textContent를 주로 씀
};

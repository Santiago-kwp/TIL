window.onload = function () {
  const header = this.document.getElementById('Header');
  const originalText = header.innerHTML;
  // const originalText = this.orientation;

  header.innerHTML = '변경 후 : From JavaSCript! <br/>';
  header.innerHTML += `변경 전 : ${originalText}`;
};

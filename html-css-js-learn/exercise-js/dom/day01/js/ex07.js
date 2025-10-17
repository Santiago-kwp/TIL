window.onload = function () {
  let output = '';

  for (let i = 0; i < 256; i++) {
    output += `<div></div>`;
  }

  this.document.body.innerHTML = output;

  const divs = this.document.querySelectorAll('div');

  for (let i = 0; i < divs.length; i++) {
    const div = divs[i];
    div.style.height = '3px';
    div.style.background = 'rgb(' + i + ',' + i + ',' + i + ')';
  }
};

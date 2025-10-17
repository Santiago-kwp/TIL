window.onload = function () {
  for (let i = 0; i < headers.length; i++) {
    const header = headers[i];

    header.style.color = 'orange';

    header.style.BackgroundColor = 'green';
    header.innerHTML = 'From JavaScript';
  }
};

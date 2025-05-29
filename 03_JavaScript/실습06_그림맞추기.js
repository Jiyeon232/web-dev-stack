// click : 클릭 이벤트를 걸어야 하는 애
const click = document.querySelector("#click");
// console.log(click);
// img : 내가 클릭을 했을 때 바꿔야 하는 애
const img = document.querySelectorAll(".container img");
// console.log(img);
const span = document.querySelector("button span");
const h2 = document.querySelector("h2");
const restart = document.querySelector("#restart");

let count = 0;
const game = () => {
  //랜덤값 : 1 ~ 3까지
  const random = [
    Math.floor(Math.random() * 3) + 1,
    Math.floor(Math.random() * 3) + 1,
    Math.floor(Math.random() * 3) + 1,
  ];
  // console.log(random);

  for (let i = 0; i < img.length; i++) {
    img[i].setAttribute("src", `assets/spy${random[i]}.jpg`);
  }
  // 클릭할 때마다 카운트 증가
  span.innerHTML = ++count;

  // 이미지 3개가 일치한 경우 버튼 disabled 처리
  // click.setAttribute("disabled", disabled);
  // click.setAttribute("disabled", true);
  if (random[0] === random[1] && random[1] === random[2]) {
    click.disabled = true;

    // h2에 visibility: visible로 변경
    h2.style.visibility = "visible";
  }
};

const end = () => {
  // 이미지는 처음 그대로 1, 2, 3 순서대로 보이게 하고
  for (let i = 0; i < img.length; i++) {
    img[i].setAttribute("src", `assets/spy${[i + 1]}.jpg`);
    // img[0] = setAttribute("src", "assets/spy1.jpg");
    // img[1] = setAttribute("src", "assets/spy2.jpg");
    // img[2] = setAttribute("src", "assets/spy3.jpg");
  }
  //count는 0으로 바꾸고 span은 비우고
  count = 0;
  span.innerHTML = "";
  // h2 visibility = hidden 처리
  h2.style.visibility = "hidden";
  // click 버튼의 disabled false 처리
  click.disabled = false;
};
click.addEventListener("click", game);
restart.addEventListener("click", end);
/* 강제 새로고침
restart.addEventListener("click", () => {
  location.reload();
});
*/

//$(document).ready(function () {});
$(function () {
  // 자바스크립트 방식
  //const h1 = document.querySelector("h1");
  //const pList = document.querySelectorAll("p");

  //h1.style.color = "blue";
  /*for (p of pList) {
    p.style.color = "white";
    p.style.backgroundColor = "tomato";
  }*/

  // 제이쿼리 방식
  $("h1").css("color", "blue");
  //$("p").css("color", "white");
  //$("p").css("background-color", "tomato"); // css 속성 그대로 사용 가능!
  $("p").css({
    color: "white",
    //backgroundColor: "tomato",
    "background-color": "tomato", // css 속성 그대로 사용 가능!
  });
  $("p").first().css("font-size", "4rem");
  $("p").last().css("color", "yellow");
  $("p:eq(2)").text("eq로 다시 글 작성합니다");
  //$("p").eq(2).text("eq로 다시 글 작성합니다"); // 둘 다 가능
  $(".wrap").children().css({ color: "deeppink", border: "2px solid" }); // 자식 선택자
  $(".wrap").find("h1").css({ color: "orange" }); // find : 원하는 태그 찾기
  $("h1").siblings("p").css("background-color", "navy"); // 형제 선택자

  // DOM 객체
  // 자바스크립트
  const p = document.createElement("p");
  p.innerText = "자바스크립트로 추가";
  document.querySelector("#content").appendChild(p);

  // 제이쿼리
  const p2 = $("<p>").html("제이쿼리로 추가");
  $("#content").append(p2);
  // append 해당 자식요소 뒷부분에 추가 (appendTo)
  $("#item").append("<span>append</span>");
  $("<span>appendTo</span>").appendTo("#item");
  // prepend 해당 자식요소 앞부분에 추가
  $("#item").prepend("<span>prepend</span>");
  // after 해당 형제 요소로 뒷부분에 추가
  $("#item").after("<span>after</after>");
  // before 해당 형제 요소로 앞부분에 추가
  $("#item").before("<span>before</before>");

  // 이벤트
  /*
  $("textarea").on({
    keydown: (e) => {
      // 키보드 눌려질 때
      console.log(`keydown - e.key : ${e.key}, e.keyCode : ${e.keyCode}`);
    },
    keypress: (e) => {
      // 키보드 입력될 때
      console.log(`keypress - e.key : ${e.key}, e.keyCode : ${e.keyCode}`);
    },
    keyup: (e) => {
      // 키보드 떼어질 때
      console.log(`keyup - e.key : ${e.key}, e.keyCode : ${e.keyCode}`);
    },
  });
  */

  $("textarea").keyup((e) => {
    let target = $(e.target);
    //console.log(target.val()); // 입력한 text 값
    //console.log(target.val().length); // 입력한 text 길이
    let length = target.val().length;
    if (length > 50) {
      target.val(target.val().substr(0, 50));
    } else {
      $("#counter").text(length);
    }
  });

  $("#userId").keyup((e) => {
    let id = $(e.target).val(); // 제이쿼리 방식
    id = e.target.value; // 자바스크립트 방식
    console.log(id); // 입력한 id 값
    const regExp = /^[a-z][0-9a-z]{3,11}$/;
    if (regExp.test(id)) {
      $("#idCheck").text("사용 가능한 아이디입니다.");
      $("#idCheck").css("color", "green");
    } else if (id === "") {
      // 입력한 값을 지웠을 때 text 비워버리기
      $("#idCheck").text("");
    } else {
      // 한 줄로 이어서 쓰는 것도 가능!
      $("#idCheck").text("사용 불가능한 아이디입니다.").css("color", "red");
    }
  });
});

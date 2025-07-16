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

  //
});

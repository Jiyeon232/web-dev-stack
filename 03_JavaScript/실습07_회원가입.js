const id = document.querySelector("#id");
const pw = document.querySelector("#pw");
const pwck = document.querySelector("#pwck");
const name = document.querySelector("#name");
const email = document.querySelector("#email");
const tel = document.querySelector("#tel");
const input = document.querySelectorAll("input");
const result1 = document.querySelector("#result1");
const result2 = document.querySelector("#result2");
const result3 = document.querySelector("#result3");
const result4 = document.querySelector("#result4");
const result5 = document.querySelector("#result5");
const result6 = document.querySelector("#result6");
const submit = document.querySelector("#submit");
const reset = document.querySelector("#reset");

let inputId = /^[a-zA-Z][a-zA-Z0-9]{3,11}$/;
id.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputId.test(e.target.value));
  if (inputId.test(e.target.value)) {
    result1.innerHTML = "OK!";
    result1.style.color = "green";
  } else {
    result1.innerHTML = "영문자로 시작하고 영문자와 숫자 조합으로 4~12자 이내";
    result1.style.color = "red";
  }
});

let inputPw =
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,15}$/;
pw.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputPw.test(e.target.value));
  if (inputPw.test(e.target.value)) {
    result2.innerHTML = "OK!";
    result2.style.color = "green";
  } else {
    result2.innerHTML = "영문자, 숫자, 특수문자 조합으로 8~15자 이내";
    result2.style.color = "red";
  }
});

pwck.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputPwck.test(e.target.value));
  if (e.target.value === pw.value) {
    result3.innerHTML = "OK!";
    result3.style.color = "green";
  } else {
    result3.innerHTML = "위 비밀번호와 동일하게";
    result3.style.color = "red";
  }
});

let inputName = /^[가-힣]{2,}$/;
name.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputName.test(e.target.value));
  if (inputName.test(e.target.value)) {
    result4.innerHTML = "OK!";
    result4.style.color = "green";
  } else {
    result4.innerHTML = "한글 2자 이상";
    result4.style.color = "red";
  }
});

let inputMail = /^[\w.-]+@[\w.-]+\.[A-Za-z]{2,}$/;
email.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputMail.test(e.target.value));
  if (inputMail.test(e.target.value)) {
    result5.innerHTML = "OK!";
    result5.style.color = "green";
  } else {
    result5.innerHTML = "이메일 형식";
    result5.style.color = "red";
  }
});

let inputTel = /^010-\d{4}-\d{4}$/;
tel.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputTel.test(e.target.value));
  if (inputTel.test(e.target.value)) {
    result6.innerHTML = "OK!";
    result6.style.color = "green";
  } else {
    result6.innerHTML = "전화번호 형식";
    result6.style.color = "red";
  }
});

submit.addEventListener("click", (e) => {});
reset.addEventListener("click", (e) => {});

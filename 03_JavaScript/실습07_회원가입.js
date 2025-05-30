const userId = document.querySelector("#userId");
const password = document.querySelector("#password");
const passwordCk = document.querySelector("#passwordCk");
const userName = document.querySelector("#userName");
const email = document.querySelector("#email");
const tel = document.querySelector("#tel");
const input = document.querySelectorAll("input");
const result1 = document.querySelector("#result1");
const result2 = document.querySelector("#result2");
const result3 = document.querySelector("#result3");
const result4 = document.querySelector("#result4");
const result5 = document.querySelector("#result5");
const result6 = document.querySelector("#result6");
const signup = document.querySelector("#signup");
const cancle = document.querySelector("#cancle");

signup.disabled = true;
let check1 = false; // userId
let check2 = false; // password
let check3 = false; // passwordCk
let check4 = false; // userName
let check5 = false; // email
let check6 = false; // tel

const inputuserId = /^[a-zA-Z][a-zA-Z0-9]{3,11}$/;
userId.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputuserId.test(e.target.value));
  if (inputuserId.test(e.target.value)) {
    result1.innerHTML = "OK!";
    result1.style.color = "green";
    check1 = true;
  } else if (e.target.value === "") {
    result1.innerHTML = "영문자로 시작하고 영문자와 숫자 조합으로 4~12자 이내";
    result1.style.color = "rgba(0, 0, 0, 0.3)";
    check1 = false;
  } else {
    result1.innerHTML = "영문자로 시작하고 영문자와 숫자 조합으로 4~12자 이내";
    result1.style.color = "red";
    check1 = false;
  }

  /*
  if (check1 && check2 && check3 && check4 && check5 && check6) {
    signup.disabled = false;
  }
  */

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

const inputpassword =
  /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,15}$/;
password.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputpassword.test(e.target.value));
  if (inputpassword.test(e.target.value)) {
    result2.innerHTML = "OK!";
    result2.style.color = "green";
    check2 = true;
  } else if (e.target.value === "") {
    result2.innerHTML = "영문자, 숫자, 특수문자 조합으로 8~15자 이내";
    result2.style.color = "rgba(0, 0, 0, 0.3)";
    check2 = false;
  } else {
    result2.innerHTML = "영문자, 숫자, 특수문자 조합으로 8~15자 이내";
    result2.style.color = "red";
    check2 = false;
  }

  if (passwordCk.value === e.target.value && passwordCk.value !== "") {
    result3.innerHTML = "OK!";
    result3.style.color = "green";
    check3 = true;
  } else {
    result3.innerHTML = "위 비밀번호와 동일하게";
    result3.style.color = "red";
    check3 = false;
  }

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

passwordCk.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputpasswordCk.test(e.target.value));
  if (e.target.value !== "" && password.value === e.target.value) {
    result3.innerHTML = "OK!";
    result3.style.color = "green";
    check3 = true;
  } else if (e.target.value === "") {
    result3.innerHTML = "위 비밀번호와 동일하게";
    result3.style.color = "rgba(0, 0, 0, 0.3)";
    check3 = false;
  } else {
    result3.innerHTML = "위 비밀번호와 동일하게";
    result3.style.color = "red";
    check3 = false;
  }

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

const inputuserName = /^[가-힣]{2,}$/;
userName.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputuserName.test(e.target.value));
  if (inputuserName.test(e.target.value)) {
    result4.innerHTML = "OK!";
    result4.style.color = "green";
    check4 = true;
  } else if (e.target.value === "") {
    result4.innerHTML = "한글 2자 이상";
    result4.style.color = "rgba(0, 0, 0, 0.3)";
    check4 = false;
  } else {
    result4.innerHTML = "한글 2자 이상";
    result4.style.color = "red";
    check4 = false;
  }

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

const inputemail = /^[\w.-]+@[\w.-]+\.[A-Za-z]{2,}$/;
email.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputemail.test(e.target.value));
  if (inputemail.test(e.target.value)) {
    result5.innerHTML = "OK!";
    result5.style.color = "green";
    check5 = true;
  } else if (e.target.value === "") {
    result5.innerHTML = "이메일 형식";
    result5.style.color = "rgba(0, 0, 0, 0.3)";
    check5 = false;
  } else {
    result5.innerHTML = "이메일 형식";
    result5.style.color = "red";
    check5 = false;
  }

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

const inputTel = /^010-\d{4}-\d{4}$/;
tel.addEventListener("input", (e) => {
  // console.log(e.target.value);
  // console.log(inputTel.test(e.target.value));
  if (inputTel.test(e.target.value)) {
    result6.innerHTML = "OK!";
    result6.style.color = "green";
    check6 = true;
  } else if (e.target.value === "") {
    result6.innerHTML = "전화번호 형식";
    result6.style.color = "rgba(0, 0, 0, 0.3)";
    check6 = false;
  } else {
    result6.innerHTML = "전화번호 형식";
    result6.style.color = "red";
    check6 = false;
  }

  signup.disabled = !(check1 && check2 && check3 && check4 && check5 && check6);
});

/*
signup.addEventListener("click", (e) => {
  if (
    inputuserId.test(e.target.value) &&
    inputpassword.test(e.target.value) &&
    e.target.value === password.value &&
    inputuserName.test(e.target.value) &&
    inputemail.test(e.target.value) &&
    inputTel.test(e.target.value)
  ) {
    signup.disabled = false;
  } else {
    signup.disabled = true;
  }
});
*/
cancle.addEventListener("click", (e) => {
  input.innerHTML = "";
  result1.innerHTML = "영문자로 시작하고 영문자와 숫자 조합으로 4~12자 이내";
  result1.style.color = "rgba(0, 0, 0, 0.3)";
  result2.innerHTML = "영문자, 숫자, 특수문자 조합으로 8~15자 이내";
  result2.style.color = "rgba(0, 0, 0, 0.3)";
  result3.innerHTML = "위 비밀번호와 동일하게";
  result3.style.color = "rgba(0, 0, 0, 0.3)";
  result4.innerHTML = "한글 2자 이상";
  result4.style.color = "rgba(0, 0, 0, 0.3)";
  result5.innerHTML = "이메일 형식";
  result5.style.color = "rgba(0, 0, 0, 0.3)";
  result6.innerHTML = "전화번호 형식";
  result6.style.color = "rgba(0, 0, 0, 0.3)";
});

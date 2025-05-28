const click = document.querySelector("#click");
const img = document.querySelectorAll(".container img");
click.addEventListener("click", (e) => {
  for (let i = 0; i < img.length; i++) {
    console.log(img[i]);
  }
});

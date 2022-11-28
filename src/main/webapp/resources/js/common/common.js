var changeVerifyCode = function (img) {
    img.src="../kaptch?"+Math.floor(Math.random()*100);
}

function login_form() {
    var popUrl = "./login.html";
    window.open(popUrl, "blank","width=440, height=350");
}

function member_check(){
  var id_reg = document.frm.id.value;
  var reg =  /^[a-zA-Z]+$/g;
  var rst = reg.test(id_reg);

  var pass_reg = document.frm.pass.value;
  var reg_pass =  /^[A-Za-z0-9_-]{6,12}$/;
  var rst_pass = reg_pass.test(pass_reg);

  var email_reg = document.frm.email.value;
  var reg_email = /^[a-z][a-z0-9_-]{2,11}@([a-z\d\.-]+)\.([a-z\.]{2,6})$/;
  var rst_email = reg_email.test(email_reg);

  var phone_reg = document.frm.phone.value;
  var reg_phone = /^\d{3}-\d{4}-\d{4}$/;
  var rst_phone = reg_phone.test(phone_reg);

  if(!rst){
    alert("아이디는 영어로 입력해 주세요.");
    return false;
  }else if (document.frm.id.value.length==0) {
    alert("아이디를 입력해 주세요.");
    return false;
  }else if(document.frm.id.value.length>=11){
    alert("아이디를 10자리를 초과할 수 없습니다.");
    return false;
  }


  if(!rst_pass){
    alert("비밀번호는 6-12자리 입니다.");
    return false;
  }else if(document.frm.pass.value.length==0){
    alert("비밀번호를 입력해 주세요");
    return false;
  }

  if(document.frm.pass.value!=document.frm.pass2.value){
    alert("비밀번호 확인해 주세요");
    return false;
  }

  if(!rst_email){
    alert("유효한 이메일을 입력해 주세요");
    return false;
  }

  if(!rst_phone){
    alert("유효한 핸드폰 번호를 입력해 주세요");
    return false;
  }

  return true;
}

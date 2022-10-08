 window.onpopstate = function(event) {  //뒤로가기 이벤트를 캐치합니다.
alert("뒤로가기 버튼을 눌렀습니다.");
  console.log('뒤로가기 체크');

  history.back();   // pushState로 인하여 페이지가 하나 더 생성되기 떄문에 한번에 뒤로가기 위해서 뒤로가기를 한번 더 해줍니다.

 };


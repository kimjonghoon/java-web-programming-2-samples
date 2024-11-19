$(document).ready(function() {
   $('#modifyForm').submit(function() {
      var title = $('#modifyForm input[name*=title]').val();
      var content = $('#modifyForm-ta').val();
      title = $.trim(title);
      content = $.trim(content);
      if (title.length === 0) {
          alert('제목이 비었습니다!');
          $('#modifyForm input[name*=title]').val('');
          return false;
      }
      if (content.length === 0) {
          alert('내용이 비었습니다!');
          $('#modifyForm-ta').val('');
          return false;
      }
      $('#modifyForm input[name*=title]').val(title);
      $('#modifyForm-ta').val(content);
   });
   $('#goView').click(function(){
      $('#viewForm').submit(); 
   });
});


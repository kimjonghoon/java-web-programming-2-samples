$(document).ready(function() {
   $('#writeForm').submit(function() {
      var title = $('#writeForm input[name*=title]').val();
      var content = $('#writeForm-ta').val();
      title = $.trim(title);
      content = $.trim(content);
      if (title.length === 0) {
          alert('제목이 비었습니다!');
          $('#writeForm input[name*=title]').val('');
          return false;
      }
      if (content.length === 0) {
          alert('내용이 비었습니다!');
          $('#writeForm-ta').val('');
          return false;
      }
      $('#writeForm input[name*=title]').val(title);
      $('#writeForm-ta').val(content);
   });
   $('#goList').click(function() {
      $('#listForm').submit(); 
   });
   $('#goView').click(function() {
      $('#viewForm').submit(); 
   });
});


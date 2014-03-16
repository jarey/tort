$(function () {
  $('.testclass-title').click(function (event) {
    var tableHeader = $(event.target).parent();
    var arrowIcon = tableHeader.children('i');
    var clazz = arrowIcon.attr('class');
    if (clazz.indexOf('glyphicon-chevron-down') != -1) {
      arrowIcon.attr('class', clazz.replace('glyphicon-chevron-down', 'glyphicon glyphicon-chevron-right'));
      tableHeader.siblings("table").hide();
    } else {
      arrowIcon.attr('class', clazz.replace('glyphicon glyphicon-chevron-right', 'glyphicon-chevron-down'));
      tableHeader.siblings("table").show();
    }
  })
});
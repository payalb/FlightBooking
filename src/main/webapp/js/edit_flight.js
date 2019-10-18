jQuery.validator.setDefaults({
	errorElement: 'span',
	errorClass: "badge badge-warning"
});
$.validator.addMethod('dateCompare', function(value, element, params) {
    var field_1 = $('input[name="' + params[0] + '"]').val(),
        field_2 = $('input[name="' + params[1] + '"]').val(),
        field_3 = $('input[name="' + params[2] + '"]').val(),
        field_4 = $('input[name="' + params[3] + '"]').val(),
        arr = new Date(field_3+'T'+field_4)
    	dept = new Date(field_1+'T'+field_2);
    return arr - dept > 0;
}, "Arrival time is not valid");
$('form').validate({
	highlight: function(element) {
        $(element).removeClass("badge badge-warning");
    },
	rules : {
		deptCity : {
			required : true,
			minlength : 2
		},
		arrCity : {
			required : true,
			minlength : 2
		},
		arrDate : {
			dateCompare : ['deptDate', 'deptTime', 'arrDate', 'arrTime']
		}
	}
});
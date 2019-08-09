$('document').ready(function () {
	$.ajax({
		url: "airplaneinfo",
		method: "GET",
		dataType: "json",
		success: function (data) {
			let result = $.map(data, function (d) {
				return d['airplaneId'];
			});
			$.each(result, function(index, value) {
				$('#airplaneId').append($('<option>', {
					value: value,
					text: value
				}));
			});
		},
		error: function (e) {
			console.log(e);
		}
	});
	$('#airplaneId').on('change', function() {
		$.get({
	        url: "airplaneinfo",
	        success: function(data){
	        	$.each(JSON.parse(data), function(index, airplane) {
	        		if ($('#airplaneId').val() === '') {
	        			$('#businessCap').val('');
		    			$('#firstCap').val('');
		    			$('#economyCap').val('');
	        		}
		    		if (airplane['airplaneId'] == $('#airplaneId').val()) {
		    			$('#businessCap').val(airplane['businessClassCap']);
		    			$('#firstCap').val(airplane['firstClassCap']);
		    			$('#economyCap').val(airplane['economyClassCap']);
		    		}
		    	});
	        }
	   });
	});
});
jQuery.validator.setDefaults({
	errorElement: 'span',
	errorClass: "badge badge-warning"
});
$.validator.addMethod('dateCompare', function(value, element, params) {
    var field_1 = $('input[name="' + params[0] + '"]').val(),
        field_2 = $('input[name="' + params[1] + '"]').val(),
        field_3 = $('input[name="' + params[2] + '"]').val(),
        field_4 = $('input[name="' + params[3] + '"]').val(),
        arr = new Date(field_3+'T'+field_4),
    	dept = new Date(field_1+'T'+field_2);
    if (field_1.length==0 || field_2.length==0){
    	$(element).data('error-msg',"Please input departure date and time.");
    	return false;
    }
    else{
    	$(element).data('error-msg',"Invalid arrive time.");
    	return arr - dept >= 0;
    }
}, function(params, element) {
    return $(element).data('error-msg');
});

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
		deptDate :{
			required : true
		},
		deptTime :{
			required : true
		},		
		arrTime :{
			required : true,
			dateCompare : ['deptDate', 'deptTime', 'arrDate', 'arrTime']
		},
		arrDate :{
			required : true,
			dateCompare : ['deptDate', 'deptTime', 'arrDate', 'arrTime']
		}

	},
	submitHandler:function(form)
	{
		form.submit();
	}
    
});
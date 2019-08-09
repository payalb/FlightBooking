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



    // ($element).data(name,data)


    	$('form').validate({
    		highlight: function(element) {
    	        $(element).removeClass("badge badge-warning");
    	    },
    		rules : {
    			deptCity : {
    				required : true,
    				minlength : 2,
    			},
    			arrCity : {
    				required : true,
    				minlength : 2
    			},
    		
    			arrDate :{
    				dateCompare : ['deptDate', 'deptTime', 'arrDate', 'arrTime']
    				
    			}
    		},
    	    
    	    submitHandler:function(form){
    	    	form.submit();

    	        
    	    }
    	    
    	    
    	    
    	});

$.validator.addMethod('dateCompare', function(value, element, params) {
    var field_1 = $('input[name="' + params[0] + '"]').val(),
        field_2 = $('input[name="' + params[1] + '"]').val(),
        field_3 = $('input[name="' + params[2] + '"]').val(),
        field_4 = $('input[name="' + params[3] + '"]').val(),
        today = new Date();
        arr_date = new Date(field_3)
    	dept_date = new Date(field_1);
        arr = new Date(field_3+'T'+field_4)
    	dept = new Date(field_1+'T'+field_2);
        
        if (field_1.length == 0){
        	$(element).data('error-msg',"Please input departure date");
        	return false;
        }
        if (field_2.length==0){
        	$(element).data('error-msg',"Please input departure time");
        	return false;
        }
        if (field_3.length==0){
        	$(element).data('error-msg',"Please input Arrival Date");
        	return false;
        }
        if (field_4.length==0){
        	$(element).data('error-msg',"Please input Arrival time");
        	return false;
        }   
        if(dept_date.getTime()>today){
    	if(arr_date>= dept_date){
    		return arr - dept > 0;
    		 
    	}else{
    		return false;
    	}
    	
    }
},function(params, element) {
    return $(element).data('error-msg');
},"Invalid Date and Time");










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
    $(function() {
        $("#deptCity").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "fcauto",
                    type: "GET",
                    data: {
                        find: request.find
                    },
                    dataType: "json",
                    success: function(data) {                    	
                        response(data);
                    }
                });
            }
        });
    });
    $(function() {
        $("#arrCity").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "tcauto",
                    type: "GET",
                    data: {
                        find: request.find
                    },
                    dataType: "json",
                    success: function(data) {
                        response(data);
                    }
                });
            }
        });
    });
//	$( function() {
//	    var availableTags = [
//	      "Delhi",
//	      "New York",
//	      "Chicago",
//	      "Pune","San Francisco",
//	      "Bangalore","Los Angeles","St Charles",
//	      "Shanghai","Albany", "Austin","Belfast"
//	    ];
//	    $( "#deptCity" ).autocomplete({
//	      source: availableTags
//	    });
//	    $( "#arrCity" ).autocomplete({
//		      source: availableTags
//		    });
//	    
//	  } );

	

	
	  
	
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
    				notEqual :deptCity
    			},
    			
    			arrCity : {
    				required : true,
    				minlength : 2,
    				
    			},
    		
    			arrTime :{
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
    	        arr = new Date(field_3+'T'+field_4),
    	    	dept = new Date(field_1+'T'+field_2);
    	    if (field_1.length == 0 || field_2.length == 0){
    	    	$(element).data('error-msg',"Please input departure date and time.");
    	    	return false;
    	    }
    	    else if(field_3.length == 0 ){
    	    	$(element).data('error-msg',"Please input arrival date.");
    	    	return false;
    	    }else{
    	    	$(element).data('error-msg',"Please input  valid arrival time.");
    	    	return arr - dept >0
    	    }
    	}, function(params, element) {
    	    return $(element).data('error-msg');
    	});
jQuery.validator.setDefaults({
	errorElement: 'span',
	errorClass: "badge badge-warning"
});
$.validator.addMethod('ticketNum', function(value, element, params) {
    var field_1 = $('input[name="' + params[0] + '"]').val()+0,
        field_2 = $('input[name="' + params[1] + '"]').val()+0,
        field_3 = $('input[name="' + params[2] + '"]').val()+0;
    return parseInt(field_1) + parseInt(field_2) + parseInt(field_3) > 0;
}, "Total num of ticket is invalid");
$('form').validate({
	highlight: function(element) {
        $(element).removeClass("badge badge-warning");
    },
	rules : {
		business : {
			require_from_group : [ 1, ".ticket-group" ],
			ticketNum : ['business', 'firstClass', 'economy'],
			min : 0
		},
		firstClass : {
			require_from_group : [ 1, ".ticket-group" ],
			min : 0
		},
		economy : {
			require_from_group : [ 1, ".ticket-group" ],
			min : 0
		}
	},
	messages : {
		business : {
			require_from_group : 'Please specify num of ticket',
			min : 'Should not be negative'
		},
		firstClass : {
			require_from_group : 'Please specify num of ticket',
			min : 'Should not be negative'
		},
		economy : {
			require_from_group : 'Please specify num of ticket',
			min : 'Should not be negative'
		}
	}
});
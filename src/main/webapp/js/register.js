$.validate({
	modules : 'date, security'
});

$('#email').on('focusout', function () {
	if($(this).val().trim()) {
		$.get({
			url: "emailcheck?email="+$(this).val().trim(),
			success: function(data){
				if(data=='true') {
					$('#emailalert').show();
				} else {
					$('#emailalert').hide();
				}
			}
		});
	}
});
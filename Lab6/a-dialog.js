$(function () {
	$("#my-dialog").dialog({
		autoOpen: false,
		buttons: {
			OK: function() {$(this).dialog("close");}
		}	
	});
	$("#my-button").click(function() {$("#my-dialog").dialog("open");});
});


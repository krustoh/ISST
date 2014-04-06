$(function() {
	$('input.ace[type="radio"], input.ace[type="checkbox"]').each(function() {
		$(this).next('input[type="hidden"]').insertBefore($(this));
	});
	
	$('.isst-table-form th input:checkbox').on('click' , function(){
		var that = this;
		$(this).closest('table').find('tr > td:first-child input:checkbox')
		.each(function(){
			this.checked = that.checked;
			$(this).closest('tr').toggleClass('selected');
		});
	});

	$('.isst-table-form .isst-table-form-actions').find('button[data-url], a').click(function() {
		var form = $('.isst-table-form');
		var url = $(this).attr('data-url');
		url = url || $(this).attr("href");
		form.attr('action', url);
		var checkboxes = form.find('input[name="id[]"]:checked');
		if (checkboxes.length > 0) {
			form.submit();
		} else {
			alert('请先选择需要操作的项！');
		}
		return false;
	});
});
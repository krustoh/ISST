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
	
	var colorbox_params = {
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="icon-arrow-left"></i>',
		next:'<i class="icon-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = 'auto';
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('a[data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").append("<i class='icon-spinner orange'></i>");
});
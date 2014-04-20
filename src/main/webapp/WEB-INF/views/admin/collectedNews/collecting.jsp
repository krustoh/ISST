<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="pagination" uri="/pagination"%>
<%@ taglib uri="/jsp_layout" prefix="layout"%>
<%@ taglib uri="/navigation" prefix="navigation"%>
<%@ taglib uri="/utils" prefix="utils"%>

<navigation:setNavigationActiveKey key="${category.alias=='experience' ? 'job_' : 'archive_'}${category.alias}"/>
<navigation:setPageTitle label="采集"/>

<layout:override name="javascripts">

<script type="text/javascript">
$(function() {
	var newsList;
	var $newsTemplate = $(".data-news");
	
	var collectDetail = function(id, callback) {
		$.ajax({
			dataType: "json",
			url: "<utils:url url="/collectedNews/collect/" />" + id,
			success: function(data) {
				callback && callback.call(null, data);
			}
		});
	};
	
	var collectDetailRecursion = function(i) {
		if (newsList && i < newsList.length) {
			var news = newsList[i];
			$("#newsCollectTitle").html(news.title);
			$("#newsCollectCurrent").html(i + 1);
			collectDetail(news.id, function(data) {
				var $template = $newsTemplate.clone(true);
				$template.find("input.data-news-id").val(data.id);
				$template.find("td.data-news-id").html(data.id);
				$template.find("td.data-news-title").html(data.title);
				$template.find("td.data-news-time").html(new Date(data.postTime).format("yyyy-MM-dd hh:mm:ss"));
				$template.find("a.data-news-edit").attr("href", '<utils:url url="/collectedNews/" />' + data.id + ".html");
				$template.find("a.data-news-publish").attr("href", '<utils:url url="/collectedNews/categories/${category.alias}/publish/archive?id[]=" />' + data.id);
				$template.find("a.data-news-ignore").attr("href", '<utils:url url="/collectedNews/categories/${category.alias}/ignore?id[]=" />' + data.id);
				$template.find("a.data-news-delete").attr("href", '<utils:url url="/collectedNews/categories/${category.alias}/delete?id[]=" />' + data.id);
				$("#newsContentList tbody").prepend($template.fadeIn());
				collectDetailRecursion(i + 1);
			});
		} else {
			$("#newsCollectTitle").html("");
		}
	};
	
	$("#newsCollecting").click(function() {
		$("#newsCollectNotice").show();
		
		$.ajax({
			dataType: "json",
			url: "<utils:url url="/collectedNews/categories/${category.alias}/collect/archive" />",
			success: function(data) {
				$("#newsCollectedCount").html(data.length);
				$("#newsCollectTotal").html(data.length);
				if (data.length > 0) {
					$("#newsCollectShow").show();
					newsList = data;
					collectDetailRecursion(0);
				}
			}
		});
		
		return false;
	});
	
	$(".newsReCollect").click(function() {
		var $this = $(this);
		collectDetail($this.data("news-id"), function() {
			$this.remove();
		});
		return false;
	});
});
</script>
</layout:override>

<layout:override name="page-header">
			<div class="pull-right" style="margin-right: 6%;">
				<a style="color:white" class="btn btn-sm btn-primary" href="javascript:;" id="newsCollecting">
					<i class="icon-plus align-top bigger-125"></i>
						开始采集
				</a>
			</div>
</layout:override>

<layout:override name="content">
	<div class="col-xs-12">
		<div class="alert alert-info" style="display:none;" id="newsCollectNotice">
			<button type="button" class="close" data-dismiss="alert">
				<i class="icon-remove"></i>
			</button>
			<strong>已采集到 <em id="newsCollectedCount"></em> 条 </strong> <br />
			<p id="newsCollectShow" style="display:none;">
				<strong>正在采集：<em id="newsCollectCurrent"></em> / <em id="newsCollectTotal"></em></strong> &nbsp; &nbsp;
				<em id="newsCollectTitle"></em>
			</p>
		</div>

		<div class="tabbable">
			<ul class="nav nav-tabs" id="myTab">
				<li class="active"><a href="<utils:url url="/collectedNews/categories/${category.alias}.html" />">未处理 </a>
				</li>
				<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/published.html" />">已发布 </a>
				</li>
				<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/ignored.html" />">已忽略 </a>
				</li>
			</ul>
			<div class="tab-content">
				<div id="newsContentList" class="tab-pane in active">
					<div class="table-responsive">
						<form action="" class="isst-table-form">

							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center"><label> <input type="checkbox" class="ace" /> <span class="lbl"></span> </label>
										</th>
										<th>ID</th>
										<th>标题</th>
										<th>发布日期</th>
										<th></th>
									</tr>
								</thead>

								<tbody>
									<tr class="data-news" style="display:none;">
										<td class="center">
											<label> <input type="checkbox" class="ace data-news-id" name="id[]" value="" /> 
											<span class="lbl"></span> 
											</label>
										</td>
										<td class="data-news-id"></td>
										<td class="data-news-title"></td>
										<td class="data-news-time"></td>
										<td>
											<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
												<a class="btn btn-xs btn-info data-news-edit" href="" title="编辑"> 
													<i class="icon-edit bigger-120"></i> 
												</a> 
												<a class="btn btn-xs btn-info data-news-publish" href="" title="发布"> 
													<i class="icon-edit bigger-120"></i> 
												</a> 
												<a class="btn btn-xs btn-danger data-news-ignore" href="" title="忽略"> 
													<i class="icon-trash bigger-120"></i>
												</a>
												<a class="btn btn-xs btn-danger data-news-delete" href="" title="删除"> 
													<i class="icon-trash bigger-120"></i>
												</a>
											</div>
											<div class="visible-xs visible-sm hidden-md hidden-lg">
												<div class="inline position-relative">
													<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
														<i class="icon-cog icon-only bigger-110"></i>
													</button>

													<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

														<li>
															<a href="" class="tooltip-success data-news-edit" data-rel="tooltip" title="编辑"> 
																<span class="green"> <i class="icon-edit bigger-120"></i> </span>
															</a>
														</li>
														<li>
															<a href="" class="tooltip-error" data-rel="tooltip  data-news-publish" title="发布"> 
																<span class="red"> <i class="icon-trash bigger-120"></i> </span> 
															</a>
														</li>
														<li>
															<a href="" class="tooltip-error" data-rel="tooltip data-news-ignore" title="忽略"> 
																<span class="red"> <i class="icon-trash bigger-120"></i> </span> 
															</a>
														</li>
														<li>
															<a href="" class="tooltip-error" data-rel="tooltip data-news-delete" title="删除"> 
																<span class="red"> <i class="icon-trash bigger-120"></i> </span> 
															</a>
														</li>
													</ul>
												</div>
											</div>
										</td>
									</tr>
									<c:forEach items="${news.items}" var="item">
										<tr>
											<td class="center"><label> <input type="checkbox" class="ace" name="id[]" value="${item.id}" /> <span class="lbl"></span> </label>
											</td>
											<td>${item.id}</td>
											<td>
											${item.title}
											<c:if test="${item.status==0}">
											<a href="javascript:;" class="newsReCollect" data-news-id="${item.id}">重试</a>
											</c:if>
											</td>
											<td><fmt:formatDate value="${item.createdAt}" pattern="yyyy-MM-dd HH:mm:ss" />
											</td>

											<td>
												<div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
													<a class="btn btn-xs btn-info" href="<utils:url url="/collectedNews/${item.id}.html" />" title="编辑"> 
														<i class="icon-edit bigger-120"></i> 
													</a> 
													<a class="btn btn-xs btn-info" href="<utils:url url="/collectedNews/categories/${category.alias}/publish/archive?id[]=${item.id}" />" title="发布"> 
														<i class="icon-edit bigger-120"></i> 
													</a> 
													<a class="btn btn-xs btn-danger" href="<utils:url url="/collectedNews/categories/${category.alias}/ignore?id[]=${item.id}" />" title="忽略"> 
														<i class="icon-trash bigger-120"></i> 
													</a>
													<a class="btn btn-xs btn-danger" href="<utils:url url="/collectedNews/categories/${category.alias}/delete?id[]=${item.id}" />" title="删除"> 
														<i class="icon-trash bigger-120"></i> 
													</a>
												</div>
												<div class="visible-xs visible-sm hidden-md hidden-lg">
													<div class="inline position-relative">
														<button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown">
															<i class="icon-cog icon-only bigger-110"></i>
														</button>

														<ul class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">

															<li>
																<a href="<utils:url url="/collectedNews/${item.id}.html" />" class="tooltip-success" data-rel="tooltip" title="编辑"> 
																<span class="green"> <i class="icon-edit bigger-120"></i></span> 
																</a>
															</li>
															<li>
																<a href="<utils:url url="/collectedNews/categories/${category.alias}/publish/archive?id[]=${item.id}" />" class="tooltip-error" data-rel="tooltip" title="发布"> 
																	<span class="red"><i class="icon-trash bigger-120"></i> </span> 
																</a>
															</li>
															<li>
																<a href="<utils:url url="/collectedNews/categories/${category.alias}/ignore?id[]=${item.id}" />" class="tooltip-error" data-rel="tooltip" title="忽略"> 
																	<span class="red"><i class="icon-trash bigger-120"></i> </span> 
																</a>
															</li>
															<li>
																<a href="<utils:url url="/collectedNews/categories/${category.alias}/delete?id[]=${item.id}" />" class="tooltip-error" data-rel="tooltip" title="删除"> 
																	<span class="red"> <i class="icon-trash bigger-120"></i> </span> 
																</a>
															</li>
														</ul>
													</div>
												</div>
											</td>

										</tr>
									</c:forEach>
								</tbody>
							</table>

							<div class="row">
								<div class="col-sm-4 isst-table-form-actions">
									<div class="btn-group dropup">
										<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
											批量操作 <i class="icon-angle-up icon-on-right"></i>
										</button>
										<ul class="dropdown-menu">
											<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/publish" />">批量发布</a></li>
											<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/ignore" />">批量忽略</a></li>
											<li><a href="<utils:url url="/collectedNews/categories/${category.alias}/delete" />">批量删除</a></li>
										</ul>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</layout:override>

<%@ include file="../layouts/main.jsp"%>
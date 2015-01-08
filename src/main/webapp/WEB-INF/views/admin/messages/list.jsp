<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="pagination" uri="/pagination" %>
<%@ taglib uri="/jsp_layout" prefix="layout" %>
<%@ taglib uri="/navigation" prefix="navigation" %>
<%@ taglib uri="/utils" prefix="utils" %>

<navigation:setNavigationActiveKey key="messages"/>

<layout:override name="page-header">
    <div class="pull-right" style="margin-right: 6%;">
        <a style="color:white" class="btn btn-sm btn-primary" href="<utils:url url="/messages/add.html" />">
            <i class="icon-plus align-top bigger-125"></i>
            添加
        </a>
    </div>

</layout:override>

<layout:override name="content">
    <div class="col-xs-12">
        <div class="table-responsive">

            <c:choose>
                <c:when test="${messages.total>0}">
                    <form action="" class="isst-table-form">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                            <tr>
                                <th class="center">
                                    <label>
                                        <input type="checkbox" class="ace"/>
                                        <span class="lbl"></span>
                                    </label>
                                </th>
                                <th>ID</th>
                                <th>标题</th>
                                <th>内容</th>
                                <th>状态</th>
                                <th>发布日期</th>
                                <th></th>
                            </tr>
                            </thead>

                            <tbody>
                            <c:forEach items="${messages.items}" var="message">
                                <tr>
                                    <td class="center">
                                        <label>
                                            <input type="checkbox" class="ace" name="id[]" value="${message.id}"/> <span
                                                class="lbl"></span> </label>
                                    </td>
                                    <td>${message.id}</td>
                                    <td>
                                            ${message.title}
                                        <c:if test="${message.status==0}">
                                            <a href="<utils:url url="/messages/push?id[]=${message.id}" />"><span
                                                    class="label label-sm label-warning">重发</span></a>
                                        </c:if>
                                    </td>
                                    <td>${message.content}</td>
                                    <td>
                                        <c:if test="${message.status==0}">
                                        发送失败
                                        </c:if>
                                        <c:if test="${message.status>0}">
                                            已发送
                                        </c:if>
                                    </td>

                                    <td>
                                        <fmt:formatDate value="${message.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/>

                                    </td>

                                    <td>
                                        <div
                                                class="visible-md visible-lg hidden-sm hidden-xs btn-group">

                                            <a class="btn btn-xs btn-danger" data-rel="tooltip" data-placement="bottom"
                                               title="删除"
                                               href="<utils:url url="/messages/delete?id[]=${message.id}" />">
                                                <i class="icon-trash bigger-120"></i>
                                            </a>
                                        </div>
                                        <div class="visible-xs visible-sm hidden-md hidden-lg">
                                            <div class="inline position-relative">
                                                <button class="btn btn-minier btn-primary dropdown-toggle"
                                                        data-toggle="dropdown">
                                                    <i class="icon-cog icon-only bigger-110"></i>
                                                </button>

                                                <ul
                                                        class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">


                                                    <li>
                                                        <a href="<utils:url url="/messages/delete?id[]=${message.id}" />"
                                                           class="tooltip-error"
                                                           data-rel="tooltip" title="删除"> <span class="red">
															<i class="icon-trash bigger-120"></i> </span> </a></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </td>

                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                        <div class="row">
                            <div class="col-xs-12 col-sm-4 isst-table-form-actions">
                                <a href="<utils:url url="/messages/delete" />" class="btn btn-primary">批量删除</a>
                            </div>

                            <div class="col-sm-8 col-xs-12">
                                <!-- pager -->
                                <div id="pager" class="pull-right">
                                    <pagination:paging page="${messages.page}" total="${messages.total}"
                                                       size="${messages.pageSize}"/>
                                </div>
                                <!-- end pager -->
                            </div>
                        </div>
                    </form>
                </c:when>

                <c:otherwise>
                    <div class="alert alert-warning">
                        <button type="button" class="close" data-dismiss="alert">
                            <i class="icon-remove"></i>
                        </button>
                        <strong>没有记录！</strong>
                        <br/>
                        <br/>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</layout:override>

<%@ include file="../layouts/main.jsp" %>
<%@ page language="java" import="java.util.*, cn.edu.zju.isst.common.FlashMessage" pageEncoding="UTF-8"%>
<%
Object flash = request.getAttribute("flash_message");
String status = "success";
String message = null;

if (null != flash) {
	if (flash instanceof FlashMessage) {
		FlashMessage fm = (FlashMessage) flash;
		status = fm.getStatus().equals("error") ? "danger" : fm.getStatus();
		message = fm.getMessage();
	} else {
		message = (String) flash;
	}
%>
<div class="alert alert-block alert-<%=status%>">
            <button type="button" class="close" data-dismiss="alert">
                <i class="icon-remove"></i>
            </button>
        <%=message%>
</div>
<%
}
%>
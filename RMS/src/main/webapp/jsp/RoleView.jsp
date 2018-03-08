
<%@page import="in.co.mss.rms.models.RoleModel"%>
<%@page import="in.co.mss.rms.controller.RoleCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.ROLE_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.mss.rms.bean.RoleBean"
			scope="request"></jsp:useBean>

		<center>
			<%
				RoleModel model = new RoleModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit Role</h1>
			<%
				} else {
			%>
			<h1>Add Role</h1>
			<%
				}
			%>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>
			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Name*</th>
					<td><input type="text" name="name" size="18.7"
						value="<%=DataUtility.getStringData(bean.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Description*</th>
					<td><textarea style="width: 160px;border-width: 2px" rows="2" name="description"><%=DataUtility.getStringData(bean.getDescription())%></textarea> <font
						color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				<%-- <tr>
					<th align="left">Description*</th>
					<td><input type="text" name="description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr> --%>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=RoleCtl.OP_SAVE%>">&emsp; &emsp; <input
						type="submit" name="operation" value="<%=RoleCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>


<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<%@page import="in.co.mss.rms.controller.UserListCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.bean.UserBean"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<%@page import="in.co.mss.rms.bean.DropdownListBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<html>
<head>
<script type="text/javascript">
	function togglecheckboxes(master, group) {
		var cbarray = document.getElementsByName(group);
		for ( var i = 0; i < cbarray.length; i++) {
			cbarray[i].checked = master.checked;
		}

	}
</script>
<body>

	<%@include file="Header.jsp"%>

	<%
		UserBean userbean = (UserBean) session.getAttribute("user");
		/* long roleId= */
	%>
	<center>
		<h1>User List</h1>

		<h2>
			<font color="GREEN"><%=ServletUtility.getSuccessMessage(request)%></font>
		</h2>
		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
			</font>
		</H2>



		<form action="<%=ORSView.USER_LIST_CTL%>" method="POST" name="list">
			<jsp:useBean id="bean" class="in.co.mss.rms.bean.UserBean"
				scope="request"></jsp:useBean>


			<%
				List l = (List) request.getAttribute("roleList");
			%>


			<%-- <center>
				<label><b>Role : <%=HTMLUtility.getList("roleId",
					String.valueOf(bean.getRoleId()), l)%></b></label>
			</center>
			<br> --%>

			<table width="100%">
				<%-- <tr>

					<td align="center"><label><b>Role : </b></label><%=HTMLUtility.getList("roleId",
					String.valueOf(bean.getRoleId()), l)%></td>
				</tr>
				</br>
 --%>
				<tr>
					<td align="center"><label><b>FirstName :</b></label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">
						<%-- <font color="RED">
                        <%=ServletUtility.getErrorMessage("firstName",request) %></font> --%>
						&emsp; <label><b>LoginId:</b></label> <input type="text"
						name="login"
						value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
						<%-- <font color="REd">
                        <%=ServletUtility.getErrorMessage("login",request) %></font> --%>
						<label><b>Role : <%=HTMLUtility.getList("roleId",
					String.valueOf(bean.getRoleId()), l)%></b></label> &emsp; <input type="submit"
						name="operation" value="<%=UserListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>

			<table border="1" width="100%">
				<tr>

					<td><input type="checkbox" id="master" disabled="disabled"
						onclick="togglecheckboxes(this,'ids')"> <b>Select</b></td>

					<th>S.No</th>
					<th>Role Name</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>LoginId</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Edit</th>
				</tr>

				<tr>
					<%--  <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td> --%>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator it = list.iterator();
					while (it.hasNext()) {
						bean = (UserBean) it.next();
				%>
				<%-- <%
					SimpleDateFormat dateformat = new SimpleDateFormat("dd/mm/yyyy");
						Date d = bean.getDob();
						dateformat.format(d);
				%> --%>
				<tr>
					<%-- <%SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); %> --%>
					<%
						if (userBean.getLogin().equals(bean.getLogin())) {
					%>
					<td><input type="checkbox" name="ids" disabled="disabled"
						value="<%=bean.getId()%>"></td>

					<%
						} else {
					%>
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<%
						}
					%>
					<td><%=index++%></td>
					<td><%=bean.getRoleName()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDob()%></td>

					<td><a href="<%=ORSView.USER_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1 || pageNo == 0) {
					%>
					<td align="left"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>
					<%
						if (list.size() < 10) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</head>
</html>

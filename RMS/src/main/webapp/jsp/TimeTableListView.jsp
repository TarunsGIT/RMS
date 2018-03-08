
<%@page import="in.co.mss.rms.controller.TimeTableListCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.bean.TimeTableBean"%>
<%@page import="in.co.mss.rms.bean.UserBean"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
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
	    <%
		 if (userbean.getRoleId() == RoleBean.ADMIN) {
		%>
		<h1>Time Table List</h1>
        <%
		 } else {
        %>
        <h1>Exam Schedule</h1> 
        <%} %>		
		<h2>
			<font color="GREEN"><%=ServletUtility.getSuccessMessage(request)%></font>
		</h2>

		<h2>
			<font color="RED"><%=ServletUtility.getErrorMessage(request)%></font>
		</h2>

		<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="POST">
			<table width="100%">
				<tr>
					<td align="center"><label><b>Course :</b></label> <input
						type="text" name="Course"
						value="<%=ServletUtility.getParameter("Course", request)%>">
						&nbsp; <input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					
					<%
						if (userbean.getRoleId() == RoleBean.ADMIN) {
					%>
					<td><input type="checkbox" id="master"
						onclick="togglecheckboxes(this,'ids')"><b>Select</b></td>
					<%
						}
					%>
					<th>S.No</th>
					<th>Course</th>
					<th>Subject</th>
					<th>Examination Date</th>
					<th>Time</th>
					<th>Day</th>
					<%
						if (userbean.getRoleId() == RoleBean.ADMIN) {
					%>
					<th>Edit</th>
					<%
						}
					%>
				</tr>
				<%--  <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr> --%>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<TimeTableBean> it = list.iterator();
					while (it.hasNext()) {
						TimeTableBean bean = it.next();
				%>
				<tr>
				   <%
						if (userbean.getRoleId() == RoleBean.ADMIN) {
					%>
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<%
						}
					%>
					<td><%=index++%></td>
					<%-- <td><%=bean.getId()%></td> --%>
					<td><%=bean.getCourse()%></td>
					<td><%=bean.getSubject()%></td>
					<td><%=bean.getExaminationDate()%></td>
					<td><%=bean.getTime()%></td>
					<td><%=bean.getDay()%></td>
					<%
						if (userbean.getRoleId() == RoleBean.ADMIN) {
					%>
					<td><a href="<%=ORSView.TIMETABLE_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
					<%
						}
					%>
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
						disabled="disabled" value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<%
						if (userbean.getRoleId() ==  RoleBean.ADMIN) {
					%>
					<td><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEW%>"></td>
					<%
						}
					%>
					<%
						if (userbean.getRoleId() == RoleBean.ADMIN) {
					%>	
					<td align="center"><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_DELETE%>"></td>
					<%
						}
					%>	
					<%
						if (list.size() < 10) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=TimeTableListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=TimeTableListCtl.OP_NEXT%>"></td>
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

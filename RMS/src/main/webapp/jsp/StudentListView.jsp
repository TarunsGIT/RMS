
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="in.co.mss.rms.controller.StudentListCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<%@page import="in.co.mss.rms.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Date"%>
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
	<center>
		<h1>Student List</h1>
		<h2>
			<font color="GREEN"><%=ServletUtility.getSuccessMessage(request)%></font>
		</h2>
		<h2>
			<font color="RED"><%=ServletUtility.getErrorMessage(request)%></font>
		</h2>

		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="POST">
			<table width="100%">
				<tr>
					<td align="center"><label><b>FirstName :</b></label> <input
						type="text" name="firstName"
						value="<%=ServletUtility.getParameter("firstName", request)%>">&emsp;
						<%-- <font color="RED">
                        <%ServletUtility.getErrorMessage(request); %></font> --%>
						<label><b>LastName :</b></label> <input type="text"
						name="lastName"
						value="<%=ServletUtility.getParameter("lastName", request)%>">&emsp;
						<%-- <font color="RED">
                        <%ServletUtility.getErrorMessage(request); %></font> --%>
						<label><b>Email_Id:</b></label> <input type="text" name="email"
						value="<%=ServletUtility.getParameter("email", request)%>">&emsp;<%-- <font
						color="RED"> <%ServletUtility.getErrorMessage(request);%></font> --%>
						<input type="submit" alt="SEARCH" name="operation"
						value="<%=StudentListCtl.OP_SEARCH%>"></td>

				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<td><input type="checkbox" id="master"
						onclick="togglecheckboxes(this,'ids')"><b>Select</b></td>
					<th>S.No</th>
					<!-- <th>ID.</th> -->
					<th>College</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date Of Birth</th>
					<th>Mobile No</th>
					<th>Email ID</th>
					<th>Edit</th>
				</tr>
				<%-- <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr> --%>
				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					List list = ServletUtility.getList(request);
					Iterator<StudentBean> it = list.iterator();

					while (it.hasNext()) {

						StudentBean bean = it.next();
				%>
				<%
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						Date dob = bean.getDob();
						String d = sdf.format(dob);
				%>
				<tr>
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>">
					<td><%=index++%></td>
					<%-- <td><%=bean.getId()%></td> --%>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getEmail()%></td>

					<td><a href="<%=ORSView.STUDENT_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_DELETE%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEW%>"> <%
 	if (list.size() < 10) {
 %>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=StudentListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=StudentListCtl.OP_NEXT%>"></td>
					<%
						}
					%>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"><input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>
	</center>
	<%@include file="Footer.jsp"%>

</body>
</head>
</html>

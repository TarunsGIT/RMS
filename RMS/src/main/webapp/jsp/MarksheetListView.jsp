
<%@page import="in.co.mss.rms.controller.MarksheetListCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.controller.ORSView"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<%@page import="in.co.mss.rms.bean.MarksheetBean"%>
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
	<center>
		<h1>Marksheet List</h1>
		<h2>
			<font color="GREEN"><%=ServletUtility.getSuccessMessage(request)%></font>
		</h2>

		<h2>
			<font color="RED"><%=ServletUtility.getErrorMessage(request)%></font>
		</h2>

		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

			<table width="100%">
				<tr>
					<td align="center"><label><b> Name :</b></label> <input
						type="text" name="name"
						value="<%=ServletUtility.getParameter("name", request)%>"><font
						color="RED"> <%
 	ServletUtility.getErrorMessage("name", request);
 %></font>
						&emsp; &emsp; <label><b>RollNo :</b></label> <input type="text"
						name="rollNo"
						value="<%=ServletUtility.getParameter("rollNo", request)%>"><font
						color="RED"> <%
 	ServletUtility.getErrorMessage("rollNo", request);
 %></font>&emsp;
						<input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_SEARCH%>"></td>
				</tr>
			</table>
			<br>
			<table border="1" width="100%">
				<tr>
					<td><input type="checkbox" id="master"
						onclick="togglecheckboxes(this,'ids')"><b>Select</b></td>
					<th>S.No</th>
					<th>RollNo</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
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
					Iterator<MarksheetBean> it = list.iterator();

					while (it.hasNext()) {

						MarksheetBean bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>

					<td><a href="<%=ORSView.MARKSHEET_CTL%>?id=<%=bean.getId()%>">Edit</a></td>
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
						disabled="disabled" value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						} else {
					%>
					<td align="left"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>
					<td><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEW%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_DELETE%>"></td>
					<%
						if (list.size() < 10) {
					%>
					<td align="right"><input type="submit" name="operation"
						align="right" disabled="disabled"
						value="<%=MarksheetListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=MarksheetListCtl.OP_NEXT%>"></td>
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

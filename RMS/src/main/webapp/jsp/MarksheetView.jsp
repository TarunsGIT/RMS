
<%@page import="in.co.mss.rms.models.MarksheetModel"%>
<%@page import="in.co.mss.rms.controller.MarksheetCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.mss.rms.util.HTMLUtility"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>

<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>

	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.mss.rms.bean.MarksheetBean"
		scope="request"></jsp:useBean>

	<%
		List l = (List) request.getAttribute("studentList");
	%>

	<center>

		<%
			MarksheetModel model = new MarksheetModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			if (id > 0) {
		%>

		<h1>Edit Marksheet</h1>
		<%
			} else {
		%>
		<h1>Add Marksheet</h1>
		<%
			}
		%>


		<H2>
			<font color="red"> <%=ServletUtility.getErrorMessage(request)%></font>
		</H2>
		<H2>
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
		</H2>

		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<tr>
					<th align="left">Rollno*</th>
					<td><input type="text" name="rollNo" id="rol"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>> <font
						color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
					</font>
					</td>

				</tr>
				<tr>
					<th align="left">Name*</th>
					<td><%=HTMLUtility.getList("studentId",
					String.valueOf(bean.getStudentId()), l)%><font color="RED">
							<%=ServletUtility.getErrorMessage("Name", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Physics*</th>
					<td><input type="text" name="physics"
						value="<%=DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Chemistry*</th>
					<td><input type="text" name="chemistry"
						value="<%=DataUtility.getStringData(bean.getChemistry())
					.equals("0") ? "" : DataUtility.getStringData(bean
					.getChemistry())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Maths*</th>
					<td><input type="text" name="maths"
						value="<%=DataUtility.getStringData(bean.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(bean.getMaths())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("maths", request)%></font></td>

				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						onclick="return AllowSingleSpaceNotInFirstAndLast();"
						value="<%=MarksheetCtl.OP_SAVE%>">&emsp;&emsp; <%-- <%
 	  if (bean.getId() > 0) {
 %><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_DELETE%>"> <%
 	} --%> <!-- %> --> &nbsp; <input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
		</form>
	</center>
	<%@ include file="Footer.jsp"%>
	<script type="text/javascript">
		function AllowSingleSpaceNotInFirstAndLast() {
			var obj = document.getElementById('rol');
			obj.value = obj.value.replace(/^\s+|\s+$/g, "");
			var CharArray = obj.value.split(" ");
			if (CharArray.length > 1) {
				alert("Roll No. cannot contain space");
				return false;
			}
			return true;
		}
	</script>

</body>
</html>

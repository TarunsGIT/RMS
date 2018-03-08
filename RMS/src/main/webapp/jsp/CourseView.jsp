
<%@page import="in.co.mss.rms.models.CourseModel"%>
<%@page import="in.co.mss.rms.controller.CourseCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<head>

</head>
<body>
	<form action="<%=ORSView.COURSE_CTL%>" method="POST">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.mss.rms.bean.CourseBean"
			scope="request"></jsp:useBean>
		<input type="hidden" name="id" value="<%=bean.getId()%>">
		<center>
			<%
				CourseModel model = new CourseModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit Course</h1>
			<%
				} else {
			%>
			<h1>Add Course</h1>
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



			<table>
				<tr>
					<th align="left">Name*</th>
					<td><input type="text" name="name" size="19"
						value="<%=DataUtility.getStringData(bean.getName())%>"><font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>



				<tr>
					<th align="left">Description*</th>
					<td><textarea style="width: 167px;border-width: 2px" rows="2" name="description"><%=DataUtility.getStringData(bean.getDescription())%></textarea><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>
				<%-- <tr>
					<th align="left">Description*</th>
					<td><input type="text" name="description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr> --%>
				<tr>
					<th align="left">Duration*(in year)</th>
					<td><input type="text" name="duration" size="19"
						value="<%=DataUtility.getStringData(bean.getDuration()).equals("0") ? ""
					: DataUtility.getStringData(bean.getDuration())%>"><font
						color="RED"> <%=ServletUtility.getErrorMessage("duration", request)%>
					</font>
				</tr>
				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=CourseCtl.OP_SAVE%>">&emsp; <input type="submit"
						name="operation" value="<%=CourseCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	<%@ include file="Footer.jsp"%>
	<%-- 	</center>
	
	<script type="text/javascript">
		function Duration() {
			var duration = document.getElementById('dur');
			var limit = /^[1-6]^/;
			if (!limit.test(duration)) {
				alert("Duration Of Course Cannot be more than 6 Years");
				return false;

			}
			return true;

		}
	</script> --%>
</body>
</html>

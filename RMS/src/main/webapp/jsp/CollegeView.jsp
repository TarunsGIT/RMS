
<%@page import="in.co.mss.rms.models.CollegeModel"%>
<%@page import="in.co.mss.rms.controller.CollegeCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>
	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="in.co.mss.rms.bean.CollegeBean"
			scope="request"></jsp:useBean>

		<center>
			<%
				CollegeModel model = new CollegeModel();
				long id = DataUtility.getLong(request.getParameter("id"));
				if (id > 0) {
			%>

			<h1>Edit College</h1>
			<%
				} else {
			%>
			<h1>Add College</h1>
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
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>
				 <tr>
				<th align="left">Address*</th>
				<td><textarea style="border-width: 2px;width: 172px" rows="" name="address" spellcheck="true"><%=DataUtility.getStringData(bean.getAddress())%></textarea> <font 
				color="red"><%=ServletUtility.getErrorMessage("address",request) %>
				
				</font>
				</tr>
				<%-- <tr>
					<th align="left">Address*</th>
					<td><input type="text" name="address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr> --%>
				<tr>
					<th align="left">State*</th>
					<td><input type="text" name="state"
						value="<%=DataUtility.getStringData(bean.getState())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>
				<tr>
					<th align="left">City*</th>
					<td><input type="text" name="city"
						value="<%=DataUtility.getStringData(bean.getCity())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>
				<tr>
					<th align="left">PhoneNo*</th>
					<td><input type="text" name="phoneNo"
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"><font
						color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<tr>
					<th></th>
					<td colspan="2"><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>"> <%--  <%
                     if (bean.getId() > 0) {
                 %> &emsp;<input type="submit" name="operation"
                        value="<%=CollegeCtl.OP_DELETE%>"> <%
                     }
                 %> --%>&emsp; <input type="submit" name="operation"
						value="<%=CollegeCtl.OP_CANCEL%>"></td>
				</tr>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>

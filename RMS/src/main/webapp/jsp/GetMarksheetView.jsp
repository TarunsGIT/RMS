
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="in.co.mss.rms.controller.GetMarksheetCtl"%>
<%@page import="in.co.mss.rms.controller.BaseCtl"%>
<%@page import="in.co.mss.rms.util.DataUtility"%>
<%@page import="in.co.mss.rms.util.ServletUtility"%>
<html>
<body>
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.mss.rms.bean.MarksheetBean"
		scope="request"></jsp:useBean>

	<center>
		<h1>Get Marksheet</h1>


		<h2>
			<font color="red"> <%=ServletUtility.getErrorMessage("rollNo", request)%></font>
		</h2>
		<h2>
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
		</h2>
		<h2>
			<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
		</h2>

		<form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="POST">

			<input type="hidden" name="id" value="<%=bean.getId()%>">
			<table>
				<tr>
					<th>RollNo* :</th>
					<td><input type="text" name="rollNo"
						value="<%=ServletUtility.getParameter("rollNo", request)%>"></td>&emsp;
					<td><input type="submit" name="operation"
						value="<%=GetMarksheetCtl.OP_GO%>"></td>
					<%-- <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font>
 --%>
				</tr>
			</table>
			<%
				if (bean.getRollNo() != null
						&& bean.getRollNo().trim().length() > 0) {
			%>
			<br>
			<table border="2" width="20%" bordercolor="#D8AB00">
				<tr>
					<td>Rollno</td>
					<td><%=DataUtility.getStringData(bean.getRollNo())%></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><%=DataUtility.getStringData(bean.getName())%></td>
				</tr>
				<tr>
					<td>Physics</td>
					<td><%=DataUtility.getStringData(bean.getPhysics())%></td>
				</tr>
				<tr>
					<td>Chemistry</td>
					<td><%=DataUtility.getStringData(bean.getChemistry())%></td>
				</tr>
				<tr>
					<td>Maths</td>
					<td><%=DataUtility.getStringData(bean.getMaths())%></td>

				</tr>
				<tr>
					<td>Total</td>
					<td><%=DataUtility.getStringData(bean.getMaths()
						+ bean.getChemistry() + bean.getPhysics())%></td>
				</tr>

				<%
					double TOTAL = bean.getPhysics() + bean.getMaths()
								+ bean.getChemistry();
						double Percentage = (TOTAL / 300) * 100;
				%>
				<tr>
					<td>Percantage Secured</td>
					<td><%=Math.round(Percentage) + "%"%></td>

				</tr>

				<%
					String division = null;
						if (Percentage <= 100 && Percentage >= 60) {
							division = "First";
						} else if (Percentage <= 59 && Percentage >= 45) {
							division = "Second";

						} else {
							division = "Third";
						}
				%>
				<tr>
					<td>Division</td>
					<td><%=division%></td>
				</tr>
				<%
					Date todaysDate = new Date();
						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				%>
				<tr>
					<td>Genrated on</td>
					<td><%=dateFormat.format(todaysDate)%></td>
				</tr>


			</table>


			<%
				}
			%>
		</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>

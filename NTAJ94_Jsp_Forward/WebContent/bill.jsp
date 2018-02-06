<%
String name=null;
Float price=0.0f ,billAmt=0.0f;
int qty=0;

name=request.getParameter("iname");
price=Float.parseFloat(request.getParameter("price"));
qty=Integer.parseInt(request.getParameter("qty"));

billAmt=price*qty;
if(billAmt>=50000){%>
<jsp:forward page="discount.jsp">
<jsp:param name="amt"  value="<%=billAmt %>"/>
<jsp:param name="iname"  value="<%=name%>"/>
</jsp:forward>


<%}
else 
{%>
Item Name:<%=name%>
Total Bill:<%=billAmt%>
<a href="form.html">Home</a>

<%}%>







<%
String name=request.getParameter("iname");
float billAmt=Float.parseFloat(request.getParameter("amt"));
float discount=0.0f;

float newAmt=0.0f;
// Discount Logic
discount=billAmt*0.3f;
newAmt=billAmt-discount;
 %>
 Item Name:<%=name %><br>
Amount:<%=billAmt %><br>
 Discount :<%=discount %>
 Total Amount:<%=newAmt %>
 
<a href="form.html">Home</a>




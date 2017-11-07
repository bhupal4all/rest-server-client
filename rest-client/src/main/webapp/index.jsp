<html>
<head><title>First JSP</title></head>
<body>
  <%
    double num = Math.random();
    if (num > 0.95) {
  %>
      <h2>You'll have a luck day!</h2><p>(<%= num %>)</p>
  <%
    } else {
  %>
      <h2>Well, life goes on ... </h2><p>(<%= num %>)</p>
  <%
    }
  %>
  <a href="<%= request.getRequestURI() %>"><h3>Try Again</h3></a>
  
  <%@ page import="com.sun.jersey.api.client.Client" %>
  <%@ page import="com.sun.jersey.api.client.ClientResponse" %>
  <%@ page import="com.sun.jersey.api.client.WebResource" %>
  
  <%
  
  String output = "";
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/sampleservice/rest/post");

			String input = "{\"username\":\"defaultuser\",\"password\":\"deafaultpassword\"}";

			ClientResponse res = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (res.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ res.getStatus());
			}

			output = res.getEntity(String.class);
		} catch (Exception e) {
			output = "Exception:: " + e.getMessage();
		}
  
  %>
  
  <h3><%=output%><h3>
  
  
</body>
</html>
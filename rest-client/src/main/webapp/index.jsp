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
  <%@ page import="com.sun.jersey.api.representation.Form" %>
  
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
  
  
<form action="/client/rest/postform" method="post">
    <label for="username">Username: </label>
    <input id="username" type="text" name="username" />
    <input type="submit" value="Submit" />
</form>

  <%
  
  String output2 = "";
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/client/rest/postform");

			Form f = new Form();    
			f.add("username", "FormUser");  
			f.add("password", "FormPassword");

			ClientResponse res = webResource.post(ClientResponse.class, f);

			output2 = res.getEntity(String.class);
		} catch (Exception e) {
			output2 = "Exception:: " + e.getMessage();
		}
  
  %>
  
  <h3>Sending Form data from code and response ==> <%=output2%><h3>
  
  <h3>Here Current Application Info</h3>
  <ul>
	<li>Protocol: <%=request.getProtocol()%></li>
	<li>is Secure: <%=request.isSecure()%>, Used <%=request.isSecure()?"Https":"Http"%></li>
	<li>Server Name: <%=request.getServerName()%></li>
	<li>Server Port: <%=request.getServerPort()%></li>
	<li>Context Path: <%=request.getContextPath()%></li>
  </ul>
  
  
  <h3>Window Open with Form Data</h3>
  
  <script>
	function OpenWindowWithPost(url, windowoption, type, params)
	{
		var form = document.createElement("form");
		form.setAttribute("method", "post");
		form.setAttribute("action", url);
		form.setAttribute("target", type);

		for (var i in params) {
			if (params.hasOwnProperty(i)) {
				var input = document.createElement('input');
				input.type = 'hidden';
				input.name = i;
				input.value = params[i];
				form.appendChild(input);
			}
		}
		
		document.body.appendChild(form);
		window.open("", type, windowoption);
		form.submit();
		document.body.removeChild(form);
	}

	function openPopupPage(url) {
		var param = {'username':'window_username', 'password':'window_password'};
		OpenWindowWithPost(url, "width=1000, height=600, left=100, top=100, resizable=yes, scrollbars=yes", "newfile", param);
	}
  </script>
  
  <a href="javascript:openPopupPage('/client/rest/postform')">New Window With Post Data</a>
  <a href="javascript:openPopupPage('/client/rest')">New Window With Page Data</a>

</body>
</html>
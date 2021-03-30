<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ page import="sps.team48.HelloInfo" %>
    <html>

    <head>
      <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
      <title>Hello App Engine Flexible</title>
    </head>

    <body>
      <h1>Hello App Engine -- Flexible!</h1>

      <p>This is <%= HelloInfo.getInfo() %>.</p>
      <table>
        <tr>
          <td colspan="2" style="font-weight:bold;">Available Servlets:</td>
        </tr>
        <tr>
          <td><a href='/hello'>The servlet</a></td>
        </tr>
      </table>

    </body>

    </html>
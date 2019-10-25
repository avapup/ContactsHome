<%@ page import="java.util.List" %>
<%@ page import="net.homecredit.trainee.oberfrancova.entity.PhoneNumberPrefix" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Romana.Oberfrancova
  Date: 11.10.2019
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<jsp:useBean id="contact"
             type="net.homecredit.trainee.oberfrancova.entity.Contact"
             scope="request"/>
<jstl:url var="cssLink" value="/css/style.css"/>

<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>DetailOfContact</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="${cssLink}"/>
</head>
<body>

    <h1>${localizedExpressions[0]}</h1>

    <form method="post" class="editForm">

        <input type="hidden" name="contactId" id="contactId" value="${contact.contactId}" required/>

        <label for="firstName">${localizedExpressions[1]}</label>
        <input type="text" name="firstName" id="firstName" value="${contact.person.firstName}" required/>

        <label for="lastName">${localizedExpressions[2]}</label>
        <input type="text" name="lastName" id="lastName" value="${contact.person.lastName}" required/>

        <label for="number">${localizedExpressions[3]}</label>
        <div class="editPhoneNumberPart">
            <input type="text" name="prefix" id="prefix" class="editPrefix" placeholder="+42x"
                   value="${contact.phoneNumber.prefix.toString()}" required/>
            <input type="text" name="number" id="number" class="editNumber"
                   minlength="9" maxlength="9" value="${contact.phoneNumber.number}" required/>
        </div>

        <jstl:if test="${not empty contact.person.firstName}">
            <input type="submit" value="${localizedExpressions[5]}" class="myButton addAndUpdateButton"/>
        </jstl:if>

        <jstl:if test="${empty contact.person.firstName}">
            <input type="submit" value="${localizedExpressions[4]}" class="myButton addAndUpdateButton"/>
        </jstl:if>



    </form>

</body>
</html>

<%--
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
    <title>Detail of Contact</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="${cssLink}"/>
</head>
<body>

    <h1>Contact Details</h1>

    <form method="post" class="editForm">

        <input type="hidden" name="contactId" id="contactId" value="${contact.contactId}" required/>

        <label for="firstName">First Name</label>
        <input type="text" name="firstName" id="firstName" value="${contact.person.firstName}" required/>

        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" id="lastName" value="${contact.person.lastName}" required/>

        <label for="phoneNumber">Phone Number</label>
        <input type="text" name="phoneNumber" id="phoneNumber" minlength="9" maxlength="14" value="${contact.phoneNumber.phoneNumber}" required/>

        <jstl:if test="${empty contact.person.firstName}">
            <input type="submit" value="add" class="myButton"/>
        </jstl:if>

        <jstl:if test="${not empty contact.person.firstName}">
            <input type="submit" value="update" class="myButton"/>
        </jstl:if>

    </form>

</body>
</html>

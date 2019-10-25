<%--
  Created by IntelliJ IDEA.
  User: Romana.Oberfrancova
  Date: 01.10.2019
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<jsp:useBean id="contactList"
             type="java.util.List<net.homecredit.trainee.oberfrancova.entity.Contact>"
             scope="request"/>
<jstl:url var="cssLink" value="/css/style.css"/>


<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>ContactList</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" type="text/css" href="${cssLink}"/>
</head>
<body>




        <h1>${localizedExpressions[0]}</h1>


        <jstl:url var="servlet" value="/contacts/new.html"/>
        <h2><a href="${servlet}" class="myButton">${localizedExpressions[1]}</a></h2>

        <table class="contactsTable">
            <thead>
                <tr>
                    <jstl:forEach var="expression" items="${localizedExpressions}" begin="2" end="6">
                        <th>${expression}</th>
                    </jstl:forEach>

                </tr>
            </thead>
            <jstl:forEach var="contact" items="${contactList}">
                <tbody>
                    <tr>
                        <td class="tableIdColumn">${contact.contactId}</td>
                        <td><jstl:out value="${contact.person.firstName}"/></td>
                        <td><jstl:out value="${contact.person.lastName}"/></td>
                        <td class="tableNumberColumn">
                            <jstl:out value="${contact.phoneNumber.prefix.toString()}
                                                ${contact.phoneNumber.number.substring(0, 3)}
                                                ${contact.phoneNumber.number.substring(3, 6)}
                                                ${contact.phoneNumber.number.substring(6, 9)}"/>
                        </td>
                        <td>
                            <div class="tableActionColumn">
                                <jstl:url var="editLink" value="/contacts/edit.html">
                                    <jstl:param name="id" value="${contact.contactId}"/>
                                </jstl:url>
                                <a href="${editLink}" class="myButton tableButton editButton">${localizedExpressions[7]}</a>

                                <jstl:url var="deleteLink" value="/contacts/delete.html"/>
                                <form method="post" action="${deleteLink}">
                                    <input type="hidden" name="id" value="${contact.contactId}"/>
                                    <input type="submit" class="myButton tableButton" value="${localizedExpressions[8]}"/>
                                </form>
                            </div>

                        </td>
                    </tr>
                </tbody>
            </jstl:forEach>

        </table>



    <div class="pagination">
        <a href="#">&laquo;</a>
        <a href="#">1</a>
        <a class="active" href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">&raquo;</a>
    </div>

</body>
</html>

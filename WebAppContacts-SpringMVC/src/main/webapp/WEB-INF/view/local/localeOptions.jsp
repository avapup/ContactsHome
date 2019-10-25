<%--
  Created by IntelliJ IDEA.
  User: Romana.Oberfrancova
  Date: 25.10.2019
  Time: 4:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="jstl"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
    <title>LanguageOptions</title>
</head>
<body>

    <h2>Language options: </h2>

    <jstl:forEach var="language" items="${localeList}">

        <div class="languages">
            <jstl:url var="localeLink" value="/contacts/all/locale.html">
                <jstl:param name="locale" value="${language.toString()}"/>
            </jstl:url>
            <h3>
                <a href="${localeLink}" class="languageLink">${language.toString()}</a>
            </h3>
        </div>


    </jstl:forEach>

</body>
</html>

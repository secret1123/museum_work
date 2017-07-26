<!DOCTYPE html>
<%@ page pageEncoding="UTF-8"%>
<%@ include file="/commons/inc.jsp"%>
<html>
<head>
    <title>museum page</title>
</head>
<body>
<table border="1">
    <tr>
        <th>COUNT</th>
        <th>NAME</th>
        <th>LOGO</th>
        <th>PICTURE</th>
        <th>ADDRESS</th>
        <th>WORKS</th>
    </tr>
    <c:forEach var="museum" items="${sessionScope.pagination.list}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
            <td>${museum.name}</td>
            <td>${museum.logo}</td>
            <td>${museum.picture}</td>
            <td>${museum.address}</td>
            <td>
                <c:forEach var="work" items="${museum.works}">
                    ${work.title}
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>
<c:import url="${ctx}/commons/page.jsp">
    <c:param name="path" value="museum/${sessionScope.pagination.statement}"/>
</c:import>
</body>
</html>
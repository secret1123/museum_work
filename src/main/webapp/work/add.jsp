<!DOCTYPE html><%@ page pageEncoding="UTF-8"%><%@ include file="/commons/inc.jsp"%>
<html>
<head>
    <title>work add page</title>
</head>
<body>
<h1>ADD Work</h1>
<form action="${ctx}/work/create" method="post">
    <input name="title" placeholder="TITLE"><br>
    <input name="picture" placeholder="PICTURE"><br>
    <input name="artist" placeholder="ARTIST"><br>
    <input name="year" placeholder="YEAR"><br>
    <input name="museumId" placeholder="MUSEUMID"><br>
    <input type="submit" value="ADD">
</form>
<c:import url="${ctx}/commons/page.jsp">
    <c:param name="path" value="work/${sessionScope.pagination.statement}"/>
</c:import>
</body>
</html>
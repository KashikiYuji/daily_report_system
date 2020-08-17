<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:if test="${errors != null}">
    <div id="flush_error">
        入力内容にエラーがあります。<br />
        <c:forEach var="error" items="${errors}">
            ・<c:out value="${error}" /><br />
        </c:forEach>

    </div>
</c:if>
<label for="attendance_date">日付</label><br />
<fmt:parseDate var="parsed_attendance_day" pattern="yyyy-MM-dd" type="date" value="${attendance.attendance_date}" />
<fmt:formatDate var="attendance_day" value="${parsed_attendance_day}" pattern="yyyy-MM-dd" type="date" />
<input type="date" name="attendance_date" value="${attendance_day}" />
<br /><br />

<label for="name">氏名</label><br />
<c:out value="${sessionScope.login_employee.name}" />
<br /><br />


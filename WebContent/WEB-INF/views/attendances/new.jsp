<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">
        <h2>出退勤　登録ページ</h2>

       <form method="POST" action="<c:url value='/attendances/create' />">
            <c:import url="_form.jsp" />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">出勤</button>
       </form>
       <form method="POST" action="<c:url value='/attendances/update' />">
            <c:import url="_form.jsp" />
            <input type="hidden" name="_token" value="${_token}" />
            <button type="submit">退勤</button>
       </form>

        <p><a href="<c:url value='/attendances/index' />">一覧に戻る</a></p>
    </c:param>
</c:import>
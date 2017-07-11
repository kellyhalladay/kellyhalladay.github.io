<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="pageTitle" value="Home"/>
<%@ include file ="common/header.jspf" %>

<c:forEach var="park" items="${parks}">
	<div class="home-main">
		<div class="home-image">
				<c:url var="parkUrl" value="/parkDetails">
					<c:param name="parkCode" value="${park.parkCode}" /> 
				</c:url>
				<c:set var="image"><c:out value="${fn:toLowerCase(park.parkCode)}" /></c:set>
				<c:url var="imageUrl" value="/img/parks/${image}.jpg" />
			<a href="${parkUrl}"><img src="${imageUrl}" /></a>
		</div>
		<div class="main-content">
			<h2><a href="${parkUrl}"><c:out value="${park.parkName}" /></a></h2><br>
			<h4><c:out value="${park.state}" /></h4><br><br>
			<c:out value="${park.parkDescription}" />
		</div>
	</div>
</c:forEach>

<%@ include file ="common/footer.jspf" %>
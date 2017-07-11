<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="pageTitle" value="Top Parks"/>
<%@ include file ="common/header.jspf" %>

<div class="topparks-header">
<h2>And the winner is...</h2><br></br>

<p>All of our national parks! We need and love them all.</p><br>
We know, we know.  You want to know which parks people voted for, right?  Well, here are the top parks according to our National Park Geek visitors.
</p>
</div>

<c:forEach var="rankedpark" items="${topParks}">
	<div class="home-main">
		<div class="home-image">
				<c:url var="parkUrl" value="/parkDetails">
					<c:param name="parkCode" value="${rankedpark.key.parkCode}" /> 
				</c:url>
				<c:set var="image"><c:out value="${fn:toLowerCase(rankedpark.key.parkCode)}" /></c:set>
				<c:url var="imageUrl" value="/img/parks/${image}.jpg" />
			<a href="${parkUrl}"><img src="${imageUrl}" /></a>
		</div>
		<div class="main-content">
			<h2><a href="${parkUrl}"><c:out value="${rankedpark.key.parkName}" /></a></h2><br>
		<div class="main-content">
			<h4>Votes: <c:out value="${rankedpark.value}" /></h4><br>
		</div>
		</div>
	</div>
</c:forEach>

<%@ include file ="common/footer.jspf" %>
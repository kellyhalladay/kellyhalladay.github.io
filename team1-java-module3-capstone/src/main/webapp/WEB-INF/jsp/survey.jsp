<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="pageTitle" value="Survey"/>
<%@ include file ="common/header.jspf" %>

<div class="survey-main">
	<div class="survey-header">
		<h2>National Park Geek Survey</h2><br><br>

		<p>Wave those amber waves of grain and make your voice heard from sea to shining sea!<br>  
		Let us know which national park is YOUR favorite and we'll add it to our tally toward 
		our Nation's top parks.</p>
	</div>


	<c:url var="formAction" value="/survey" />
		<form:form action="${formAction}" method="POST" modelAttribute="survey">
			<div>
		      <h5><label for="emailAddress">Email:</label></h5>
		        <form:input path="emailAddress"/>
		        <form:errors path="emailAddress" cssClass="red-text"/>
	   		 </div>
			<div>
			<h5>Select your State:</h5>
				<form:select path="state">
					<form:option value="" label="Select" />
					<form:options items="${states}" />
				</form:select>
				<form:errors path="state" cssClass="red-text"/>
			</div>
			<div>
				<h5>Select a Park:</h5>
				<form:select path="parkCode">
					<form:option value="" label="Select" />
					<c:forEach items="${parks}" var="park">
					<form:option value="${park.parkCode}" label="${park.parkName}" />
					</c:forEach>
				</form:select>
				<form:errors path="parkCode" cssClass="red-text"/>
			</div>
			 <div>
			 <h5>Select your activity level:</h5>
				<c:forEach items="${activity}" var="level">
				<form:radiobutton path="activityLevel" value="${level}" /><c:out value="${level}" />
				</c:forEach>
				<form:errors path="activityLevel" cssClass="red-text"/>		
			</div>
			<div>
				<input type="submit" class="submit-btn" value="Submit" />
			</div>
		</form:form>
</div>
<%@ include file ="common/footer.jspf" %>
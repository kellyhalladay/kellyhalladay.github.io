<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<c:set var="pageTitle"><c:out value="${park.parkName}" /> Details</c:set>
<%@ include file ="common/header.jspf" %>

	<div class="stuff">
		<div class="background-image">
			<c:set var="image"><c:out value="${fn:toLowerCase(park.parkCode)}" /></c:set>
			<c:url var="imageUrl" value="/img/parks/${image}.jpg" />
			<img src="${imageUrl}" />
		</div>
		<div class="detail-image">
			<c:set var="image"><c:out value="${fn:toLowerCase(park.parkCode)}" /></c:set>
			<c:url var="imageUrl" value="/img/parks/${image}.jpg" />
			<img src="${imageUrl}" />
		</div>
	</div>
	
	<div class="detail-content">
		<div class="detail-header">
			<h2><c:out value="${park.parkName}" /></h2><br>
			<h4><c:out value="${park.state}" /></h4><br>
			<h5><i>Founded in <c:out value="${park.yearFounded}" /></i></h5>
		</div>
		
		<table>
			<tr>
				<th>Campsite Information:</th>
				<td></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<th>Cost of Entry</th>
				<td>$<c:out value="${park.entryFee}" /></td>
			</tr>
			<tr>
				<th>Climate</th>
				<td><c:out value="${park.climate}" /></td>
			</tr>
			<tr>
				<th>Number of Animal Species</th>
				<td><c:out value="${park.numberOfAnimalSpecies}" /></td>
			</tr>
			<tr>
				<th>Acreage</th>
				<td><c:out value="${park.acreage}" /> acres</td>
			</tr>
			<tr>
				<th>Elevation</th>
				<td><c:out value="${park.elevationInFeet}" /> feet</td>
			</tr>
			<tr>
				<th>Miles of Trail</th>
				<td><c:out value="${park.milesOfTrail}" /> miles</td>
			</tr>
			<tr>
				<th>Visitors Annually</th>
				<td><c:out value="${park.annualVisitorCount}" /></td>
			</tr>
			<tr>
				<th>Number of Campsites</th>
				<td><c:out value="${park.numberOfCampsites}" /></td>
			</tr>
			
		</table>

		<div class="quote">
			"<c:out value="${park.inspirationalQuote}" />" - <i><c:out value="${park.inspirationalQuoteSource}" /></i>
		</div>
		
		<c:out value="${park.parkDescription}" />
	</div>
	
	<div class="weather">
	<a name="forecast"></a>
	
	<!-- button -->
	<c:choose>
		<c:when test="${isCelsius}">
		<c:url var="formAction" value="/parkDetails#forecast"></c:url>
		
		<form method="GET" action="${formAction}">
			<input type="hidden" name="isCelsius" value="false" />
			<input type="hidden" name="parkCode" value="${park.parkCode}" />
			<input type="submit" class="toggle" value="Change to Fahrenheit" />
		</form>
		</c:when>
		
		<c:when test="${! isCelsius}">
		<c:url var="formAction" value="/parkDetails#forecast"></c:url>
		
		<form method="GET" action="${formAction}">
			<input type="hidden" name="isCelsius" value="true" />
			<input type="hidden" name="parkCode" value="${park.parkCode}" />
			<input type="submit" class="toggle" value="Change to Celsius" />
		</form>
		</c:when>
	</c:choose>
	
	<!-- Weather Content -->
	
	<c:forEach var="weather" items="${forecast}">
	<div class="weather-content">
		<div class="weather-image">
			<c:set var="weatherImage"><c:out value="${weather.forecast}" /></c:set>
			<c:url var="imageUrl" value="/img/weather/${fn:replace(weatherImage,' ','')}.png" />
			<img src="${imageUrl}" />
		</div>
	
	<div class="W-text-stuff">
	<!-- Display Date Start -->
			<div class="date">
				<c:choose>
					<c:when
						test="${weather.fiveDayForecastValue.getDate() % 10 == 1 && weather.fiveDayForecastValue.getDate() != 11}">
						<c:set var="suffix" value="st" />
					</c:when>
					<c:when
						test="${weather.fiveDayForecastValue.getDate() % 10 == 2 && weather.fiveDayForecastValue.getDate() != 12}">
						<c:set var="suffix" value="nd" />
					</c:when>
					<c:when
						test="${weather.fiveDayForecastValue.getDate() % 10 == 3 && weather.fiveDayForecastValue.getDate() != 13}">
						<c:set var="suffix" value="rd" />
					</c:when>
					<c:otherwise>
						<c:set var="suffix" value="th" />
					</c:otherwise>
				</c:choose>
				
				<fmt:formatDate value="${weather.fiveDayForecastValue}"
					pattern="EEEEE, MMMM d" />${suffix}
			</div>
	<!-- Display Date End -->
	
			<div class="temperature">
	<!-- Temperature High -->
				<c:choose>
					<c:when test="${isCelsius == 'true'}">
					High: <c:out value="${Math.round((weather.high - 32) * 0.5556)}" /> C
					</c:when>
					<c:otherwise> 
					High: <c:out value="${weather.high}" /> F
					</c:otherwise>
				</c:choose>
				<br>
	<!-- Temperature Low -->
				<c:choose>
					<c:when test="${isCelsius == 'true'}">
					Low: <c:out value="${Math.round((weather.low - 32) * 0.5556)}" /> C
					</c:when>
					<c:otherwise> 
					Low: <c:out value="${weather.low}" /> F
					</c:otherwise>
				</c:choose>
			</div>
			
			<div class="tips">
	<!-- Tips -->
				<c:choose>
					<c:when test="${weather.forecast == 'sunny'}">Pack Sunblock!</c:when>
					<c:when test="${weather.forecast == 'rain'}">Pack your rain gear! Waterproof shoes are a good idea too.</c:when>
					<c:when test="${weather.forecast == 'thunderstorms'}">Seek Shelter! Avoid hiking on exposed ridges.</c:when>
					<c:when test="${weather.forecast == 'snow'}">Pack your snow shoes!</c:when>
				</c:choose>
				<c:if test="${weather.high > 75}">Pack an extra gallon of water!</c:if>
				<c:if test="${(weather.high - weather.low) > 20}">Wear breathable layers!</c:if>
				<c:if test="${weather.low < 20}">It's cold out there!</c:if>
			</div>
		</div>
		</div>
	</c:forEach>
	</div>
	

<%@ include file ="common/footer.jspf" %>
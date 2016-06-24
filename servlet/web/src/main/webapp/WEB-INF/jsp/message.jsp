<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<data-message>
    <c:set value="negative" var="resultClass"></c:set>
    <c:if test="${result.isSuccess()}"> 
        <c:set value="positive" var="resultClass"></c:set>
    </c:if> 
    <c:if test="${not empty result}">
        <div class="ui ${resultClass} message">
            <i class="close icon"></i>
            <div class="header">
                ${result.getTitle()}
            </div>
            <p>
                ${result.getMessage()}
            </p>
        </div>
    </c:if> 
</data-message>
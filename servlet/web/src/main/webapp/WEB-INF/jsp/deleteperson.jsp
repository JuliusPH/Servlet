<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<data-delete>
    <c:set value="${result.getPerson()}" var="person"></c:set>
    <div class="header">
        Delete Person
    </div>
    <div class="content">
        <form class="ui delete form" action="${pageContext.request.contextPath}" method="post">
            <input id="action" name="action" type="hidden" value="delete"/>
            <input id="id" name="id" type="hidden" value="${person.getId()}"/>
            <div class="description">
                <p>
                    Do you really want to delete ${person.getFullName()}?
                </p>
            </div>
            <div class="actions">
                <div class="ui red cancel button">
                    Cancel
                </div>
                <input type="submit" class="ui green approve button" value="Delete"/>
            </div>
        </form>
    </div>
</data-delete>
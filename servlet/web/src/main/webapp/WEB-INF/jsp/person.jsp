<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<data-info>
    <c:set value="${result.getPerson()}" var="person"></c:set>
    <div class="header">
        Person Information
    </div>
    <div class="content">
        <div class="description">
            <div class="ui divided selection list">
                <a class="item">
                    <div class="ui horizontal label">Name</div>
                    ${person.getFullName()}
                </a>
                <a class="item">
                    <div class="ui horizontal label">Address</div>
                    ${person.getFullAddress()}
                </a>
                <a class="item">
                    <div class="ui horizontal label">Birthday</div>
                    ${person.getFormattedBirthday()}
                </a>
                <a class="item">
                    <div class="ui horizontal label">GWA</div>
                    ${person.getGwa()}
                </a>
            </div>
        </div>
    </div>
    <div class="actions">
        <div class="ui red cancel button">Close</div>
        <div class="ui green update button">Update</div>
        <div class="ui blue contacts button">Update Contacts</div>
        <div class="ui red delete button">Delete</div>
    </div>
</data-info>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<data-list>   
    <table class="ui celled table">
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Birthday</th>
                <th>GWA</th>
                <th>Employment</th>
                <th>Contacts</th>
                <th>Gender</th>
                <th>Roles</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${!result.getPersons().isEmpty()}">
                    <c:forEach var="person" items="${result.getPersons()}">
                        <tr>
                            <td>${person.getId()}</td>
                            <td><a href="#" class="name-link" data-id="${person.getId()}">${person.getFullName()}</span></td>
                            <td>${person.getFullAddress()}</td>
                            <td>${person.getFormattedBirthday()}</td>
                            <td>${person.getGwa()}</td>
                            <td>${person.getEmployment()}</td>
                            <td>${person.getAllContacts()}</td>
                            <td>${person.getGender()}</td>
                            <td>${person.getAllRoles()}</td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <td colspan="10" align="center">(No person to display)</td>
                </c:otherwise>
            </c:choose>
        </tbody>
        <tfoot>
            <th colspan="10">
            </th>
        </tfoot>
    </table>
</data-list>   
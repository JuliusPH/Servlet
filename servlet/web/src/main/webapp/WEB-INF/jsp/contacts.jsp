<<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<data-contacts>
    <c:set value="${result.getPerson()}" var="person"></c:set>
    <div class="header">
        Update Contacts
    </div>
    <div class="content">
        <form class="ui updatecontacts form" action="${pageContext.request.contextPath}/" method="post">
            <input id="action" name="action" type="hidden" value="updatecontacts"/>
            <input id="id" name="id" type="hidden" value="${person.getId()}"/>
            <h4 class="ui dividing header">Contacts</h4>
            <c:if test="${person.getEmail() != null}">
                <div class="field">
                    <label>E-mail</label>
                    <div class="two fields">
                        <div class="thirteen wide field">
                            <input type="hidden" name="emailid" value="${person.getEmail().getId()}">
                            <input type="text" name="email" id="email" placeholder="E-mail" value="${person.getEmail().getValue()}">
                        </div>
                        <div class="three wide field">
                            <div class="ui toggle checkbox" id="deleteemail">
                                <input type="checkbox" name="deletecontact" value="email">
                                <label>Delete</label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${person.getMobile() != null}">
                <div class="field">
                    <label>Mobile number</label>
                    <div class="two fields">
                        <div class="thirteen wide field">
                            <input type="hidden" name="mobilenumberid" value="${person.getMobile().getId()}">
                            <input type="text" name="mobilenumber" id="mobile" placeholder="Mobile Number" value="${person.getMobile().getValue()}">
                        </div>
                        <div class="three wide field">
                            <div class="ui toggle checkbox" id="deletemobile">
                                <input type="checkbox" name="deletecontact" value="mobile">
                                <label>Delete</label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${person.getLandline() != null}">
                <div class="field">
                    <label>Landline number</label>
                    <div class="two fields">
                        <div class="thirteen wide field">
                            <input type="hidden" name="landlinenumberid" value="${person.getLandline().getId()}">
                            <input type="text" name="landlinenumber" id="landline" placeholder="Landline Number" value="${person.getLandline().getValue()}">
                        </div>
                        <div class="three wide field">
                            <div class="ui toggle checkbox" id="deletelandline">
                                <input type="checkbox" name="deletecontact" value="landline">
                                <label>Delete</label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <div class="actions">
                <div class="ui red cancel button">
                    Cancel
                </div>
                <input type="submit" class="ui green button" value="Update"/>
            </div>
        </form>
        <script>
            prepareForm();
            validateForm();
        </script>
    </div>
</data-contacts>
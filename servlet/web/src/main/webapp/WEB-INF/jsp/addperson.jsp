<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<data-add>
    <div class="header">
        Add Person
    </div>
    <div class="content">
        <form class="ui add form" action="${pageContext.request.contextPath}/" method="post">
            <input id="action" name="action" type="hidden" value="add"/>
            <h4 class="ui dividing header">Personal Information</h4>
            <div class="field">
                <label>Name</label>
                <div class="three fields">
                    <div class="field">
                        <input type="text" name="firstname" placeholder="First Name" value="${firstname}">
                    </div>
                    <div class="field">
                        <input type="text" name="middlename" placeholder="Middle Name" value="${middlename}">
                    </div>
                    <div class="field">
                        <input type="text" name="lastname" placeholder="Last Name" value="${lastname}">
                    </div>
                </div>
                <div class="fields">
                    <div class="nine wide field">
                        <label>Birthday</label>
                        <div class="fields">
                            <div class="seven wide field">
                                <select class="ui dropdown birthmonth" id="birthmonth" name="birthmonth">
                                    <option value="">Month</option>
                                    <c:set var="months" value="${['January','February','March','April','May','June','July','August','September','October','November','December']}"/>
                                    <c:forEach begin="1" end="12" var="index">
                                        <option value="${index}">${months[index - 1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="four wide field">
                                <select class="ui dropdown birthday" id="birthday" name="birthday">
                                    <option value="">Day</option>
                                    <c:forEach begin="1" end="31" var="day">
                                        <option value="${day}">${day}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="five wide field">
                                <select class="ui dropdown birthyear" id="birthyear" name="birthyear">
                                    <option value="">Year</option>
                                    <c:forEach begin="1900" end="2016" var="year">
                                        <option value="${year}">${year}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="four wide field">
                        <label>Gender</label>
                        <div class="field">
                            <select class="ui dropdown" name="gender">
                                <option value="">Gender</option>
                                <option value="male" <c:if test="${gender == 'male'}">selected</c:if>>Male</option>
                                <option value="female" <c:if test="${gender == 'female'}">selected</c:if>>Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="three wide field">
                        <label>GWA</label>
                        <div class="field">
                            <input type="text" name="gwa" placeholder="GWA">
                        </div>
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Address Information</h4>
            <div class="field">
                <div class="two fields">
                    <div class="field">
                        <label>Street Number</label>
                        <input type="text" name="streetnumber" placeholder="Street Number">
                    </div>
                    <div class="field">
                        <label>Barangay</label>
                        <input type="text" name="barangay" placeholder="Barangay">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>City</label>
                        <input type="text" name="city" placeholder="City">
                    </div>
                    <div class="field">
                        <label>Zip Code</label>
                        <input type="text" name="zipcode" placeholder="Zip Code">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Employment Information</h4>
            <div class="fields">
                <div class="seven wide field">
                    <label>Is employed</label>
                    <select class="ui dropdown isemployed" name="isemployed">
                        <option value="">Is employed</option>
                        <option value="true">Employed</option>
                        <option value="false">Not employed</option>
                    </select>
                </div>
                <div class="nine wide field">
                    <label>Date hired</label>
                    <div class="fields">
                    <div class="seven wide field">
                        <select class="ui dropdown hiredmonth" id="hiredmonth" name="hiredmonth">
                            <option value="">Month</option>
                            <c:forEach begin="1" end="12" var="index">
                                <option value="${index}">${months[index - 1]}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="four wide field">
                        <select class="ui dropdown hiredday" id="hiredday" name="hiredday">
                            <option value="">Day</option>
                            <c:forEach begin="1" end="31" var="day">
                                <option value="${day}">${day}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="five wide field">
                        <select class="ui dropdown hiredyear" id=="hiredyear" name="hiredyear">
                            <option value="">Year</option>
                            <c:forEach begin="1900" end="2016" var="year">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                </div>
            </div>
            <h4 class="ui dividing header">Contact Information</h4>
            <div class="three fields">
                <div class="field">
                    <label>E-mail</label>
                    <input type="text" name="email" placeholder="E-mail">
                </div>
                <div class="field">
                    <label>Mobile number</label>
                    <input type="text" name="mobilenumber" placeholder="Mobile number">
                </div>
                <div class="field">
                    <label>Landline number</label>
                    <input type="text" name="landlinenumber" placeholder="Landline number">
                </div>
            </div>
            <h4 class="ui dividing header">Role Information</h4>
            <div class="field">
                <label>Roles</label>
                <select class="ui fluid search dropdown" multiple="" name="roles">
                    <option value="">Roles</option>
                    <c:if test="${!roles.isEmpty()}">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.getId()}">${role.getValue()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="actions">
                <div class="ui red cancel button">
                    Cancel
                </div>
                <button type="submit" name="add" class="ui blue submit button">
                    <i class="add user icon"></i>
                    Add Person
                </button>
            </div>
        </form>
        <script>
            prepareForm();
            validateForm();
        </script>
    </div>
</data-add>
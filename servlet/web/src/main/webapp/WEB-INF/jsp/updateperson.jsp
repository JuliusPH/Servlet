<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<data-update>
    <c:set value="${result.getPerson()}" var="person"></c:set>
    <div class="header">
        Update Person
    </div>
    <div class="content">
        <form class="ui update form" action="${pageContext.request.contextPath}/" method="post">
            <input id="action" name="action" type="hidden" value="update"/>
            <input id="id" name="id" type="hidden" value="${person.getId()}"/>
            <h4 class="ui dividing header">Personal Information</h4>
            <div class="field">
                <label>Name</label>
                <div class="three fields">
                    <div class="field">
                        <input type="text" name="firstname" placeholder="First Name" value="${person.getName().getFirstName()}">
                    </div>
                    <div class="field">
                        <input type="text" name="middlename" placeholder="Middle Name" value="${person.getName().getMiddleName()}">
                    </div>
                    <div class="field">
                        <input type="text" name="lastname" placeholder="Last Name" value="${person.getName().getLastName()}">
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
                                    <c:forEach begin="1" end="12" var="month">
                                        <option value="${month}" <c:if test="${month == person.getBirthday().getMonth() + 1}">selected</c:if>>${months[month - 1]}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="four wide field">
                                <select class="ui dropdown birthday" id="birthday" name="birthday">
                                    <option value="">Day</option>
                                    <c:forEach begin="1" end="31" var="day">
                                        <option value="${day}" <c:if test="${day == person.getBirthday().getDate()}">selected</c:if>>${day}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="five wide field">
                                <select class="ui dropdown birthyear" id="birthyear" name="birthyear">
                                    <option value="">Year</option>
                                    <c:forEach begin="1900" end="2016" var="year">
                                        <option value="${year}" <c:if test="${year == person.getBirthday().getYear() + 1900}">selected</c:if>>${year}</option>
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
                                <option value="male" <c:if test="${person.getGender() == 'Male'}">selected</c:if>>Male</option>
                                <option value="female" <c:if test="${person.getGender() == 'Female'}">selected</c:if>>Female</option>
                            </select>
                        </div>
                    </div>
                    <div class="three wide field">
                        <label>GWA</label>
                        <div class="field">
                            <input type="text" name="gwa" placeholder="GWA" value="${person.getGwa()}">
                        </div>
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Address Information</h4>
            <div class="field">
                <div class="two fields">
                    <div class="field">
                        <label>Street Number</label>
                        <input type="text" name="streetnumber" placeholder="Street Number" value="${person.getAddress().getStreetNumber()}">
                    </div>
                    <div class="field">
                        <label>Barangay</label>
                        <input type="text" name="barangay" placeholder="Barangay" value="${person.getAddress().getBarangay()}">
                    </div>
                </div>
                <div class="two fields">
                    <div class="field">
                        <label>City</label>
                        <input type="text" name="city" placeholder="City" value="${person.getAddress().getCity()}">
                    </div>
                    <div class="field">
                        <label>Zip Code</label>
                        <input type="text" name="zipcode" placeholder="Zip Code" value="${person.getAddress().getZipCode()}">
                    </div>
                </div>
            </div>
            <h4 class="ui dividing header">Employment Information</h4>
            <div class="fields">
                <div class="seven wide field">
                    <label>Is employed</label>
                    <select class="ui dropdown isemployed" name="isemployed">
                        <option value="">Is employed</option>
                        <option value="true" <c:if test="${person.isEmployed()}">selected</c:if>>Employed</option>
                        <option value="false" <c:if test="${!person.isEmployed()}">selected</c:if>>Not employed</option>
                    </select>
                </div>
                <div class="nine wide field">
                    <label>Date hired</label>
                    <div class="fields">
                    <div class="seven wide field">
                        <select class="ui dropdown hiredmonth" id="hiredmonth" name="hiredmonth">
                            <option value="">Month</option>
                            <c:set var="months" value="${['January','February','March','April','May','June','July','August','September','October','November','December']}"/>
                            <c:forEach begin="1" end="12" var="month">
                                <option value="${month}" <c:if test="${person.isEmployed() && month == person.getDateHired().getMonth() + 1}">selected</c:if>>${months[month - 1]}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="four wide field">
                        <select class="ui dropdown hiredday" id="hiredday" name="hiredday">
                            <option value="">Day</option>
                            <c:forEach begin="1" end="31" var="day">
                                <option value="${day}" <c:if test="${person.isEmployed() && day == person.getDateHired().getDate()}">selected</c:if>>${day}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="five wide field">
                        <select class="ui dropdown hiredyear" id=="hiredyear" name="hiredyear">
                            <option value="">Year</option>
                            <c:forEach begin="1900" end="2016" var="year">
                                <option value="${year}" <c:if test="${person.isEmployed() && year == person.getDateHired().getYear() + 1900}">selected</c:if>>${year}</option>
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
                    <input type="hidden" name="emailid" value="${person.getEmail().getId()}">
                    <input type="text" name="email" placeholder="E-mail" value="${person.getEmail().getValue()}">
                </div>
                <div class="field">
                    <label>Mobile number</label>
                    <input type="hidden" name="mobilenumberid" value="${person.getMobile().getId()}">
                    <input type="text" name="mobilenumber" placeholder="Mobile Number" value="${person.getMobile().getValue()}">
                </div>
                <div class="field">
                    <label>Landline number</label>
                    <input type="hidden" name="landlinenumberid" value="${person.getLandline().getId()}">
                    <input type="text" name="landlinenumber" placeholder="Landline Number" value="${person.getLandline().getValue()}">
                </div>
            </div>
            <h4 class="ui dividing header">Role Information</h4>
            <div class="field">
                <label>Roles</label>
                <select class="ui fluid search dropdown" multiple="" name="roles">
                    <option value="">Roles</option>
                    <c:if test="${!roles.isEmpty()}">
                        <c:forEach var="role" items="${roles}">
                            <option value="${role.getId()}" <c:if test="${person.getRoles().contains(role)}">selected</c:if>>${role.getValue()}</option>
                        </c:forEach>
                    </c:if>
                </select>
            </div>
            <div class="actions">
                <div class="ui red cancel button">
                    Cancel
                </div>
                <input type="submit" class="ui green approve button" value="Update"/>
            </div>
        </form>
        <script>
            prepareForm();
            validateForm();
        </script>
    </div>
</data-update>
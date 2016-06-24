<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>List of Persons</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/semantic.min.css">
        <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/semantic.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/functions.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                activateSortDropdowns();
                $(".person.modal")
                    .modal({
                        closable: false,
                        onDeny : function() {
                            $(".person.modal").empty();
                            $(".delete.modal").empty();
                            $(".update.modal").empty();
                            $(".contacts.modal").empty();
                            activateSortDropdowns();
                        }
                    })
                ;
                
                $(".delete.modal")
                    .modal({
                        closable: false,
                        onDeny : function() {
                            $(".person.modal").modal("show");
                            activateSortDropdowns();
                        }
                    })
                ;
                
                $(".update.modal")
                    .modal({
                        closable: false,
                        onDeny : function() {
                            $(".person.modal").modal("show");
                            activateSortDropdowns();
                        },
                        onApprove : function() {
                            activateSortDropdowns();
                            return false;
                        }
                    })
                ;
                
                $(".contacts.modal")
                    .modal({
                        closable: false,
                        onDeny : function() {
                            $(".person.modal").modal("show");
                            activateSortDropdowns();
                        },
                        onApprove : function() {
                            activateSortDropdowns();
                            return false;
                        }
                    })
                ;
                
                $(".add.modal")
                    .modal({
                        closable: false,
                        onHide : function(){
                            $(".add.modal").empty();
                            activateSortDropdowns();
                        }
                    })
                ;
                
                $(document).on("click", ".name-link", function(e){
                    var personId = $(this).data("id");
                    $.get("", {id : personId, action: "get"}, function(data){
                        $(".person.modal").html($(data).find("data-info").html());
                        $(".delete.modal").html($(data).find("data-delete").html());
                        $(".update.modal").html($(data).find("data-update").html());
                        $(".contacts.modal").html($(data).find("data-contacts").html());
                        $(".person.modal").modal("show");
                    });
                    
                    e.preventDefault();
                });
                
                $(document).on("click", ".person.modal .update.button", function(e){
                    $(".update.modal").modal("show");
                });
                
                $(document).on("click", ".person.modal .contacts.button", function(e){
                    $(".contacts.modal").modal("show");
                });
                
                $(document).on("click", ".person.modal .delete.button", function(e){
                    $(".delete.modal").modal("show");
                });
                
                $(document).on("click", ".add.button", function(){
                    $.get("", {action : "add"}, function(data){
                        $(".add.modal").html($(data).find("data-add").html());
                        $(".add.modal").modal("show");
                    });
                });

                $(document).on("submit", ".add.form", function(e){
                    $.post("", $(".add.form").serialize(), function(data) { 
                        $(".result").html($(data).find("data-message").html());
                        $(".wrapper .segment .content").html($(data).find("data-list").html());
                        $(".add.modal").modal("hide");
                        initSortDropdowns();
                    });
                    e.preventDefault();
                });

                $(document).on("submit", ".update.form", function(e){
                    $.post("", $(".update.form").serialize(), function(data){
                        $(".result").html($(data).find("data-message").html());
                        $(".wrapper .segment .content").html($(data).find("data-list").html());
                        $(".update.modal").modal("hide");
                        $(".update.modal").empty();
                        $(".person.modal").modal("hide");
                        $(".person.modal").empty();
                        initSortDropdowns();
                    });
                    e.preventDefault();
                });

                $(document).on("submit", ".updatecontacts.form", function(e){
                    $.post("", $(".updatecontacts.form").serialize(), function(data){
                        $(".result").html($(data).find("data-message").html());
                        $(".wrapper .segment .content").html($(data).find("data-list").html());
                        $(".contacts.modal").modal("hide");
                        $(".contacts.modal").empty();
                        $(".person.modal").modal("hide");
                        $(".person.modal").empty();
                        initSortDropdowns();
                    });
                    e.preventDefault();
                });

                $(document).on("submit", ".delete.form", function(e){
                    $.post("", $(".delete.form").serialize(), function(data) { 
                        $(".result").html($(data).find("data-message").html());
                        $(".wrapper .segment .content").html($(data).find("data-list").html());
                        $(".delete.modal").empty();
                        $(".person.modal").empty();
                        $(".delete.modal").modal("hide"); 
                        $(".person.modal").modal("hide");
                        initSortDropdowns();
                    });
                    e.preventDefault();
                });
                
                $(document).on("click", ".message .close", function(){
                    $(this)
                        .closest('.message')
                        .transition('fade')
                    ;
                });
            });
        </script>
    </head>
    <body>
        <div class="wrapper">
            <h1 class="ui header">
                List of Persons
            </h1>
            <div class="ui hidden divider"></div>
            <div class="result">
                <c:if test="${showMessage}">
                    <jsp:include page="message.jsp" />
                </c:if>
            </div>
            <div class="ui top attached menu">
                <a class="item add button">
                    <i class="add user icon"></i>
                    Add Person
                </a>
                <div class="right menu">
                    <div class="ui floating labeled icon dropdown item sort">
                        <i class="sort icon"></i>
                        <span class="text">ID</span>
                        <div class="menu">
                            <div class="header">
                                Sort by
                            </div>
                            <div class="divider"></div>
                            <div class="item active selected">
                                ID
                            </div>
                            <div class="item">
                                GWA
                            </div>
                            <div class="item">
                                Last Name
                            </div>
                            <div class="item">
                                Date Hired
                            </div>
                        </div>
                    </div>
                    <div class="ui floating labeled icon dropdown item order">
                        <i class="list icon"></i>
                        <span class="text">Ascending</span>
                        <div class="menu">
                            <div class="header">
                                Order by
                            </div>
                            <div class="divider"></div>
                            <div class="item active selected">
                                Ascending
                            </div>
                            <div class="item">
                                Descending
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="ui bottom attached segment">
                <div class="content">
                    <c:if test="${loadPersons}">
                        <jsp:include page="listperson.jsp" />
                    </c:if>
                </div>
            </div>
        </div>
        <div class="ui small modal person">
            <c:if test="${loadPerson}">
                <jsp:include page="person.jsp" />
            </c:if>
        </div>
        
        <div class="ui small modal delete">
            <c:if test="${loadPerson}">
                <jsp:include page="deleteperson.jsp" />
            </c:if>
        </div>
        
        <div class="ui long modal update">
            <c:if test="${loadPerson}">
                <jsp:include page="updateperson.jsp" />
            </c:if>
        </div>
        
        <div class="ui long modal contacts">
            <c:if test="${loadPerson}">
                <jsp:include page="contacts.jsp" />
            </c:if>
        </div>
         
        <div class="ui long modal add">
            <c:if test="${addPerson}">
                <jsp:include page="addperson.jsp" />
            </c:if>
        </div>
    </body>
</html>
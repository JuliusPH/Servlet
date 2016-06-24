function prepareForm(){
    $(".ui.dropdown").dropdown();
    
    if($(".update.form .ui.dropdown.isemployed").dropdown("get value") == "false" || $(".ui.dropdown.isemployed").dropdown("get value") == null){
        $(".update.form .ui.dropdown.hiredmonth").addClass("disabled");
        $(".update.form .ui.dropdown.hiredday").addClass("disabled");
        $(".update.form .ui.dropdown.hiredyear").addClass("disabled");
    }
    
    $(".add.form .ui.dropdown.hiredmonth").addClass("disabled");
    $(".add.form .ui.dropdown.hiredday").addClass("disabled");
    $(".add.form .ui.dropdown.hiredyear").addClass("disabled");
    
    $(".ui.dropdown.isemployed").dropdown({
        onChange: function(val){
            toggleDateHired(val);
        }
    });
    
    $(".ui.dropdown.birthmonth").dropdown({
        onChange: function(val){
            if($(".ui.dropdown.birthday").dropdown("get value") >= 29){
                changeDay(
                    val, 
                    ".ui.dropdown.birthmonth", 
                    ".ui.dropdown.birthyear", 
                    ".ui.dropdown.birthday"
                );
                $(".ui.dropdown.birthday").dropdown();
            }
        }
    });
    
    $(".ui.dropdown.birthyear").dropdown({
        onChange: function(val){
            if($(".ui.dropdown.birthmonth").dropdown("get value") == 2){
                changeDay(
                    val, 
                    ".ui.dropdown.birthmonth", 
                    ".ui.dropdown.birthyear", 
                    ".ui.dropdown.birthday"
                );
            }
        }
    });
    
    $(".ui.dropdown.hiredmonth").dropdown({
        onChange: function(val){
            if($(".ui.dropdown.hiredday").dropdown("get value") >= 29){
                changeDay(
                    val, 
                    ".ui.dropdown.hiredmonth", 
                    ".ui.dropdown.hiredyear", 
                    ".ui.dropdown.hiredday"
                );
            }
        }
    });
    
    $(".ui.dropdown.hiredyear").dropdown({
        onChange: function(val){
            if($(".ui.dropdown.hiredmonth").dropdown("get value") == 2){
                changeDay(
                    val, 
                    ".ui.dropdown.hiredmonth", 
                    ".ui.dropdown.hiredyear", 
                    ".ui.dropdown.hiredday"
                );
            }
        }
    });
    
    $("#deleteemail").checkbox({
        onChecked : function(){
            $(".ui.contacts.modal #email").attr("readonly", "readonly");
        },
        onUnchecked : function(){
            $(".ui.contacts.modal #email").removeAttr("readonly");
        }
    });
    
    $("#deletemobile").checkbox({
        onChecked : function(){
            $(".ui.contacts.modal #mobile").attr("readonly", "readonly");
        },
        onUnchecked : function(){
            $(".ui.contacts.modal #mobile").removeAttr("readonly");
        }
    });
    
    $("#deletelandline").checkbox({
        onChecked : function(){
            $(".ui.contacts.modal #landline").attr("readonly", "readonly");
        },
        onUnchecked : function(){
            $(".ui.contacts.modal #landline").removeAttr("readonly");
        }
    });
}

function validateForm(){
    var validationRules = {
        firstname: {
            identifier: "firstname",
            rules: [
                {
                    type: "empty",
                    prompt: "Please enter your first name"
                }
            ]
        },
        middlename: {
            identifier: "middlename",
            rules: [
                {
                    type: "empty",
                    prompt: "Please enter your middle name"
                }
            ]
        },
        lastname: {
            identifier: "lastname",
            rules: [
                {
                    type: "empty",
                    prompt: "Please enter your last name"
                }
            ]
        },
        birthmonth: {
            identifier: "birthmonth",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please select month of birth"
                }
            ]
        },
        birthday: {
            identifier: "birthday",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please select day of birth"
                }
            ]
        },
        birthyear: {
            identifier: "birthyear",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please select year of birth"
                }
            ]
        },
        gender: {
            identifier: "gender",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please select gender"
                }
            ]
        },
        streetnumber: {
            identifier: "streetnumber",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please enter your street number"
                }
            ]
        },
        barangay: {
            identifier: "barangay",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please enter your barangay"
                }
            ]
        },
        city: {
            identifier: "city",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please enter your city"
                }
            ]
        },
        zipcode: {
            identifier: "zipcode",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please enter your zipcode"
                },
                {
                    type   : "regExp[^[0-9]{4}$]",
                    prompt : "Please enter a valid zipcode"
                }
            ]
        },
        gwa: {
            identifier: "gwa",
            rules: [
                {
                    type   : "isValidGwa",
                    prompt : "Please enter a valid gwa"
                }
            ]
        },
        isemployed: {
            identifier: "isemployed",
            rules: [
                {
                    type   : "empty",
                    prompt : "Please select if employed or not employed"
                }
            ]
        },
        hiredmonth: {
            identifier: "hiredmonth",
            rules: [
                {
                    type   : "dateHired[isemployed]",
                    prompt : "Please select month of date hired"
                }
            ]
        },
        hiredday: {
            identifier: "hiredday",
            rules: [
                {
                    type   : "dateHired[isemployed]",
                    prompt : "Please select day of date hired"
                }
            ]
        },
        hiredyear: {
            identifier: "hiredyear",
            rules: [
                {
                    type   : "dateHired[isemployed]",
                    prompt : "Please select year of date hired"
                }
            ]
        },
        email: {
            identifier: "email",
            rules: [
                {
                    type: "allEmpty[mobilenumber, landlinenumber]",
                    prompt : "Please enter atleast one contact"
                },
                {
                    type: "regExp[^$|^.*@.*\..*$]",
                    prompt : "Please enter a valid email"
                }
            ]
        },
        mobilenumber: {
            identifier: "mobilenumber",
            rules: [
                {
                    type: "allEmpty[email, landlinenumber]",
                    prompt: "Please enter atleast one contact"
                },
                {
                    type: "regExp[^$|^(09|\\+639)\\d{9}$]",
                    prompt: "Please enter a valid mobile number"
                }
            ]
        },
        landlinenumber: {
            identifier: "landlinenumber",
            rules: [
                {
                    type   : "allEmpty[email, mobilenumber]",
                    prompt : "Please enter atleast one contact"
                },
                {
                    type   : "regExp[^$|^\\d{7}$]",
                    prompt : "Please enter a valid landline number"
                }
            ]
        },
        roles: {
            identifier  : "roles",
            rules: [
                {
                    type   : "minCount[1]",
                    prompt : "Please select at least 1 role"
                }
            ]
        }
    }
    
    var contactValidationRules = {
        email: {
            identifier: "email",
            rules: [
                {
                    type: "regExp[^$|^.*@.*\..*$]",
                    prompt : "Please enter a valid email"
                }
            ]
        },
        mobilenumber: {
            identifier: "mobilenumber",
            rules: [
                {
                    type: "regExp[^$|^(09|\\+639)\\d{9}$]",
                    prompt: "Please enter a valid mobile number"
                }
            ]
        },
        landlinenumber: {
            identifier: "landlinenumber",
            rules: [
                {
                    type   : "regExp[^$|^\\d{7}$]",
                    prompt : "Please enter a valid landline number"
                }
            ]
        },
        deletecontact: {
            identifier: "deletecontact",
            rules: [
                {
                    type: "spareOneUnchecked",
                    prompt : "Please spare one unchecked contact"
                }
            ]
        }
    }
    
    $(".add.form").form({
        inline: true, 
        fields: validationRules
    });
    $(".update.form").form({
        inline: true, 
        fields: validationRules
    });
    $(".updatecontacts.form").form({
        inline: true, 
        fields: contactValidationRules
    });
}

$.fn.form.settings.rules.allEmpty = function(value, parameters){
    if(value){
        return true;
    }
    var fieldsArray = parameters.split(", ");
    var isValid = false;

    $.each(fieldsArray, function(index, name){
        var element = $("input[name=" + name + "]");
        if (element && element.val()){
            isValid = true;
        }
    });
    return isValid; 
};

$.fn.form.settings.rules.spareOneUnchecked = function(value){
    var deleteToggles = $("input[name='deletecontact']");
    var checkedDeleteToggles = $("input[name='deletecontact']:checked");
    return (deleteToggles.length - checkedDeleteToggles.length) > 0;
};

$.fn.form.settings.rules.isValidGwa = function(value){
    var regex = /^\d*(\.\d{1})?\d*$/;
    var isValid = false;
    if(regex.test(value)){ 
        if(value >= 50 && value <= 100){
            isValid = true;
        }
    }
    return isValid;
}

$.fn.form.settings.rules.dateHired = function(value, name){
    var isValid = false;

    var element = $("select[name=" + name + "]");
    if(element.val() == "true"){
        if(value){
            isValid = true;
        }
        else{
            isValid = false;
        }
    }
    else{
        isValid = true;
    }
    return isValid; 
};


function daysInMonth(month, year) {
    return new Date(year, month, 0).getDate();
}

function changeDay(val, monthElement, yearElement, dayElement){
    var month = $(monthElement).dropdown("get value");
    var year = $(yearElement).dropdown("get value");
    var lastDay = daysInMonth(month, year);
    $(dayElement + " .menu").empty();
    for(var day = 1; day <= lastDay; day++){
        $(dayElement + " .menu").append(
            "<div class='item' data-value=" + day + ">" + day + "</div>"
        );
    }
    $(dayElement).dropdown("restore defaults");
    $(dayElement + " > .text").text("Day");
    $(dayElement + " > .text").addClass("default");
}

function toggleDateHired(val){
    if(val == "true"){
        $(".ui.dropdown.hiredmonth").removeClass("disabled");
        $(".ui.dropdown.hiredday").removeClass("disabled");
        $(".ui.dropdown.hiredyear").removeClass("disabled");
    }
    else{
        $(".ui.dropdown.hiredmonth").addClass("disabled");
        $(".ui.dropdown.hiredmonth").dropdown("restore defaults");
        $(".ui.dropdown.hiredmonth").parent().removeClass("error").find(".prompt").remove();
        $(".ui.dropdown.hiredday").addClass("disabled");
        $(".ui.dropdown.hiredday").dropdown("restore defaults");
        $(".ui.dropdown.hiredday > .text").text("Day");
        $(".ui.dropdown.hiredday > .text").addClass("default");
        $(".ui.dropdown.hiredday").parent().removeClass("error").find(".prompt").remove();
        $(".ui.dropdown.hiredyear").addClass("disabled");
        $(".ui.dropdown.hiredyear").dropdown("restore defaults");
        $(".ui.dropdown.hiredyear").parent().removeClass("error").find(".prompt").remove();
    }
}
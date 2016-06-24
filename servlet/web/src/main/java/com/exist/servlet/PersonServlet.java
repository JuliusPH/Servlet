package com.exist.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.exist.service.ContactService;
import com.exist.service.PersonService;
import com.exist.service.RoleService;
import com.exist.service.impl.ContactServiceImpl;
import com.exist.service.impl.PersonServiceImpl;
import com.exist.service.impl.RoleServiceImpl;
import com.exist.dto.AddressDto;
import com.exist.dto.ContactDto;
import com.exist.dto.PersonDto;
import com.exist.dto.RoleDto;
import com.exist.model.Name;
import com.exist.model.enums.ContactType;
import com.exist.model.enums.Gender;
import com.exist.model.enums.Sort;
import com.exist.result.Result;
import com.exist.validation.Validator;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonServlet extends HttpServlet{
    private ContactService contactService = new ContactServiceImpl();
    private PersonService personService = new PersonServiceImpl();
    private RoleService roleService = new RoleServiceImpl();
    private List<RoleDto> roles;
    
    public void init() throws ServletException{
        roles = roleService.getAll();
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Result result = null;
        String action = request.getParameter("action");
        String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "id";
        String order = request.getParameter("order") != null ? request.getParameter("order") : "ascending";
        if(!(action == null || "".equals("action"))){
            switch(action){
                case "get":
                    String id = request.getParameter("id");
                    result = getPerson(id);
                    request.setAttribute("loadPerson", true);
                    break;
                case "add":
                    request.setAttribute("addPerson", true);
                    break;
                default:
                    break;
            }
        }
        else{
            result = getPersons(sort, order);
            request.setAttribute("loadPersons", true);
        }
        
        request.setAttribute("roles", roles);
        request.setAttribute("result", result);
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        String action = request.getParameter("action");
        Result result = new Result();
        switch(action){
            case "delete":
                result = deletePerson(request);
                break;
            case "add":
                result = addPerson(request);
                break;
            case "update":
                result = updatePerson(request);
                break;
            case "updatecontacts":
                result = updateContacts(request);
                break;
            default:
                break;
        }
        String sort = request.getParameter("sort") != null ? request.getParameter("sort") : "id";
        String order = request.getParameter("order") != null ? request.getParameter("order") : "ascending";
        List<PersonDto> persons = getPersons(sort, order).getPersons();
        
        request.setAttribute("loadPersons", true);
        request.setAttribute("showMessage", true);
        result.setPersons(persons);
        
        request.setAttribute("result", result);
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
    }
    
    private Result deletePerson(HttpServletRequest request){
        String id = request.getParameter("id");
        if(id == null || "".equals(id)){
            return new Result().setTitle("Failed")
                               .setMessage("Person cannot be null")
                               .setSuccess(false);
        }
        
        PersonDto person = personService.get(Long.parseLong(id));
        
        if(person == null){
            return new Result().setTitle("Failed")
                               .setMessage("Person not found")
                               .setSuccess(false);
        }
        
        if(personService.delete(person)){
            return new Result().setTitle("Success")
                               .setMessage(person.getFullName() + " was deleted")
                               .setSuccess(true);
        }
        
        return new Result().setTitle("Failed")
                           .setMessage("An error occurs, please try again")
                           .setSuccess(false);
    }
    
    private Result addPerson(HttpServletRequest request){
        List<String> errorList = new ArrayList<String>();
        
        String firstName = validateField(request.getParameter("firstname"), Validator::isValidText, errorList);
        String middleName = validateField(request.getParameter("middlename"), Validator::isValidText, errorList);
        String lastName = validateField(request.getParameter("lastname"), Validator::isValidText, errorList);
        String birthMonth = request.getParameter("birthmonth");
        String birthDay = request.getParameter("birthday");
        String birthYear = request.getParameter("birthyear");
        String birthDate = validateField(birthMonth + "/" + birthDay + "/" + birthYear, Validator::isValidDate, errorList);
        String gender = validateField(request.getParameter("gender"), new String[]{"male", "female"}, Validator::isValidChoice, errorList);
        String gwa = validateField(request.getParameter("gwa"), Validator::isValidGwa, errorList);
        String streetNumber = validateField(request.getParameter("streetnumber"), Validator::isValidText, errorList);
        String barangay = validateField(request.getParameter("barangay"), Validator::isValidText, errorList);
        String city = validateField(request.getParameter("city"), Validator::isValidText, errorList);
        String zipCode = validateField(request.getParameter("zipcode"), Validator::isValidZipCode, errorList);
        String isEmployed = validateField(request.getParameter("isemployed"), new String[]{"true", "false"}, Validator::isValidChoice, errorList);
        String hiredMonth = request.getParameter("hiredmonth").equals("") ? "01" : request.getParameter("hiredmonth");
        String hiredDay = request.getParameter("hiredday").equals("") ? "01" : request.getParameter("hiredday");
        String hiredYear = request.getParameter("hiredyear").equals("") ? "1900" : request.getParameter("hiredyear");
        String hiredDate = validateField(hiredMonth + "/" + hiredDay + "/" + hiredYear, Validator::isValidDate, errorList);
        String email = "";
        if(!request.getParameter("email").equals("")){
            email = validateField(request.getParameter("email"), Validator::isValidEmail, errorList);
        }
        String mobileNumber = "";
        if(!request.getParameter("mobilenumber").equals("")){
            mobileNumber = validateField(request.getParameter("mobilenumber"), Validator::isValidMobileNumber, errorList);
        }
        String landlineNumber = "";
        if(!request.getParameter("landlinenumber").equals("")){
            landlineNumber = validateField(request.getParameter("landlinenumber"), Validator::isValidLandlineNumber, errorList);
        }
        String[] roles = validateField(request.getParameterValues("roles"), 
                                       roleService.getAll().stream().map(role -> role.getId().toString()).toArray(String[]::new), 
                                       Validator::areValidChoices, errorList);
                                       
        Result failedResult = new Result().setTitle("Failed")
                                          .setMessage("An error occurs, please try again")
                                          .setSuccess(false);
        
        if(errorList.size() > 0){
            return failedResult;
        }
        else{
            String dateFormat = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            
            PersonDto person = new PersonDto();
            try{
                person.setName(new Name(firstName, middleName, lastName));
                person.setAddress(new AddressDto(streetNumber, barangay, city, zipCode));
                person.setBirthday(simpleDateFormat.parse(birthDate));
                person.setGender(gender.equals("male") ? Gender.Male : Gender.Female);
                person.setGwa(Float.parseFloat(gwa));
                person.setEmployed(isEmployed.equals("true") ? true : false);
                if(isEmployed.equals("true")){
                    person.setDateHired(simpleDateFormat.parse(hiredDate));
                }
                Set<ContactDto> contactSet = new HashSet<ContactDto>();
                System.out.println(landlineNumber);
                if(!email.equals("")){
                    ContactDto emailContact = new ContactDto(ContactType.Email, email);
                    emailContact.setPerson(person);
                    contactSet.add(emailContact);
                }
                if(!mobileNumber.equals("")){
                    ContactDto mobileContact = new ContactDto(ContactType.Mobile, mobileNumber);
                    mobileContact.setPerson(person);
                    contactSet.add(mobileContact);
                }
                if(!landlineNumber.equals("")){
                    ContactDto landlineContact = new ContactDto(ContactType.Landline, landlineNumber);
                    landlineContact.setPerson(person);
                    contactSet.add(landlineContact);
                }
                person.setContacts(contactSet);
                Set<RoleDto> roleSet = new HashSet<RoleDto>();
                for(String role : roles){
                    roleSet.add(roleService.get(Long.parseLong(role)));
                }
                person.setRoles(roleSet);
                
                if(personService.add(person)){
                    return new Result().setTitle("Success")
                                       .setMessage("Added " + person.getFullName())
                                       .setSuccess(true);
                }
                else{
                    return failedResult;
                }
            }
            catch(ParseException ex){
                ex.printStackTrace();
                return failedResult;
            }
        }
    }
    
    private Result updatePerson(HttpServletRequest request){
        List<String> errorList = new ArrayList<String>();
        
        String id = validateField(request.getParameter("id"), Validator::isValidId, errorList);
        String firstName = validateField(request.getParameter("firstname"), Validator::isValidText, errorList);
        String middleName = validateField(request.getParameter("middlename"), Validator::isValidText, errorList);
        String lastName = validateField(request.getParameter("lastname"), Validator::isValidText, errorList);
        String birthMonth = request.getParameter("birthmonth");
        String birthDay = request.getParameter("birthday");
        String birthYear = request.getParameter("birthyear");
        String birthDate = validateField(birthMonth + "/" + birthDay + "/" + birthYear, Validator::isValidDate, errorList);
        String gender = validateField(request.getParameter("gender"), new String[]{"male", "female"}, Validator::isValidChoice, errorList);
        String gwa = validateField(request.getParameter("gwa"), Validator::isValidGwa, errorList);
        String streetNumber = validateField(request.getParameter("streetnumber"), Validator::isValidText, errorList);
        String barangay = validateField(request.getParameter("barangay"), Validator::isValidText, errorList);
        String city = validateField(request.getParameter("city"), Validator::isValidText, errorList);
        String zipCode = validateField(request.getParameter("zipcode"), Validator::isValidZipCode, errorList);
        String isEmployed = validateField(request.getParameter("isemployed"), new String[]{"true", "false"}, Validator::isValidChoice, errorList);
        String hiredMonth = request.getParameter("hiredmonth").equals("") ? "01" : request.getParameter("hiredmonth");
        String hiredDay = request.getParameter("hiredday").equals("") ? "01" : request.getParameter("hiredday");
        String hiredYear = request.getParameter("hiredyear").equals("") ? "1900" : request.getParameter("hiredyear");
        String hiredDate = validateField(hiredMonth + "/" + hiredDay + "/" + hiredYear, Validator::isValidDate, errorList);
        
        String email = "";
        String emailId = "";
        if(!request.getParameter("email").equals("")){
            email = validateField(request.getParameter("email"), Validator::isValidEmail, errorList);
        }
        if(!request.getParameter("emailid").equals("")){
            emailId = validateField(request.getParameter("emailid"), Validator::isValidId, errorList);
        }
        
        String mobileNumber = "";
        String mobileNumberId = "";
        if(!request.getParameter("mobilenumber").equals("")){
            mobileNumber = validateField(request.getParameter("mobilenumber"), Validator::isValidMobileNumber, errorList);
        }
        if(!request.getParameter("mobilenumberid").equals("")){
            mobileNumberId = validateField(request.getParameter("mobilenumberid"), Validator::isValidId, errorList);
        }
        
        String landlineNumber = "";
        String landlineNumberId = "";
        if(!request.getParameter("landlinenumber").equals("")){
            landlineNumber = validateField(request.getParameter("landlinenumber"), Validator::isValidLandlineNumber, errorList);
        }
        if(!request.getParameter("landlinenumberid").equals("")){
            landlineNumberId = validateField(request.getParameter("landlinenumberid"), Validator::isValidId, errorList);
        }
        
        String[] roles = validateField(request.getParameterValues("roles"), 
                                       roleService.getAll().stream().map(role -> role.getId().toString()).toArray(String[]::new), 
                                       Validator::areValidChoices, errorList);
                                       
        Result failedResult = new Result().setTitle("Failed")
                                          .setMessage("An error occurs, please try again")
                                          .setSuccess(false);
        
        if(errorList.size() > 0){
            return failedResult;
        }
        else{
            String dateFormat = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
            
            PersonDto person = personService.get(Long.parseLong(id));
            try{
                person.setName(new Name(firstName, middleName, lastName));
                AddressDto address = person.getAddress();
                address.setStreetNumber(streetNumber);
                address.setBarangay(barangay);
                address.setCity(city);
                address.setZipCode(zipCode);
                person.setAddress(address);
                person.setBirthday(simpleDateFormat.parse(birthDate));
                person.setGender(gender.equals("male") ? Gender.Male : Gender.Female);
                person.setGwa(Float.parseFloat(gwa));
                person.setEmployed(isEmployed.equals("true") ? true : false);
                if(isEmployed.equals("true")){
                    person.setDateHired(simpleDateFormat.parse(hiredDate));
                }
                Set<ContactDto> contactSet = new HashSet<ContactDto>();
                if(!email.equals("")){
                    ContactDto emailContact = null;
                    if(!emailId.equals("")){
                        emailContact = contactService.get(Long.parseLong(emailId));
                        emailContact.setValue(email);
                    }
                    else{
                        emailContact = new ContactDto(ContactType.Email, email);
                        emailContact.setPerson(person);
                    }
                    contactSet.add(emailContact);
                }
                else{
                    if(!emailId.equals("")){
                        ContactDto emailContact = contactService.get(Long.parseLong(emailId));
                        contactService.delete(emailContact);
                    }
                }
                
                if(!mobileNumber.equals("")){
                    ContactDto mobileContact = null;
                    if(!mobileNumberId.equals("")){
                        mobileContact = contactService.get(Long.parseLong(mobileNumberId));
                        mobileContact.setValue(mobileNumber);
                    }
                    else{
                        mobileContact = new ContactDto(ContactType.Mobile, mobileNumber);
                        mobileContact.setPerson(person);
                    }
                    contactSet.add(mobileContact);
                }
                else{
                    if(!mobileNumberId.equals("")){
                        ContactDto mobileContact = contactService.get(Long.parseLong(mobileNumberId));
                        contactService.delete(mobileContact);
                    }
                }
                
                if(!landlineNumber.equals("")){
                    ContactDto landlineContact = null;
                    if(!landlineNumberId.equals("")){
                        landlineContact = contactService.get(Long.parseLong(landlineNumberId));
                        landlineContact.setValue(landlineNumber);
                    }
                    else{
                        landlineContact = new ContactDto(ContactType.Landline, landlineNumber);
                        landlineContact.setPerson(person);
                    }
                    contactSet.add(landlineContact);
                }
                else{
                    if(!landlineNumberId.equals("")){
                        ContactDto landlineContact = contactService.get(Long.parseLong(landlineNumberId));
                        contactService.delete(landlineContact);
                    }
                }
                
                person.setContacts(contactSet);
                Set<RoleDto> roleSet = new HashSet<RoleDto>();
                for(String role : roles){
                    roleSet.add(roleService.get(Long.parseLong(role)));
                }
                person.setRoles(roleSet);
                
                if(personService.update(person)){
                    return new Result().setTitle("Success")
                                       .setMessage("Updated " + person.getFullName())
                                       .setSuccess(true);
                }
                else{
                    return failedResult;
                }
            }
            catch(ParseException ex){
                ex.printStackTrace();
                return failedResult;
            }
        }
    }
    
    private Result updateContacts(HttpServletRequest request){
        List<String> errorList = new ArrayList<String>();
        
        String id = validateField(request.getParameter("id"), Validator::isValidId, errorList);
        
        String email = "";
        String emailId = "";
        if(request.getParameter("email") != null){
            email = validateField(request.getParameter("email"), Validator::isValidEmail, errorList);
            emailId = validateField(request.getParameter("emailid"), Validator::isValidId, errorList);
        }
        
        String mobileNumber = "";
        String mobileNumberId = "";
        if(request.getParameter("mobilenumber") != null){
            mobileNumber = validateField(request.getParameter("mobilenumber"), Validator::isValidMobileNumber, errorList);
            mobileNumberId = validateField(request.getParameter("mobilenumberid"), Validator::isValidId, errorList);
        }
        
        String landlineNumber = "";
        String landlineNumberId = "";
        if(request.getParameter("landlinenumber") != null){
            landlineNumber = validateField(request.getParameter("landlinenumber"), Validator::isValidLandlineNumber, errorList);
            landlineNumberId = validateField(request.getParameter("landlinenumberid"), Validator::isValidId, errorList);
        }
        
        String[] deleteContactToggles = request.getParameterValues("deletecontact") != null ? request.getParameterValues("deletecontact") : new String[]{};
                                       
        Result failedResult = new Result().setTitle("Failed")
                                          .setMessage("An error occurs, please try again")
                                          .setSuccess(false);
        
        if(errorList.size() > 0){
            return failedResult;
        }
        else{
            PersonDto person = personService.get(Long.parseLong(id));
            Set<ContactDto> toUpdateContacts = new HashSet<ContactDto>();
            Set<ContactDto> toDeleteContacts = new HashSet<ContactDto>();
            if(!email.equals("")){
                ContactDto emailContact = contactService.get(Long.parseLong(emailId));
                if(Arrays.asList(deleteContactToggles).contains("email")){
                    toDeleteContacts.add(emailContact);
                }
                else{
                    emailContact.setValue(email);
                    toUpdateContacts.add(emailContact);
                }
            }
            
            if(!mobileNumber.equals("")){
                ContactDto mobileContact = contactService.get(Long.parseLong(mobileNumberId));
                if(Arrays.asList(deleteContactToggles).contains("mobile")){
                    toDeleteContacts.add(mobileContact);
                }
                else{
                    mobileContact.setValue(mobileNumber);
                    toUpdateContacts.add(mobileContact);
                }
            }
            
            if(!landlineNumber.equals("")){
                ContactDto landlineContact = contactService.get(Long.parseLong(landlineNumberId));
                if(Arrays.asList(deleteContactToggles).contains("landline")){
                    toDeleteContacts.add(landlineContact);
                }
                else{
                    landlineContact.setValue(landlineNumber);
                    toUpdateContacts.add(landlineContact);
                }
            }
            
            if(contactService.updateAll(toUpdateContacts) && contactService.deleteAll(toDeleteContacts)){
                return new Result().setTitle("Success")
                                   .setMessage("Updated contacts of " + person.getFullName())
                                   .setSuccess(true);
            }
            else{
                return failedResult;
            }
        }
    }
    
    private Result getPerson(String id){
        if(id == null || "".equals(id)){
            return new Result().setTitle("Failed")
                               .setMessage("Person cannot be null")
                               .setSuccess(false);
        }
        
        PersonDto person = personService.get(Long.parseLong(id));
        
        if(person == null){
            return new Result().setTitle("Failed")
                               .setMessage("Person not found")
                               .setSuccess(false);
        }
        
        return new Result().setPerson(person)
                           .setSuccess(true);
    }
    
    private Result getPersons(String sort, String order){
        boolean isAscending = order.equals("ascending") || order.equals("") ? true : false;
        List<PersonDto> persons = null;
        switch(sort){
            case "gwa":
                persons = personService.getAllBy(Sort.GWA, isAscending);
                break;
            case "date hired":
                persons = personService.getAllBy(Sort.DateHired, isAscending);
                break;
            case "last name":
                persons = personService.getAllBy(Sort.LastName, isAscending);
                break;
            default:
                persons = personService.getAllBy(Sort.ID, isAscending);
                break;
        }
        
        if(persons == null){
            return new Result().setTitle("Failed")
                               .setMessage("An error occurs, please try again")
                               .setSuccess(false);
        }
        
        
        return new Result().setPersons(persons)
                           .setSuccess(true);
    }
    
    private String validateField(String value, Function<String, Boolean> validationMethod, List<String> errorList){
        if(!validationMethod.apply(value)){
            errorList.add("Error");
        }
        return value;
    }
    
    private String validateField(String choice, String[] availableChoices, BiFunction<String, String[], Boolean> validationMethod, List<String> errorList){
        if(!validationMethod.apply(choice, availableChoices)){
            errorList.add("Error");
            System.out.println(choice);
        }
        return choice;
    }
    
    private String[] validateField(String[] choices, String[] availableChoices, BiFunction<String[], String[], Boolean> validationMethod, List<String> errorList){
        if(!validationMethod.apply(choices, availableChoices)){
            errorList.add("Error");
        }
        return choices;
    }
}
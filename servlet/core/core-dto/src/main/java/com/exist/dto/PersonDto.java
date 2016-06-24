package com.exist.dto;

import com.exist.model.Name;
import com.exist.model.enums.ContactType;
import com.exist.model.enums.Gender;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class PersonDto extends BaseEntityDto{
    private Name name;
    private AddressDto address;
    private Date birthday;
    private Float gwa;
    private Date dateHired;
    private Boolean isEmployed;
    private Set<ContactDto> contacts;
    private Gender gender;
    private Set<RoleDto> roles;
    
    public PersonDto(){}
    
    public PersonDto(Name name, AddressDto address, Date birthday, Float gwa, Date dateHired, 
                     Boolean isEmployed, Set<ContactDto> contacts, Gender gender, Set<RoleDto> roles){
        this.name = name;
        this.address = address;
        this.birthday = birthday;
        this.gwa = gwa;
        this.dateHired = dateHired;
        this.isEmployed = isEmployed;
        this.contacts = contacts;
        this.gender = gender;
        this.roles = roles; 
    }
    
    public Name getName(){
        return name;
    }
    
    public void setName(Name name){
        this.name = name;
    }
    
    public AddressDto getAddress(){
        return address;
    }
    
    public void setAddress(AddressDto address){
        this.address = address;
    }
    
    public Date getBirthday(){
        return birthday;
    }
    
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    
    public Float getGwa(){
        return gwa;
    }
    
    public void setGwa(Float gwa){
        this.gwa = gwa;
    }
    
    public Date getDateHired(){
        return dateHired;
    }
    
    public void setDateHired(Date dateHired){
        this.dateHired = dateHired;
    }
    
    public Boolean isEmployed(){
        return isEmployed;
    }
    
    public void setEmployed(Boolean isEmployed){
        this.isEmployed = isEmployed;
    }
    
    public Set<ContactDto> getContacts(){
        return contacts;
    }
    
    public void setContacts(Set<ContactDto> contacts){
        this.contacts = contacts;
    }
    
    public Gender getGender(){
        return gender;
    }
    
    public void setGender(Gender gender){
        this.gender = gender;
    }
    
    public Set<RoleDto> getRoles(){
        return roles;
    }
    
    public void setRoles(Set<RoleDto> roles){
        this.roles = roles;
    }
    
    public String getFullName(){
        return name.getFirstName() + " " + name.getMiddleName() + " " + name.getLastName();
    }
    
    public String getFullAddress(){
        return address.getStreetNumber() + " " + address.getBarangay() + ", " + address.getCity() + " " + address.getZipCode();
    }
    
    public String getFormattedBirthday(){
        return (new SimpleDateFormat("MMMMM dd, yyyy").format(birthday));
    }
    
    public String getEmployment(){
        return (isEmployed ? "Employed, " + new SimpleDateFormat("MMMMM dd, yyyy").format(dateHired) : "Not employed");
    }
    
    public String getAllContacts(){
        Set<String> contactValueSet = contacts.stream().map(c -> c.getValue()).collect(Collectors.toCollection(HashSet::new));
        return (contactValueSet.size() > 0 ? StringUtils.join(contactValueSet, ", ") : "None");
    }
    
    public String getAllRoles(){
        Set<String> roleValueSet = roles.stream().map(r -> r.getValue()).collect(Collectors.toCollection(HashSet::new));
        return (roleValueSet.size() > 0 ? StringUtils.join(roleValueSet, ", ")  : "None");
    }
    
    public ContactDto getEmail(){
        List<ContactDto> emailList = contacts.stream().filter(contact -> contact.getContactType() == ContactType.Email).collect(Collectors.toList());
        if(emailList.size() > 0){
            return emailList.get(0);
        }
        return null;
    }
    
    public ContactDto getMobile(){
        List<ContactDto> mobileList = contacts.stream().filter(contact -> contact.getContactType() == ContactType.Mobile).collect(Collectors.toList());
        if(mobileList.size() > 0){
            return mobileList.get(0);
        }
        return null;
    }
    
    public ContactDto getLandline(){
        List<ContactDto> landlineList = contacts.stream().filter(contact -> contact.getContactType() == ContactType.Landline).collect(Collectors.toList());
        if(landlineList.size() > 0){
            return landlineList.get(0);
        }
        return null;
    }
}

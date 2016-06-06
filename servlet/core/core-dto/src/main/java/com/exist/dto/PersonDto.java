package com.exist.dto;

import com.exist.model.Name;
import com.exist.model.enums.Gender;
import java.util.Date;
import java.util.Set;

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
}

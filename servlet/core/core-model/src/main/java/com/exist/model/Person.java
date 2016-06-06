package com.exist.model;

import com.exist.model.enums.Gender;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Person")
public class Person extends BaseEntity{
    private Name name;
    private Address address;
    private Date birthday;
    private Float gwa;
    private Date dateHired;
    private Boolean isEmployed;
    private Set<Contact> contacts;
    private Gender gender;
    private Set<Role> roles;
    
    @Embedded
    public Name getName(){
        return name;
    }
    
    public void setName(Name name){
        this.name = name;
    }
    
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    public Address getAddress(){
        return address;
    }
    
    public void setAddress(Address address){
        this.address = address;
    }
    
    @Column(name = "birthday")
    public Date getBirthday(){
        return birthday;
    }
    
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    
    @Column(name = "gwa")
    public Float getGwa(){
        return gwa;
    }
    
    public void setGwa(Float gwa){
        this.gwa = gwa;
    }
    
    @Column(name = "date_hired")
    public Date getDateHired(){
        return dateHired;
    }
    
    public void setDateHired(Date dateHired){
        this.dateHired = dateHired;
    }
    
    @Column(name = "is_employed")
    public Boolean isEmployed(){
        return isEmployed;
    }
    
    public void setEmployed(Boolean isEmployed){
        this.isEmployed = isEmployed;
    }
    
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    public Set<Contact> getContacts(){
        return contacts;
    }
    
    public void setContacts(Set<Contact> contacts){
        this.contacts = contacts;
    }
    
    @Column(name = "gender")
    @Enumerated(EnumType.STRING) 
    public Gender getGender(){
        return gender;
    }
    
    public void setGender(Gender gender){
        this.gender = gender;
    }
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="PersonRole", joinColumns=@JoinColumn(name="person_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    public Set<Role> getRoles(){
        return roles;
    }
    
    public void setRoles(Set<Role> roles){
        this.roles = roles;
    }
}

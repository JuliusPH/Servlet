package com.exist.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "Address")
public class Address extends BaseEntity{
    private Person person;
    private String streetNumber;
    private String barangay;
    private String city;
    private String zipCode;
    
    public Address(){}
    
    public Address(String streetNumber, String barangay, String city, String zipCode){
        this.streetNumber = streetNumber;
        this.barangay = barangay;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    @OneToOne
    @PrimaryKeyJoinColumn
    public Person getPerson(){
        return person;
    }
    
    public void setPerson(Person person){
        this.person = person;
    }
    
    @Column(name="street_number")
    public String getStreetNumber(){
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber){
        this.streetNumber = streetNumber;
    }
    
    @Column(name="barangay")
    public String getBarangay(){
        return barangay;
    }
    
    public void setBarangay(String barangay){
        this.barangay = barangay;
    }
    
    @Column(name="city")
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    @Column(name="zip_code")
    public String getZipCode(){
        return zipCode;
    }
    
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
}

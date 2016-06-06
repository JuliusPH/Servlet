package com.exist.dto;

public class AddressDto extends BaseEntityDto{
    private PersonDto person;
    private String streetNumber;
    private String barangay;
    private String city;
    private String zipCode;
    
    public AddressDto(){}
    
    public AddressDto(String streetNumber, String barangay, String city, String zipCode){
        this.streetNumber = streetNumber;
        this.barangay = barangay;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    public PersonDto getPerson(){
        return person;
    }
    
    public void setPerson(PersonDto person){
        this.person = person;
    }
    
    public String getStreetNumber(){
        return streetNumber;
    }
    
    public void setStreetNumber(String streetNumber){
        this.streetNumber = streetNumber;
    }
    
    public String getBarangay(){
        return barangay;
    }
    
    public void setBarangay(String barangay){
        this.barangay = barangay;
    }
    
    public String getCity(){
        return city;
    }
    
    public void setCity(String city){
        this.city = city;
    }
    
    public String getZipCode(){
        return zipCode;
    }
    
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
}

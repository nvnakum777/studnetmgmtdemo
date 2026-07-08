package com.studentmgmt.dto;
import com.studentmgmt.enums.AddressType;
import lombok.Data;

@Data
public class AddressResponse {

    private Long id;

    private AddressType addressType;

    private String addressLine;

    private String city;

    private String state;

    private String country;

    private String postalCode;

}
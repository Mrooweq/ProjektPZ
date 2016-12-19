package com.malinki.pz.validators;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class DTOForm {

    @NotEmpty
    @Size(min=3)
    private String name;
    
    @NotEmpty
    @Size(min=3)
    private String surname;
    
    @NotEmpty
    private String nrIDcard;
    
    @NotNull
    @Min(1)
    @Max(4)
    private Integer classTravel;

    @NotEmpty
    @Email
    private String email;
    
    @NotNull
    private double price;
    
    @NotEmpty
    private String sourceAirport;
    
    @NotEmpty
    private String destinyAirport;
    
    @NotEmpty
    private String flyDate;
    
    @NotEmpty
    private String airlineName;


}

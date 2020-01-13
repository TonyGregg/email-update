package com.genil.apps.contacts.emailupdate.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Antony Genil Gregory on 1/12/2020 9:02 AM
 * For project : email-update
 **/
@Data
public class Phone {
    private Long id;
    private String phoneNumber;
    private String userName;
    private Boolean isPrimary;

    private String environmentInfo;

}

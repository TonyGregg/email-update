package com.genil.apps.contacts.emailupdate.repos;

import com.genil.apps.contacts.emailupdate.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Antony Genil Gregory on 1/12/2020 9:08 AM
 * For project : email-update
 **/
public interface EmailRepo extends JpaRepository<Email, Long> {
}

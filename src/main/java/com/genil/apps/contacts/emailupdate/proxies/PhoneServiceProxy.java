package com.genil.apps.contacts.emailupdate.proxies;

import com.genil.apps.contacts.emailupdate.model.Phone;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by Antony Genil Gregory on 1/12/2020 8:54 PM
 * For project : email-update
 **/
@FeignClient(name = "phone-update-service")
public interface PhoneServiceProxy {
    @GetMapping("/api/v1/contacts/phone/{id}")
    public Phone getOnePhone(@PathVariable(value = "id") Long id);

}

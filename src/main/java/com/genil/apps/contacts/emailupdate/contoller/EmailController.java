package com.genil.apps.contacts.emailupdate.contoller;

import com.genil.apps.contacts.emailupdate.exception.EmailNotFoundException;
import com.genil.apps.contacts.emailupdate.model.CustomerContact;
import com.genil.apps.contacts.emailupdate.model.Email;
import com.genil.apps.contacts.emailupdate.model.Phone;
import com.genil.apps.contacts.emailupdate.proxies.PhoneServiceProxy;
import com.genil.apps.contacts.emailupdate.repos.EmailRepo;
import com.genil.apps.contacts.emailupdate.service.PhoneDiscoveryService;
import com.genil.apps.contacts.emailupdate.utils.environment.InstanceInformationService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by Antony Genil Gregory on 1/12/2020 9:09 AM
 * For project : email-update
 **/
@RestController
@RequestMapping("/api/v1/contacts")
@Slf4j
public class EmailController {
    @Autowired
    private EmailRepo emailRepo;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private InstanceInformationService instanceInformationService;

    @Autowired
    private PhoneDiscoveryService phoneDiscoveryService;

    @Value("${PHONE_UPDATE_SERVICE_URI:http://localhost:9092}")
    String phoneURI;
    String phoneApi = "/api/v1/contacts/phone/";

    @Autowired
    private PhoneServiceProxy phoneServiceProxy;


    @GetMapping("/emails/all")
    public List<Email> getAllEmails(@RequestParam @Max(200) @Min(0) Integer pageNumber,
                                    @RequestParam @Max(100) @Min(5) Integer size) {
        log.info("Retrieving all emails. Page Number " + pageNumber + "Size "+size);
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC,"email"));
        List<Email> emails = emailRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return emails;
    }

    @PostMapping("/email")
    public Email addNewEmail(@RequestBody @Valid Email email) {
        log.info("Email obj passed by caller {} ", email);
        return emailRepo.save(email);
    }

    @GetMapping("/email/{id}")
    public Email getOneEmail(@PathVariable Long id) {
        return emailRepo.findById(id).orElseThrow(()-> new EmailNotFoundException("Email do not exist for this " +
                "id "+id));
    }


    @GetMapping("/customer/{id}")
    @HystrixCommand(fallbackMethod = "getCustomerContactFallBack")
    public CustomerContact getCustomerContact(@PathVariable Long id) {
        log.info("Id received : "+id);
        // Retrieve Email Data from Email Repo
        CustomerContact customerContact = new CustomerContact();
        Email email =  emailRepo.findById(id).orElseThrow(()-> new EmailNotFoundException("Email do not exist for this " +
                "id "+id));

        log.info("Retrieved email {} ", email);

        log.info("Phone URI "+ phoneURI + phoneApi + id);
        Phone phone = null;
//        phone = this.restTemplate.getForObject(phoneURI + phoneApi + id, Phone.class);
        phone = this.restTemplate.getForObject(phoneDiscoveryService.getServiceURL("mini-cars-phone-update-service", phoneApi + id), Phone.class);
        log.info("Retrieved phone from rest template proxy client {} ", phone);

//        phone = phoneServiceProxy.getOnePhone(id);

//        log.info("Retrieved phone using feign client & service discovery {} ",phone);

        customerContact.setEmail(email);
        customerContact.setPhone(phone);
        customerContact.setEnvironmentInfo(instanceInformationService.retrieveInstanceInfo());
        customerContact.setPhoneServiceEnvInfo(phone.getEnvironmentInfo());

        return customerContact;
    }

    public CustomerContact getCustomerContactFallBack(Long id) {
        log.info("Fall back method ..Id received : "+id);
        // Retrieve Email Data from Email Repo
        CustomerContact customerContact = new CustomerContact();
        Email email =  emailRepo.findById(id).orElseThrow(()-> new EmailNotFoundException("Email do not exist for this " +
                "id "+id));

        log.info("Retrieved email {} ", email);

        log.info("Phone URI "+ phoneURI + phoneApi + id);
        Phone phone = null;
//        this.restTemplate.getForObject(phoneURI + phoneApi + id, Phone.class);
//        log.info("Retrieved phone from rest template proxy client {} ", phone);

        phone = new Phone();
        phone.setEnvironmentInfo("Dummy... dummy");
        phone.setPhoneNumber("123-444-5555");

        customerContact.setEmail(email);
        customerContact.setPhone(phone);
        customerContact.setEnvironmentInfo(instanceInformationService.retrieveInstanceInfo());
        customerContact.setPhoneServiceEnvInfo(phone.getEnvironmentInfo());

        return customerContact;
    }
}

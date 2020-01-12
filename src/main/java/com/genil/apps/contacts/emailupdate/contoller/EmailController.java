package com.genil.apps.contacts.emailupdate.contoller;

import com.genil.apps.contacts.emailupdate.model.Email;
import com.genil.apps.contacts.emailupdate.repos.EmailRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by Antony Genil Gregory on 1/12/2020 9:09 AM
 * For project : email-update
 **/
@RestController
@RequestMapping("/api/v1/contacts/email")
@Slf4j
public class EmailController {
    @Autowired
    private EmailRepo emailRepo;

    @GetMapping("/all")
    public List<Email> getAllEmails(@RequestParam @Max(200) @Min(0) Integer pageNumber,
                                    @RequestParam @Max(100) @Min(5) Integer size) {
        log.info("Retrieving all emails. Page Number " + pageNumber + "Size "+size);
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(Sort.Direction.ASC,"email"));
        List<Email> emails = emailRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return emails;
    }
}

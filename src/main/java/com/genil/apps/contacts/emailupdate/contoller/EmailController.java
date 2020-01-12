package com.genil.apps.contacts.emailupdate.contoller;

import com.genil.apps.contacts.emailupdate.exception.EmailNotFoundException;
import com.genil.apps.contacts.emailupdate.model.Email;
import com.genil.apps.contacts.emailupdate.repos.EmailRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

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
}

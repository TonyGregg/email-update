package com.genil.apps.contacts.emailupdate.contoller;

import com.genil.apps.contacts.emailupdate.proxies.PhoneServiceProxy;
import com.genil.apps.contacts.emailupdate.repos.EmailRepo;
import com.genil.apps.contacts.emailupdate.utils.environment.InstanceInformationService;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.Assert.*;

/**
 * Created by Antony Genil Gregory on 1/13/2020 10:15 AM
 * For project : email-update
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(value = EmailController.class)
public class EmailControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PhoneServiceProxy phoneServiceProxy;
    @MockBean
    private EmailRepo emailRepo;

    @MockBean
    private InstanceInformationService instanceInformationService;

    @InjectMocks
    private EmailController emailController;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getAllEmails() {
    }

    @Test
    public void addNewEmail() {
    }

    @Test
    public void getOneEmail() throws Exception {

        //When
        final ResultActions resultActions = mockMvc.perform(get("/api/v1/contacts/email/1")
                .accept(MediaType.APPLICATION_JSON));
        //Then
        resultActions.andExpect(status().isOk()); // Check 1st if the status is 200 !
//        resultActions.andExpect(jsonPath("$", containsInAnyOrder("Antony 2")));

    }

    @Test
    public void getCustomerContact() {
    }

    @Test
    public void getCustomerContactFallBack() {
    }
}
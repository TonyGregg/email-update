package com.genil.apps.contacts.emailupdate.contoller;

import com.genil.apps.contacts.emailupdate.model.Email;
import com.genil.apps.contacts.emailupdate.proxies.PhoneServiceProxy;
import com.genil.apps.contacts.emailupdate.repos.EmailRepo;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

/**
 * Created by Antony Genil Gregory on 1/13/2020 12:53 PM
 * For project : email-update
 **/
@RunWith(SpringRunner.class)
@WebMvcTest(EmailController.class)
public abstract class BaseControllerTest {
    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mockMvc;
    protected MediaType contentType;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8"));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



}

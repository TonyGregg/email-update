package com.genil.apps.contacts.emailupdate.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;

/**
 * Created by Antony Genil on 1/16/20 at 11 01 for email-update
 **/
@Service
@RefreshScope
@Slf4j
public class PhoneDiscoveryServiceImpl implements PhoneDiscoveryService {
    @Autowired
    private EurekaClient eurekaClient;

    @Override
    public String getServiceURL(String serviceName, String endpoint) {
        InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(serviceName, true);
        String URI = instanceInfo.getHomePageUrl() + endpoint;
        log.info("Instance Info URL "+URI);
        return URI;
    }
}

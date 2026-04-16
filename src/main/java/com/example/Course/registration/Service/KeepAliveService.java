package com.example.Course.registration.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class KeepAliveService {

    private static final Logger logger = LoggerFactory.getLogger(KeepAliveService.class);

    @Autowired
    private RestTemplate restTemplate;

    // Runs every 14 minutes (840000 milliseconds)
    @Scheduled(fixedDelay = 840000)
    public void keepAlive() {
        try {
            String url = "https://courses-7c6p.onrender.com/api/health"; // Replace with your actual URL
            String response = restTemplate.getForObject(url, String.class);
            logger.info("Keep-alive ping successful: " + response);
        } catch (Exception e) {
            logger.warn("Keep-alive ping failed: " + e.getMessage());
        }
    }
}

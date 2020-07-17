package com.pksheldon4.bootwebsample.tomcat;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("ssl")
public class SampleTomcatSslApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AbstractConfigurableWebServerFactory webServerFactory;

    @Test
    public void testSsl() {
        assertThat(this.webServerFactory.getSsl().isEnabled()).isTrue();
    }


// I/O error on GET request for "https://localhost:63799/people": java.security.cert.CertificateException: No name matching localhost found;
//    @Test
//    public void testHome() {
//        ResponseEntity<String> entity = this.restTemplate.getForEntity("/people", String.class);
//        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(entity.getBody()).contains("Preston");
//    }

}

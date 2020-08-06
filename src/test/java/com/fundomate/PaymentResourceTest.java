package com.fundomate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fundomate.entity.Payment;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class PaymentResourceTest {

    Map<String, Collection<Payment>> map = new HashMap<>();

    @BeforeEach
    void setUp() {
        map.put("company1", Collections.singletonList(PaymentMock.random()));
        map.put("company2", Collections.singletonList(PaymentMock.random()));

        PanacheMock.mock(Payment.class);
        Mockito.when(Payment.findAllToTenant("company1")).thenReturn(map.get("company1"));
        Mockito.when(Payment.findAllToTenant("company2")).thenReturn(map.get("company2"));
    }

    @Test
    public void testTenant1() throws JsonProcessingException {
        given()
                .when().get("/company1/payments")
                .then()
                .statusCode(200)
                .body(equalTo(new ObjectMapper().writeValueAsString(map.get("company1"))));
    }

    @Test
    public void testTenant2() throws JsonProcessingException {
        given()
                .when().get("/company2/payments")
                .then()
                .statusCode(200)
                .body(equalTo(new ObjectMapper().writeValueAsString(map.get("company2"))));
    }

    public static class PaymentMock extends Payment {
        public static Payment random() {
            Payment p = new Payment();
            p.id = RandomUtils.nextLong();
            p.setTenantId(RandomStringUtils.randomAlphabetic(10));
            p.setValue(RandomStringUtils.randomNumeric(3));
            return p;
        }
    }
}
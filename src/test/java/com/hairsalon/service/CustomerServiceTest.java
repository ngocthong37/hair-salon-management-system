package com.hairsalon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hairsalon.entity.ResponseObject;
import com.hairsalon.respository.CustomerRepository;

import java.util.ArrayList;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomerService.class})
@ExtendWith(SpringExtension.class)
class CustomerServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    /**
     * Method under test: {@link CustomerService#findAll()}
     */
    @Test
    void testFindAll() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualFindAllResult = customerService.findAll();
        assertTrue(actualFindAllResult.hasBody());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        assertEquals(200, actualFindAllResult.getStatusCodeValue());
        ResponseObject body = actualFindAllResult.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(customerRepository).findAll();
    }
}


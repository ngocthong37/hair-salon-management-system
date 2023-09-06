package com.hairsalon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hairsalon.Enum.Role;
import com.hairsalon.entity.Appointment;
import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.entity.Customer;
import com.hairsalon.entity.ResponseObject;
import com.hairsalon.entity.Salon;
import com.hairsalon.entity.ServiceHair;
import com.hairsalon.entity.User;
import com.hairsalon.respository.AppointmentRepository;
import com.hairsalon.respository.AppointmentStatusRepository;
import com.hairsalon.respository.CustomerRepository;
import com.hairsalon.respository.SalonRepository;
import com.hairsalon.respository.ServiceHairRepository;
import com.hairsalon.respository.UserRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentService.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private AppointmentStatusRepository appointmentStatusRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private EmailSendService emailSendService;

    @MockBean
    private SalonRepository salonRepository;

    @MockBean
    private ServiceHairRepository serviceHairRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AppointmentService#getAll()}
     */
    @Test
    void testGetAll() {
        when(appointmentRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualAll = appointmentService.getAll();
        assertTrue(actualAll.hasBody());
        assertTrue(actualAll.getHeaders().isEmpty());
        assertEquals(200, actualAll.getStatusCodeValue());
        ResponseObject body = actualAll.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(appointmentRepository).findAll();
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment() {
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("Json");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals(
                "Unrecognized token 'Json': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"Json\"; line: 1, column: 5]",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment2() {
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("ERROR");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals(
                "Unrecognized token 'ERROR': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"ERROR\"; line: 1, column: 6]",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment3() {
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment(null);
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(400, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("Empty JSON", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment4() {
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(400, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("Empty JSON", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment5() {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        Optional<AppointmentStatus> ofResult = Optional.of(appointmentStatus);
        when(appointmentStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        Optional<Customer> ofResult2 = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        Optional<Salon> ofResult3 = Optional.of(salon);
        when(salonRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        Optional<ServiceHair> ofResult4 = Optional.of(serviceHair);
        when(serviceHairRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult4);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        Optional<User> ofResult5 = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult5);
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("42");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("Text '' could not be parsed at index 0",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
        verify(appointmentStatusRepository).findById(Mockito.<Integer>any());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(salonRepository).findById(Mockito.<Integer>any());
        verify(serviceHairRepository).findById(Mockito.<Integer>any());
        verify(userRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment6() {
        when(appointmentStatusRepository.findById(Mockito.<Integer>any())).thenReturn(null);

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        Optional<Customer> ofResult = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        Optional<Salon> ofResult2 = Optional.of(salon);
        when(salonRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        Optional<ServiceHair> ofResult3 = Optional.of(serviceHair);
        when(serviceHairRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        Optional<User> ofResult4 = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult4);
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("42");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"appointmentStatusModel\" is null",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
        verify(appointmentStatusRepository).findById(Mockito.<Integer>any());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(salonRepository).findById(Mockito.<Integer>any());
        verify(serviceHairRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment7() {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        Optional<AppointmentStatus> ofResult = Optional.of(appointmentStatus);
        when(appointmentStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(null);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        Optional<Salon> ofResult2 = Optional.of(salon);
        when(salonRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        Optional<ServiceHair> ofResult3 = Optional.of(serviceHair);
        when(serviceHairRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        Optional<User> ofResult4 = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult4);
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("42");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"customerModel\" is null",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
        verify(customerRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment8() {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        Optional<AppointmentStatus> ofResult = Optional.of(appointmentStatus);
        when(appointmentStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        Optional<Customer> ofResult2 = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        when(salonRepository.findById(Mockito.<Integer>any())).thenReturn(null);

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");
        Optional<ServiceHair> ofResult3 = Optional.of(serviceHair);
        when(serviceHairRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        Optional<User> ofResult4 = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult4);
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("42");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"salonModel\" is null",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(salonRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#makeAppointment(String)}
     */
    @Test
    void testMakeAppointment9() {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");
        Optional<AppointmentStatus> ofResult = Optional.of(appointmentStatus);
        when(appointmentStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());
        Optional<Customer> ofResult2 = Optional.of(customer);
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");
        Optional<Salon> ofResult3 = Optional.of(salon);
        when(salonRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);
        when(serviceHairRepository.findById(Mockito.<Integer>any())).thenReturn(null);

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");
        Optional<User> ofResult4 = Optional.of(user);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult4);
        ResponseEntity<Object> actualMakeAppointmentResult = appointmentService.makeAppointment("42");
        assertTrue(actualMakeAppointmentResult.hasBody());
        assertEquals(500, actualMakeAppointmentResult.getStatusCodeValue());
        assertTrue(actualMakeAppointmentResult.getHeaders().isEmpty());
        assertEquals("ERROR", ((ResponseObject) actualMakeAppointmentResult.getBody()).getStatus());
        assertEquals("An error occurred", ((ResponseObject) actualMakeAppointmentResult.getBody()).getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"hairServiceModel\" is null",
                ((ResponseObject) actualMakeAppointmentResult.getBody()).getData());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(salonRepository).findById(Mockito.<Integer>any());
        verify(serviceHairRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#getAllByStatusId(Integer)}
     */
    @Test
    void testGetAllByStatusId() {
        when(appointmentRepository.findAppointmentByStatusId(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualAllByStatusId = appointmentService.getAllByStatusId(1);
        assertTrue(actualAllByStatusId.hasBody());
        assertTrue(actualAllByStatusId.getHeaders().isEmpty());
        assertEquals(200, actualAllByStatusId.getStatusCodeValue());
        ResponseObject body = actualAllByStatusId.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(appointmentRepository).findAppointmentByStatusId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#getAllByCustomerId(Integer)}
     */
    @Test
    void testGetAllByCustomerId() {
        when(appointmentRepository.findAppointmentByCustomerId(Mockito.<Integer>any())).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualAllByCustomerId = appointmentService.getAllByCustomerId(1);
        assertTrue(actualAllByCustomerId.hasBody());
        assertTrue(actualAllByCustomerId.getHeaders().isEmpty());
        assertEquals(200, actualAllByCustomerId.getStatusCodeValue());
        ResponseObject body = actualAllByCustomerId.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(appointmentRepository).findAppointmentByCustomerId(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link AppointmentService#updateStatusAppointment(String)}
     */
    @Test
    void testUpdateStatusAppointment() {
        ResponseEntity<Object> actualUpdateStatusAppointmentResult = appointmentService.updateStatusAppointment("Json");
        assertTrue(actualUpdateStatusAppointmentResult.hasBody());
        assertEquals(400, actualUpdateStatusAppointmentResult.getStatusCodeValue());
        assertTrue(actualUpdateStatusAppointmentResult.getHeaders().isEmpty());
        assertEquals("Error", ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getStatus());
        assertEquals(
                "Unrecognized token 'Json': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"Json\"; line: 1, column: 5]",
                ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getData());
    }

    /**
     * Method under test: {@link AppointmentService#updateStatusAppointment(String)}
     */
    @Test
    void testUpdateStatusAppointment2() {
        AppointmentStatus appointmentStatus = new AppointmentStatus();
        appointmentStatus.setAppointments(new ArrayList<>());
        appointmentStatus.setId(1);
        appointmentStatus.setStatus("Status");

        Customer customer = new Customer();
        customer.setAppointments(new ArrayList<>());
        customer.setCustomerAddresses(new ArrayList<>());
        customer.setCustomerName("Customer Name");
        customer.setEmail("jane.doe@example.org");
        customer.setId(1);
        customer.setOrders(new ArrayList<>());
        customer.setPaymentMethods(new ArrayList<>());
        customer.setPhoneNumber("6625550144");
        customer.setReviews(new ArrayList<>());

        Salon salon = new Salon();
        salon.setAddress("42 Main St");
        salon.setAppointments(new ArrayList<>());
        salon.setId(1);
        salon.setPhoneNumber("6625550144");
        salon.setSalonName("Salon Name");

        ServiceHair serviceHair = new ServiceHair();
        serviceHair.setAppointments(new ArrayList<>());
        serviceHair.setDescription("The characteristics of someone or something");
        serviceHair.setId(1);
        serviceHair.setPrice(10.0d);
        serviceHair.setReviews(new ArrayList<>());
        serviceHair.setServiceName("Service Name");
        serviceHair.setUrl("https://example.org/example");

        User user = new User();
        user.setEmail("jane.doe@example.org");
        user.setId(1);
        user.setPassword("iloveyou");
        user.setRole(Role.USER);
        user.setTokens(new ArrayList<>());
        user.setUserName("janedoe");

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(LocalDate.of(1970, 1, 1));
        appointment.setAppointmentStatus(appointmentStatus);
        appointment.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment.setCreateAt(mock(Timestamp.class));
        appointment.setCustomer(customer);
        appointment.setId(1);
        appointment.setSalon(salon);
        appointment.setServiceHair(serviceHair);
        appointment.setUpdateAt(mock(Timestamp.class));
        appointment.setUser(user);
        Optional<Appointment> ofResult = Optional.of(appointment);

        AppointmentStatus appointmentStatus2 = new AppointmentStatus();
        appointmentStatus2.setAppointments(new ArrayList<>());
        appointmentStatus2.setId(1);
        appointmentStatus2.setStatus("Status");

        Customer customer2 = new Customer();
        customer2.setAppointments(new ArrayList<>());
        customer2.setCustomerAddresses(new ArrayList<>());
        customer2.setCustomerName("Customer Name");
        customer2.setEmail("jane.doe@example.org");
        customer2.setId(1);
        customer2.setOrders(new ArrayList<>());
        customer2.setPaymentMethods(new ArrayList<>());
        customer2.setPhoneNumber("6625550144");
        customer2.setReviews(new ArrayList<>());

        Salon salon2 = new Salon();
        salon2.setAddress("42 Main St");
        salon2.setAppointments(new ArrayList<>());
        salon2.setId(1);
        salon2.setPhoneNumber("6625550144");
        salon2.setSalonName("Salon Name");

        ServiceHair serviceHair2 = new ServiceHair();
        serviceHair2.setAppointments(new ArrayList<>());
        serviceHair2.setDescription("The characteristics of someone or something");
        serviceHair2.setId(1);
        serviceHair2.setPrice(10.0d);
        serviceHair2.setReviews(new ArrayList<>());
        serviceHair2.setServiceName("Service Name");
        serviceHair2.setUrl("https://example.org/example");

        User user2 = new User();
        user2.setEmail("jane.doe@example.org");
        user2.setId(1);
        user2.setPassword("iloveyou");
        user2.setRole(Role.USER);
        user2.setTokens(new ArrayList<>());
        user2.setUserName("janedoe");

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(LocalDate.of(1970, 1, 1));
        appointment2.setAppointmentStatus(appointmentStatus2);
        appointment2.setAppointmentTime(LocalTime.MIDNIGHT);
        appointment2.setCreateAt(mock(Timestamp.class));
        appointment2.setCustomer(customer2);
        appointment2.setId(1);
        appointment2.setSalon(salon2);
        appointment2.setServiceHair(serviceHair2);
        appointment2.setUpdateAt(mock(Timestamp.class));
        appointment2.setUser(user2);
        when(appointmentRepository.save(Mockito.<Appointment>any())).thenReturn(appointment2);
        when(appointmentRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ResponseEntity<Object> actualUpdateStatusAppointmentResult = appointmentService.updateStatusAppointment("");
        assertTrue(actualUpdateStatusAppointmentResult.hasBody());
        assertEquals(200, actualUpdateStatusAppointmentResult.getStatusCodeValue());
        assertTrue(actualUpdateStatusAppointmentResult.getHeaders().isEmpty());
        assertEquals("OK", ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getStatus());
        assertEquals("Successfully", ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualUpdateStatusAppointmentResult.getBody()).getData());
        verify(appointmentRepository).save(Mockito.<Appointment>any());
        verify(appointmentRepository).findById(Mockito.<Integer>any());
    }
}


package com.hairsalon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hairsalon.entity.Customer;
import com.hairsalon.entity.Order;
import com.hairsalon.entity.OrderStatus;
import com.hairsalon.entity.PaymentMethod;
import com.hairsalon.entity.ResponseObject;
import com.hairsalon.respository.CustomerRepository;
import com.hairsalon.respository.OrderItemRepository;
import com.hairsalon.respository.OrderRepository;
import com.hairsalon.respository.OrderStatusRepository;
import com.hairsalon.respository.PaymentMethodRepository;
import com.hairsalon.respository.ProductItemRepository;

import java.time.LocalDate;

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

@ContextConfiguration(classes = {OrderService.class})
@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private EmailSendService emailSendService;

    @MockBean
    private OrderItemRepository orderItemRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrderStatusRepository orderStatusRepository;

    @MockBean
    private PaymentMethodRepository paymentMethodRepository;

    @MockBean
    private ProductItemRepository productItemRepository;

    /**
     * Method under test: {@link OrderService#findAll()}
     */
    @Test
    void testFindAll() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualFindAllResult = orderService.findAll();
        assertTrue(actualFindAllResult.hasBody());
        assertTrue(actualFindAllResult.getHeaders().isEmpty());
        assertEquals(200, actualFindAllResult.getStatusCodeValue());
        ResponseObject body = actualFindAllResult.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(orderRepository).findAll();
    }

    /**
     * Method under test: {@link OrderService#findAllByStatusId(Integer)}
     */
    @Test
    void testFindAllByStatusId() {
        when(orderRepository.findAllById(Mockito.<Iterable<Integer>>any())).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualFindAllByStatusIdResult = orderService.findAllByStatusId(1);
        assertTrue(actualFindAllByStatusIdResult.hasBody());
        assertTrue(actualFindAllByStatusIdResult.getHeaders().isEmpty());
        assertEquals(200, actualFindAllByStatusIdResult.getStatusCodeValue());
        ResponseObject body = actualFindAllByStatusIdResult.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(orderRepository).findAllById(Mockito.<Iterable<Integer>>any());
    }

    /**
     * Method under test: {@link OrderService#findAllByCustomerId(Integer)}
     */
    @Test
    void testFindAllByCustomerId() {
        when(orderRepository.findAllById(Mockito.<Iterable<Integer>>any())).thenReturn(new ArrayList<>());
        ResponseEntity<ResponseObject> actualFindAllByCustomerIdResult = orderService.findAllByCustomerId(1);
        assertTrue(actualFindAllByCustomerIdResult.hasBody());
        assertTrue(actualFindAllByCustomerIdResult.getHeaders().isEmpty());
        assertEquals(200, actualFindAllByCustomerIdResult.getStatusCodeValue());
        ResponseObject body = actualFindAllByCustomerIdResult.getBody();
        assertEquals("Successfully", body.getMessage());
        assertEquals(1, ((Map<String, ArrayList>) body.getData()).size());
        assertEquals("OK", body.getStatus());
        verify(orderRepository).findAllById(Mockito.<Iterable<Integer>>any());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder() {
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("Json");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals(
                "Unrecognized token 'Json': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"Json\"; line: 1, column: 5]",
                body.getData());
        assertEquals("ERROR", body.getStatus());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder2() {
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("ERROR");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals(
                "Unrecognized token 'ERROR': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"ERROR\"; line: 1, column: 6]",
                body.getData());
        assertEquals("ERROR", body.getStatus());
    }

    @Test
    void testOrder3() {
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order(null);
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(400, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("Empty JSON", body.getMessage());
        assertEquals("", body.getData());
        assertEquals("ERROR", body.getStatus());
    }


    @Test
    void testOrder4() {
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(400, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("Empty JSON", body.getMessage());
        assertEquals("", body.getData());
        assertEquals("ERROR", body.getStatus());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder5() {
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

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        orderStatus.setOrders(new ArrayList<>());
        orderStatus.setStatus("Status");
        Optional<OrderStatus> ofResult2 = Optional.of(orderStatus);
        when(orderStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);

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

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCustomer(customer2);
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Payment Method Name");
        Optional<PaymentMethod> ofResult3 = Optional.of(paymentMethod);
        when(paymentMethodRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult3);
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("42");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals("Text '' could not be parsed at index 0", body.getData());
        assertEquals("ERROR", body.getStatus());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(orderStatusRepository).findById(Mockito.<Integer>any());
        verify(paymentMethodRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder6() {
        when(customerRepository.findById(Mockito.<Integer>any())).thenReturn(null);

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        orderStatus.setOrders(new ArrayList<>());
        orderStatus.setStatus("Status");
        Optional<OrderStatus> ofResult = Optional.of(orderStatus);
        when(orderStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

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

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCustomer(customer);
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Payment Method Name");
        Optional<PaymentMethod> ofResult2 = Optional.of(paymentMethod);
        when(paymentMethodRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("42");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"customerModel\" is null", body.getData());
        assertEquals("ERROR", body.getStatus());
        verify(customerRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder7() {
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
        when(orderStatusRepository.findById(Mockito.<Integer>any())).thenReturn(null);

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

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCustomer(customer2);
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Payment Method Name");
        Optional<PaymentMethod> ofResult2 = Optional.of(paymentMethod);
        when(paymentMethodRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("42");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"orderStatusModel\" is null", body.getData());
        assertEquals("ERROR", body.getStatus());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(orderStatusRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderService#order(String)}
     */
    @Test
    void testOrder8() {
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

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        orderStatus.setOrders(new ArrayList<>());
        orderStatus.setStatus("Status");
        Optional<OrderStatus> ofResult2 = Optional.of(orderStatus);
        when(orderStatusRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult2);
        when(paymentMethodRepository.findById(Mockito.<Integer>any())).thenReturn(null);
        ResponseEntity<ResponseObject> actualOrderResult = orderService.order("42");
        assertTrue(actualOrderResult.hasBody());
        assertTrue(actualOrderResult.getHeaders().isEmpty());
        assertEquals(500, actualOrderResult.getStatusCodeValue());
        ResponseObject body = actualOrderResult.getBody();
        assertEquals("An error occurred", body.getMessage());
        assertEquals("Cannot invoke \"java.util.Optional.get()\" because \"paymentMethodModel\" is null", body.getData());
        assertEquals("ERROR", body.getStatus());
        verify(customerRepository).findById(Mockito.<Integer>any());
        verify(orderStatusRepository).findById(Mockito.<Integer>any());
        verify(paymentMethodRepository).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link OrderService#updateStatusOrder(String)}
     */
    @Test
    void testUpdateStatusOrder() {
        ResponseEntity<Object> actualUpdateStatusOrderResult = orderService.updateStatusOrder("Json");
        assertTrue(actualUpdateStatusOrderResult.hasBody());
        assertEquals(400, actualUpdateStatusOrderResult.getStatusCodeValue());
        assertTrue(actualUpdateStatusOrderResult.getHeaders().isEmpty());
        assertEquals("Error", ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getStatus());
        assertEquals(
                "Unrecognized token 'Json': was expecting (JSON String, Number, Array, Object or token 'null', 'true'"
                        + " or 'false')\n" + " at [Source: (String)\"Json\"; line: 1, column: 5]",
                ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getData());
    }

    /**
     * Method under test: {@link OrderService#updateStatusOrder(String)}
     */
    @Test
    void testUpdateStatusOrder2() {
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

        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setId(1);
        orderStatus.setOrders(new ArrayList<>());
        orderStatus.setStatus("Status");

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

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCustomer(customer2);
        paymentMethod.setId(1);
        paymentMethod.setPaymentMethodName("Payment Method Name");

        Order order = new Order();
        order.setCustomer(customer);
        order.setId(1);
        order.setOrderDate(LocalDate.of(1970, 1, 1));
        order.setOrderItems(new ArrayList<>());
        order.setOrderStatus(orderStatus);
        order.setPaymentMethod(paymentMethod);
        order.setTotalPrice(1);
        Optional<Order> ofResult = Optional.of(order);

        Customer customer3 = new Customer();
        customer3.setAppointments(new ArrayList<>());
        customer3.setCustomerAddresses(new ArrayList<>());
        customer3.setCustomerName("Customer Name");
        customer3.setEmail("jane.doe@example.org");
        customer3.setId(1);
        customer3.setOrders(new ArrayList<>());
        customer3.setPaymentMethods(new ArrayList<>());
        customer3.setPhoneNumber("6625550144");
        customer3.setReviews(new ArrayList<>());

        OrderStatus orderStatus2 = new OrderStatus();
        orderStatus2.setId(1);
        orderStatus2.setOrders(new ArrayList<>());
        orderStatus2.setStatus("Status");

        Customer customer4 = new Customer();
        customer4.setAppointments(new ArrayList<>());
        customer4.setCustomerAddresses(new ArrayList<>());
        customer4.setCustomerName("Customer Name");
        customer4.setEmail("jane.doe@example.org");
        customer4.setId(1);
        customer4.setOrders(new ArrayList<>());
        customer4.setPaymentMethods(new ArrayList<>());
        customer4.setPhoneNumber("6625550144");
        customer4.setReviews(new ArrayList<>());

        PaymentMethod paymentMethod2 = new PaymentMethod();
        paymentMethod2.setCustomer(customer4);
        paymentMethod2.setId(1);
        paymentMethod2.setPaymentMethodName("Payment Method Name");

        Order order2 = new Order();
        order2.setCustomer(customer3);
        order2.setId(1);
        order2.setOrderDate(LocalDate.of(1970, 1, 1));
        order2.setOrderItems(new ArrayList<>());
        order2.setOrderStatus(orderStatus2);
        order2.setPaymentMethod(paymentMethod2);
        order2.setTotalPrice(1);
        when(orderRepository.save(Mockito.<Order>any())).thenReturn(order2);
        when(orderRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ResponseEntity<Object> actualUpdateStatusOrderResult = orderService.updateStatusOrder("");
        assertTrue(actualUpdateStatusOrderResult.hasBody());
        assertEquals(200, actualUpdateStatusOrderResult.getStatusCodeValue());
        assertTrue(actualUpdateStatusOrderResult.getHeaders().isEmpty());
        assertEquals("OK", ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getStatus());
        assertEquals("Successfully", ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getMessage());
        assertEquals("", ((ResponseObject) actualUpdateStatusOrderResult.getBody()).getData());
        verify(orderRepository).save(Mockito.<Order>any());
        verify(orderRepository).findById(Mockito.<Integer>any());
    }
}


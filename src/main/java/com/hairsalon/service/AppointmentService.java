package com.hairsalon.service;

import com.hairsalon.entity.*;
import com.hairsalon.model.*;
import com.hairsalon.respository.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentImp appointmentImpl;

    @Autowired
    private CustomerImp customerImp;

    @Autowired
    private SalonImp salonImp;

    @Autowired
    private ServiceHairImp serviceHairImp;

    @Autowired
    private AppointmentStatusImp appointmentStatusImp;

    @Autowired
    private EmailSendService emailSendService;

    @Autowired
    private UserImp userImpl;


    public ResponseEntity<ResponseObject> getAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<AppointmentModel> appointmentList = null;
        appointmentList = appointmentImpl.getAll();
        results.put("appointmentList", appointmentList);

        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<Object> makeAppointment(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        AppointmentModel appointmentModel = new AppointmentModel();

        try {
            if (json == null || json.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("ERROR", "Empty JSON", ""));
            }
            JsonNode jsonObjectAppointment = objectMapper.readTree(json);
            Integer customerId = jsonObjectAppointment.get("customer") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("customer").asText()) : 1;
            Integer serviceId = jsonObjectAppointment.get("service") != null ?
                    jsonObjectAppointment.get("service").asInt() : 1;
            Integer statusId = jsonObjectAppointment.get("status") != null ?
                    jsonObjectAppointment.get("status").asInt() : 1;
            Integer salonId = jsonObjectAppointment.get("salon") != null ?
                    jsonObjectAppointment.get("salon").asInt() : 1;
            String appointmentDate = jsonObjectAppointment.get("appointmentDate") != null ?
                    jsonObjectAppointment.get("appointmentDate").asText() : "";
            String appointmentTime = jsonObjectAppointment.get("appointmentTime") != null ?
                    jsonObjectAppointment.get("appointmentTime").asText() : "";
            Integer userId = jsonObjectAppointment.get("userId") != null ?
                    Integer.parseInt(jsonObjectAppointment.get("userId").asText()) : 1;

            Appointment appointment = new Appointment();

            CustomerModel customerModel = customerImp.findById(customerId);
            Customer customer = new Customer();
            customer.setId(customerModel.getId());
            customer.setCustomerName(customerModel.getCustomerName());
            customer.setEmail(customerModel.getEmail());

            SalonModel salonModel = salonImp.findById(salonId);
            Salon salon = new Salon();
            salon.setId(salonModel.getId());
            salon.setSalonName(salonModel.getSalonName());

            HairServiceModel hairServiceModel = serviceHairImp.findById(serviceId);
            ServiceHair serviceHair = new ServiceHair();
            serviceHair.setId(hairServiceModel.getId());
            serviceHair.setServiceName(hairServiceModel.getServiceName());

            AppointmentStatusModel appointmentStatusModel = appointmentStatusImp.findById(statusId);
            AppointmentStatus appointmentStatus = new AppointmentStatus();
            appointmentStatus.setId(appointmentStatusModel.getId());
            appointmentStatus.setStatus(appointmentStatusModel.getStatus());

            User user = userImpl.findById(userId);


            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalTime parsedTime = LocalTime.parse(appointmentTime, timeFormatter);
            LocalDate parsedDate = LocalDate.parse(appointmentDate, dateFormatter);
            appointment.setSalon(salon);
            appointment.setAppointmentDate(parsedDate);
            appointment.setAppointmentTime(parsedTime);
            appointment.setCustomer(customer);
            appointment.setServiceHair(serviceHair);
            appointment.setAppointmentStatus(appointmentStatus);
            appointment.setUser(user);

            LocalDateTime now = LocalDateTime.now();
            Timestamp timestamp = Timestamp.valueOf(now);
            appointment.setCreateAt(timestamp);
            appointment.setUpdateAt(timestamp);

            Integer messageId = appointmentImpl.insert(appointment);
            appointmentModel = appointmentImpl.findById(messageId);

            String[] cc = {"n20dccn152@student.ptithcm.edu.vn"};

            if (messageId != 0) {
//                emailSendService.sendMail(customer.getEmail(), cc, "Lịch hẹn của bạn đã được " +
//                        "ghi lại", "Cảm ơn bé: " + customer.getCustomerName()  + " đã tin tưởng dịch vụ của chúng tôi." +
//                        " Vui lòng để ý điện thoại để nhận được những thông báo sớm nhất.");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("OK", "Successfully", appointmentModel));
            } else {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Can not make an appointment", ""));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "An error occurred", e.getMessage()));
        }
    }

    public ResponseEntity<ResponseObject> getAllByStatusId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<AppointmentModel> appointmentList = null;
        appointmentList = appointmentImpl.getAllByStatusId(id);
        results.put("appointmentList", appointmentList);

        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> getAllByCustomerId(Integer id) {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<AppointmentModel> appointmentList = null;
        appointmentList = appointmentImpl.getAllByCustomerId(id);
        results.put("appointmentList", appointmentList);

        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<Object> updateStatusAppointment(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        Integer statusCode;
        Integer appointmentId;
        try {
            jsonNode = jsonMapper.readTree(json);
            appointmentId = jsonNode.get("appointmentId") != null ? jsonNode.get("appointmentId").asInt() : null;
            statusCode = jsonNode.get("statusCode") != null ? jsonNode.get("statusCode").asInt() : -1;
            Appointment appointment = new Appointment();
            appointment = appointmentImpl.findAppointmentById(appointmentId);
            AppointmentStatus appointmentStatus = new AppointmentStatus();
            appointmentStatus.setId(statusCode);
            appointmentStatus.setStatus("ON_HOLD");
            appointment.setAppointmentStatus(appointmentStatus);
            Integer id = appointmentImpl.updateStatusAppointment(appointment);
            if (appointmentImpl.updateStatusAppointment(appointment) < 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when update status code appointment", ""));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }


}

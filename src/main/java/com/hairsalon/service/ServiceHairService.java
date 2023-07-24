package com.hairsalon.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.hairsalon.entity.Appointment;
import com.hairsalon.entity.AppointmentStatus;
import com.hairsalon.entity.ResponseObject;
import com.hairsalon.entity.ServiceHair;
import com.hairsalon.model.HairServiceModel;
import com.hairsalon.respository.imp.ServiceHairImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class ServiceHairService {
    @Autowired
    private ServiceHairImp serviceHairImp;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());


    public ResponseEntity<ResponseObject> getAll() {
        Map<String, Object> results = new TreeMap<String, Object>();
        List<HairServiceModel> hairServiceList = null;
        hairServiceList = serviceHairImp.getAllService();
        results.put("serviceList", hairServiceList);

        if (results.size() > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("Not found", "Not found", ""));
        }
    }

    public ResponseEntity<ResponseObject> findById(Integer id) {
        HairServiceModel hairServiceModel = new HairServiceModel();
        try {
            Map<String, Object> results = new TreeMap<String, Object>();
            hairServiceModel = serviceHairImp.findById(id);
            results.put("hairService", hairServiceModel);
            if (results.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", results));
            }
            else {
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ERROR", "Have error", ""));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("ERROR", "Have error:", e.getMessage()));
        }
    }

    public ResponseEntity<Object> add(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        try {
            jsonNode = jsonMapper.readTree(json);
            String serviceName = jsonNode.get("serviceName") != null ? jsonNode.get("serviceName").asText() : "";
            Double price = jsonNode.get("price") != null ? jsonNode.get("price").asDouble() : null;
            String description = jsonNode.get("description") != null ? jsonNode.get("description").asText() : "";
            String url = jsonNode.get("url") != null ? jsonNode.get("url").asText() : "";
            ServiceHair serviceHair = new ServiceHair();
            serviceHair.setServiceName(serviceName);
            serviceHair.setDescription(description);
            serviceHair.setPrice(price);
            serviceHair.setUrl(url);
            if (serviceHairImp.add(serviceHair) < 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when add service hair", ""));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }

    public ResponseEntity<Object> update(String json) {
        JsonNode jsonNode;
        JsonMapper jsonMapper = new JsonMapper();
        try {
            jsonNode = jsonMapper.readTree(json);
            Integer id = jsonNode.get("id") != null ? jsonNode.get("id").asInt() : null;
            String serviceName = jsonNode.get("serviceName") != null ? jsonNode.get("serviceName").asText() : "";
            Double price = jsonNode.get("price") != null ? jsonNode.get("price").asDouble() : null;
            String description = jsonNode.get("description") != null ? jsonNode.get("description").asText() : "";
            String url = jsonNode.get("url") != null ? jsonNode.get("url").asText() : "";
            ServiceHair serviceHair = new ServiceHair();
            serviceHair.setId(id);
            serviceHair.setServiceName(serviceName);
            serviceHair.setDescription(description);
            serviceHair.setPrice(price);
            serviceHair.setUrl(url);
            if (serviceHairImp.update(serviceHair) < 0) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new ResponseObject("ERROR", "Have error when add service hair", ""));
            }
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Error", e.getMessage(), ""));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK", "Successfully", ""));
    }


}

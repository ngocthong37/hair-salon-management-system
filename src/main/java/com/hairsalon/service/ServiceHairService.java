package com.hairsalon.service;

import com.hairsalon.entity.ResponseObject;
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


}

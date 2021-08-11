package com.vf.iotgroup.controller;

import com.vf.iotgroup.model.IoTDocument;
import com.vf.iotgroup.service.IoTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/VFIoT")
public class IoTController {

    @Autowired
    IoTService ioTService;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<IoTDocument> ioTDocuments = ioTService.findAll();
        return new ResponseEntity<>(ioTDocuments, HttpStatus.OK);
    }

    @GetMapping("/getAllWaitingForActivation")
    public ResponseEntity<?> getAllWaitingForActivation() {
        List<IoTDocument> ioTDocuments = ioTService.getAllWaitingForActivation();
        return new ResponseEntity<>(ioTDocuments, HttpStatus.OK);
    }

    @GetMapping("/getAllReadyDevices")
    public ResponseEntity<?> getAllReadyDevices() {
        List<IoTDocument> ioTDocuments = ioTService.getAllReadyDevices();
        return new ResponseEntity<>(ioTDocuments, HttpStatus.OK);
    }

    @PostMapping("/device")
    public ResponseEntity<?> addIoTDocument(@RequestBody IoTDocument newIoTDocument) {
        IoTDocument ioTDocument = ioTService.addIoTDocument(newIoTDocument);

        return new ResponseEntity<>(ioTDocument, HttpStatus.CREATED);
    }

    @PutMapping("/device/{managerKey}")
    public ResponseEntity<?> updateIoTDocument(@PathVariable String managerKey, @RequestBody IoTDocument ioTDocument) {
        IoTDocument updatedIoTDocument = ioTService.updateIoTDocument(managerKey, ioTDocument);

        return new ResponseEntity<>(updatedIoTDocument, HttpStatus.CREATED);
    }

    @DeleteMapping("/device/{simId}/{operatorCode}/{managerKey}")
    public ResponseEntity<?> deletePersonUsingId(@PathVariable String simId,@PathVariable String operatorCode, @PathVariable String managerKey) {
        ioTService.deletePersonUsingId(simId, operatorCode, managerKey);

        return new ResponseEntity<>("Device deleted successfully", HttpStatus.OK);
    }


}

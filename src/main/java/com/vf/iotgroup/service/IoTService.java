package com.vf.iotgroup.service;

import com.vf.iotgroup.exception.InvalidIoTDocumentException;
import com.vf.iotgroup.exception.InvalidManagerKeyException;
import com.vf.iotgroup.model.IoTDocument;
import com.vf.iotgroup.repository.IoTRepository;
import com.vf.iotgroup.util.SIMStatusUtil;
import com.vf.iotgroup.util.Validation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IoTService {
    @Autowired
    private IoTRepository ioTRepository;

    @Value("${vf.IoTDocument.managerKey}")
    private String configuredKey;

    public List<IoTDocument> findAll() {
        return ioTRepository.findAll();
    }

    public List<IoTDocument> getAllWaitingForActivation() {
        return ioTRepository.findBySimStatus(SIMStatusUtil.WAITING_FOR_ACTIVATION);
    }

    public List<IoTDocument> getAllReadyDevices() {
        return ioTRepository.findByDeviceStatus("READY");
    }

    public IoTDocument addIoTDocument(IoTDocument ioTDocument) {
        if (!Validation.isIoTDocumentValid(ioTDocument))
            throw new InvalidIoTDocumentException();
        Optional<IoTDocument> findIoTDocumentQuery = ioTRepository.findBySimIdAndOperatorCode(ioTDocument.getSimId(), ioTDocument.getOperatorCode());
        if (findIoTDocumentQuery.isPresent()) {
            IoTDocument foundIoTDocument = findIoTDocumentQuery.get();
            if (Validation.isRepeatedSimAndOperator(ioTDocument.getSimId(), foundIoTDocument.getSimId(),
                    ioTDocument.getOperatorCode(), foundIoTDocument.getOperatorCode()))
                throw new InvalidIoTDocumentException();
        }

        return ioTRepository.insert(ioTDocument);
    }

    public IoTDocument updateIoTDocument(String managerKey, IoTDocument ioTDocument) {
        if (!Validation.isManagerKeyValid(managerKey, configuredKey))
            throw new InvalidManagerKeyException();
        if (!Validation.isIoTDocumentValid(ioTDocument))
            throw new InvalidIoTDocumentException();
        Optional<IoTDocument> foundIoTDocumentQuery = ioTRepository.findBySimIdAndOperatorCode(ioTDocument.getSimId(), ioTDocument.getOperatorCode());
        if (foundIoTDocumentQuery.isPresent()) {
            IoTDocument ioTDocumentValues = fillIoTDocumentObject(foundIoTDocumentQuery, ioTDocument);
            return ioTRepository.save(ioTDocumentValues);
        }
        throw new InvalidIoTDocumentException();
    }

    private IoTDocument fillIoTDocumentObject(Optional<IoTDocument> foundIoTDocumentQuery, IoTDocument ioTDocument) {
        IoTDocument ioTDocumentValues = foundIoTDocumentQuery.get();
        if (StringUtils.isNotEmpty(ioTDocument.getSimStatus()))
            ioTDocumentValues.setSimStatus(ioTDocument.getSimStatus());
        if (StringUtils.isNotEmpty(ioTDocument.getCountry()))
            ioTDocumentValues.setCountry(ioTDocument.getCountry());
        if (StringUtils.isNotEmpty(ioTDocument.getDeviceName()))
            ioTDocumentValues.setDeviceName(ioTDocument.getDeviceName());
        if (StringUtils.isNotEmpty(ioTDocument.getDeviceStatus()))
            ioTDocumentValues.setDeviceStatus(ioTDocument.getDeviceStatus());
        if (StringUtils.isNotEmpty(ioTDocument.getTemperature()))
            ioTDocumentValues.setTemperature(ioTDocument.getTemperature());
        return ioTDocumentValues;
    }

    public void deletePersonUsingId(String simId, String operatorCode, String managerKey) {
        if (!Validation.isManagerKeyValid(managerKey, configuredKey))
            throw new InvalidManagerKeyException();
        try {
            ioTRepository.deleteBySimIdAndOperatorCode(simId, operatorCode);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }
    }


}

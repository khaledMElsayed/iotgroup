package com.vf.iotgroup.repository;

import com.vf.iotgroup.model.IoTDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IoTRepository extends MongoRepository<IoTDocument, String> {
    List<IoTDocument> findBySimStatus(String SIMStatus);
    List<IoTDocument> findByDeviceStatus(String deviceStatus);
    Optional<IoTDocument> findBySimId(String simId);
    Optional<IoTDocument> findBySimIdAndOperatorCode(String simId, String operatorCode);
    void deleteBySimIdAndOperatorCode(String simId, String operatorCode);
}

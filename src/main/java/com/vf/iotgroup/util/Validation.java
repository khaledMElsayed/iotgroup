package com.vf.iotgroup.util;


import com.vf.iotgroup.model.IoTDocument;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

public class Validation {

    /**
     * check if SIM have any values the SIMId or OperatorCode must not be null
     * else if all SIM not have any values the Device should be added
     */
    public static boolean isIoTDocumentValid(IoTDocument ioTDocument) {
        if (StringUtils.isNotEmpty(ioTDocument.getSimStatus())
                || StringUtils.isNotEmpty(ioTDocument.getOperatorCode())
                || StringUtils.isNotEmpty(ioTDocument.getSimId())
                || StringUtils.isNotEmpty(ioTDocument.getCountry())) {
            if (StringUtils.isNotEmpty(ioTDocument.getSimId())
                    && StringUtils.isNotEmpty(ioTDocument.getOperatorCode())) {
                return true;
            }
        } else if (StringUtils.isNotEmpty(ioTDocument.getDeviceName())
                || StringUtils.isNotEmpty(ioTDocument.getTemperature())) {
            return true;
        }
        return false;
    }

    public static boolean checkTemperature(int temperature) {
        if (temperature >= -25 && temperature <= 85) {
            return true;
        }
        return false;
    }

    public static boolean isManagerKeyValid(String managerKey, String configuredKey) {
        if (managerKey.equals(configuredKey)) {
            return true;
        }
        return false;
    }

    public static boolean isRepeatedSimAndOperator(String simId, String foundSimId, String operatorCode, String foundOperatorCode) {
        if (simId.equals(foundSimId) && operatorCode.equals(foundOperatorCode)) {
            return true;
        }
        return false;
    }
}

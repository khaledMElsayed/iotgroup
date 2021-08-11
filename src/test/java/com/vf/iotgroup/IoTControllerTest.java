package com.vf.iotgroup;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IoTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/VFIoT/findAll")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllWaitingForActivation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/VFIoT/getAllWaitingForActivation")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllReadyDevices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/VFIoT/getAllReadyDevices")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addIoTDocument() throws Exception {
        String ioTDocument = "{\"simId\":\"123\",\"operatorCode\":\"3456\",\"country\":\"Italy\"" +
                ",\"simStatus\":\"Waiting for activation\",\"deviceName\":\"d-123-89\",\"deviceStatus\":\"READY\"" +
                ",\"temperature\":\"25\"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/VFIoT/device")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ioTDocument)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void updateIoTDocument() throws Exception {
        addIoTDocument();
        String ioTDocument = "{\"simId\":\"123\",\"operatorCode\":\"3456\",\"country\":\"Italy\"" +
                ",\"simStatus\":\"Waiting for activation\",\"deviceName\":\"d-123-89\",\"deviceStatus\":\"READY\"" +
                ",\"temperature\":\"70\"}";
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/VFIoT/device/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(ioTDocument)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void deleteIoTDocument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/VFIoT/device/123/3456/admin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

}

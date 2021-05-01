package com.matome.accounts.requests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FileProcessorTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenFileUploadedVerifyStatusCoe()
            throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "statement.xml",
                MediaType.TEXT_PLAIN_VALUE,
                "<account></account>".getBytes(StandardCharsets.UTF_8));

        mockMvc.perform(multipart("/v1/upload").file(file))
                .andExpect(status().isOk());
    }

}

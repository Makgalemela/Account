package com.matome.accounts.requests;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoResourceAccountTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAccountByAccountNumberWhenItDoesNotExist() throws Exception {
        mockMvc.perform(get("/api/v1/account?accountNumber=112334999999995"))
                .andExpect(status().isNotFound());
    }


    @Test
    public void findAccountByAccountBillsWhenItDoesNotExit() throws Exception {

        mockMvc.perform(get("/api/v1/account/bills?accountNumber=112334999999995"))
                .andExpect(status().isNotFound());
    }
}

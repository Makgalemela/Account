package com.matome.accounts.requests;


import org.junit.*;
import org.junit.rules.TestWatcher;
import org.junit.rules.TestWatchman;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NoResourceAccountTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findAccountByAccountNumberWhenAccountDoesNotExist() throws Exception {
        mockMvc.perform(get("/v1/account?accountNumber=112334999999995"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void findAccountByAccountBillsWhenAccountDoesNotExist() throws Exception {
        mockMvc.perform(get("/v1/account/bills?accountNumber=112334999999995"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAccountContactDetailsWhenAccountDoesNotExist() throws Exception {

        String json = "{\"mobile\": 07393231111, \"home\": \"0739323188\", \"work\": \"0739323188\"," +
                " \"accountNumber\" : 1234567890 }";

        mockMvc.perform(
                post("/v1/account/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void editAccountContactDetailsWhenAccountDoesNotExist() throws Exception {

        String json = "{\"mobile\": 07393231111, \"home\": \"0739323188\", \"work\": \"0739323188\"," +
                " \"accountNumber\" : 1234567890 }";

        mockMvc.perform(
                put("/v1/account/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createAccountAddressDetailsWhenAccountDoesNotExist() throws Exception {

        String json = "{\"line1\": \"1 Main Road\", \"line2\": \"Rondebosch\", \"line3\": \"Cape town\",\"postalCode\": \"1200\"," +
                " \"accountNumber\" : 1234567890 , \"isActive\" : true}";

        mockMvc.perform(
                post("/v1/account/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void editAccountAddressDetailsWhenAccountDoesNotExist() throws Exception {

        String json = "{\"line1\": \"1 Main Road\", \"line2\": \"Rondebosch\", \"line3\": \"Cape town\",\"postalCode\": \"1200\"," +
                " \"accountNumber\" : 1234567890 , \"isActive\" : true}";

        mockMvc.perform(
                put("/v1/account/address")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

}

package com.example.study_hexagonal.senarioTests;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountManagementScenarioTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 계좌_생성_입금_출금_시나리오_테스트() throws Exception {
        // 계좌 생성
        MvcResult createResult = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"initialBalance\": 1000}"))
                .andExpect(status().isCreated())
                .andReturn();

        String accountId = JsonPath.read(createResult.getResponse().getContentAsString(), "$.id");

        // 입금
        mockMvc.perform(post("/accounts/" + accountId + "/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 500}"))
                .andExpect(status().isOk());

        // 출금
        mockMvc.perform(post("/accounts/" + accountId + "/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\": 200}"))
                .andExpect(status().isOk());

        // 잔액 확인
        mockMvc.perform(get("/accounts/" + accountId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(1300));
    }
}

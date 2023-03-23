package it.nave.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nave.dto.DummyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
abstract class BaseTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testWithConverter() throws Exception {
        mockMvc.perform(post("/dummy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(buildDummyDto())))
                .andExpect(status().isNoContent());
    }

    private DummyDto buildDummyDto() {
        var dummyDto = new DummyDto();
        dummyDto.setId(1);
        dummyDto.setName(UUID.randomUUID().toString());
        dummyDto.setTimestamp(Instant.now());
        return dummyDto;
    }
}

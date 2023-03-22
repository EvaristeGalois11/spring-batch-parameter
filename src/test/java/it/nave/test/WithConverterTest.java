package it.nave.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nave.dto.DummyDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(properties = "spring.profiles.active=with-converter")
@AutoConfigureMockMvc
class WithConverterTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testWithConverter() throws Exception {
        mockMvc.perform(post("/dummy")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(buildDummyDto())))
                .andExpect(status().isNoContent());
    }

    private DummyDto buildDummyDto() {
        var dummy = new DummyDto();
        dummy.setId(1);
        dummy.setName(UUID.randomUUID().toString());
        dummy.setTimestamp(Instant.now());
        return dummy;
    }
}

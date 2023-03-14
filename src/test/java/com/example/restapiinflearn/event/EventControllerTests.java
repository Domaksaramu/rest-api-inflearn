package com.example.restapiinflearn.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
//@WebMvcTest // 1.Web 관련 라이브러리만 Bean으로 등록해줌, SlicingTest
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    /*@MockBean // 2. Repository 등록
    EventRepository eventRepository;*/
    @Test
    public void createEvent() throws Exception{
        Event event = Event.builder()
                .id(100)
                        .name("Spring")
                        .description("REST API Development")
                        .beginEnrollmentDateTime(LocalDateTime.of(2023,12,12,00,00))
                        .closeEnrollmentDateTime(LocalDateTime.of(2023,12,12,01,00))
                .beginEventDateTime(LocalDateTime.of(2023,12,12,00,20))
                .endEventDateTime(LocalDateTime.of(2023,12,12,00,40))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .free(true)
                .offline(false)
                .build();
        //Mockito.when(eventRepository.save(event)).thenReturn(event);
        mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event))
                ).andDo(print())
                .andExpect(status().isBadRequest())
        ;
    }

}

package com.example.restapiinflearn.event;

import com.example.restapiinflearn.common.TestDescription;
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
    @TestDescription("정상적으로 입력할 수 없는 값을 사용한 경우에 에러가 발생하는 테스트")
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

    /**
     * //empty input에 대한 처리를 하지 않아 정상처리됨
     *
     * @throws Exception
     */
    @Test
    @TestDescription("입력 값이 비어있는 경우에 에러가 발생하는 테스트")
    public void createEvent_Bad_Request_Empty_Input() throws Exception {
        EventDto eventDto = EventDto.builder().build();

        this.mockMvc.perform(post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(eventDto))
                )
                .andExpect(status().isBadRequest());
    }
    @Test
    @TestDescription("입력 값이 잘못된 경우에 에러가 발생하는 테스트")
    public void createEvent_Bad_Request_Wrong_Input() throws Exception{
        Event event = Event.builder()
                .name("Spring")
                .description("REST API Development")
                .beginEnrollmentDateTime(LocalDateTime.of(2023,12,12,00,00))
                .closeEnrollmentDateTime(LocalDateTime.of(2023,12,10,01,00))
                .beginEventDateTime(LocalDateTime.of(2023,12,12,00,20))
                .endEventDateTime(LocalDateTime.of(2023,12,10,00,40))
                .basePrice(10000)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
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

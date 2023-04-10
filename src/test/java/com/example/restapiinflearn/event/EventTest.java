package com.example.restapiinflearn.event;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
class EventTest {
    @Test
    public void builder(){
        Event event = Event.builder()
                .name("Infleran Spring REST API")
                .description("REST API development with Spring")
                .build();
        assertThat(event).isNotNull();
    }

    @Test
    public void javaBean(){
        //given
        String name = "Bean";
        String description = "Spring";
        //when
        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        //then
        assertThat(event
                .getName()).isEqualTo(name);
        assertThat(event.getDescription())
                .isEqualTo(description);
    }

    @ParameterizedTest
    @MethodSource("parametersForTestFree")
    public void testFree(int basePrice, int maxPrice, boolean expected){
        //given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();
        //when
        event.update();

        //then
        assertThat(event.isFree()).isEqualTo(expected);
    }

    private static Stream<Arguments> parametersForTestFree(){
        return Stream.of(
                Arguments.of(0,0,true),
                Arguments.of(100,0,false),
                Arguments.of(0,100,false),
                Arguments.of(100,200,false)
        );
    }

    @Test
    public void testOffline(){
        Event event;
        //given
        event = Event.builder()
                .location("강남역")
                .build();
        //when
        event.update();

        //then
        assertThat(event.isOffline()).isTrue();
        //given
        event = Event.builder()
                .location("  ")
                .build();
        //when
        event.update();

        //then
        assertThat(event.isOffline()).isFalse();
    }
}
package com.example.restapiinflearn.event;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

@Component
public class EventValidator {
    public void validate(EventDto eventDto, Errors errors){
        if(eventDto.getBasePrice() > eventDto.getMaxPrice() && eventDto.getMaxPrice() > 0){
            //errors.rejectValue("basePrice", "wrongValue", "base price is wrong");
            //errors.rejectValue("maxPrice", "wrongValue", "max price is wrong");
            errors.reject("wrongPrices", "Values for prices are wrong");
        }
        LocalDateTime endEventDateTime = eventDto.getEndEventDateTime();
        if(endEventDateTime.isBefore(eventDto.getBeginEventDateTime()) ||
                endEventDateTime.isBefore(eventDto.getCloseEnrollmentDateTime()) ||
                endEventDateTime.isBefore(eventDto.getBeginEnrollmentDateTime())
        ){
            errors.rejectValue("endEventDateTime", "wrongValue", "endEventDatetime is wrong");
        }
    }
}

package com.example.restapiinflearn.event;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.RepresentationModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/*public class EventResource extends EntityModel<Event>{
    public EventResource(Event event, Link... links){
        super(event);
    }
}*/
public class EventResource extends EntityModel<Event> {
    /*@JsonUnwrapped
    private Event event;*/
    public EventResource(Event event, Iterable<Link> links) {
        super(event, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
    public EventResource(Event event){
        super(event, Links.NONE);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }
     /*   this.event = event;
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
    }

    public Event getEvent(){
        return event;
    }*/
}

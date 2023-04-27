package com.example.restapiinflearn.common;

import com.example.restapiinflearn.index.IndexController;
import org.springframework.hateoas.*;
import org.springframework.validation.Errors;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ErrorsResource extends EntityModel<Errors> {
    public ErrorsResource(Errors content, Iterable<Link> links){
        super(content, links);
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }
    public ErrorsResource(Errors content){
        super(content, Links.NONE);
        add(linkTo(methodOn(IndexController.class).index()).withRel("index"));
    }
}

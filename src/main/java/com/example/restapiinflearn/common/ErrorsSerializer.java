package com.example.restapiinflearn.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.hateoas.EntityModel;
import org.springframework.validation.Errors;

import java.io.IOException;

@JsonComponent
public class ErrorsSerializer extends JsonSerializer<Errors> {

    @Override
    public void serialize(Errors errors1, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName("errors");
        jsonGenerator.writeStartArray();
        errors1.getFieldErrors().stream().forEach(e -> {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("field", e.getField());
                jsonGenerator.writeStringField("objectName",e.getObjectName());
                jsonGenerator.writeStringField("code",e.getCode());
                jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());
                Object rejectedValue = e.getRejectedValue();
                if(rejectedValue != null){
                    jsonGenerator.writeStringField("rejectedValue", String.valueOf(rejectedValue));
                }
                jsonGenerator.writeEndObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        errors1.getGlobalErrors().forEach(e -> {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("objectName",e.getObjectName());
                jsonGenerator.writeStringField("code",e.getCode());
                jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());
                jsonGenerator.writeEndObject();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        jsonGenerator.writeEndArray();
        /*jsonGenerator.writeFieldName("content");
            jsonGenerator.writeStartArray();
            Errors errors1 = errors.getContent();
            errors1.getFieldErrors().stream().forEach(e -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("field", e.getField());
                    jsonGenerator.writeStringField("objectName",e.getObjectName());
                    jsonGenerator.writeStringField("code",e.getCode());
                    jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());
                    Object rejectedValue = e.getRejectedValue();
                    if(rejectedValue != null){
                        jsonGenerator.writeStringField("rejectedValue", String.valueOf(rejectedValue));
                    }
                    jsonGenerator.writeEndObject();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            errors1.getGlobalErrors().forEach(e -> {
                try {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeStringField("objectName",e.getObjectName());
                    jsonGenerator.writeStringField("code",e.getCode());
                    jsonGenerator.writeStringField("defaultMessage",e.getDefaultMessage());
                    jsonGenerator.writeEndObject();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            jsonGenerator.writeEndArray();
        jsonGenerator.writeStartObject();
        jsonGenerator.writeFieldName("_links");
        errors.getLinks().stream().forEach(link -> {
            try {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeFieldName(String.valueOf(link.getRel()));
                jsonGenerator.writeStartObject();
                jsonGenerator.writeStringField("href",link.getHref());
                jsonGenerator.writeEndObject();
                jsonGenerator.writeEndObject();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        jsonGenerator.writeEndObject();
*/
    }
}

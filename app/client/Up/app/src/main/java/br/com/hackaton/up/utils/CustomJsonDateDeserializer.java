package br.com.hackaton.up.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomJsonDateDeserializer extends JsonDeserializer<Date> {

    public static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        String date = p.getText();

        try {
            return this.format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}

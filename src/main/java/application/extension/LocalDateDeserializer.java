package application.extension;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateDeserializer extends JsonDeserializer<Date> {

    private String pattern;

    public LocalDateDeserializer(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        SimpleDateFormat spd = new SimpleDateFormat(pattern);
        Date parse = null;
        try {
            if (p.getValueAsString() != null || !p.getValueAsString().isEmpty()){
                parse = spd.parse(p.getValueAsString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }
}
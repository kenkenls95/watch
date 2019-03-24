package application.extension;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateSerializer extends JsonSerializer<Date> {

    private String pattern;

    public LocalDateSerializer(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        SimpleDateFormat spd = new SimpleDateFormat(pattern);
        gen.writeString(spd.format(value));
    }
}
package com.packt.quarkus.chapter4;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;
import java.util.List;

public class MessageEncoder implements Encoder.Text<java.util.List<Customer>>  {

    @Override
    public String encode(List<Customer> list) throws EncodeException {
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        for(Customer c : list) {
            jsonArray.add(Json.createObjectBuilder()
                    .add("Name", c.getName())
                    .add("Surname", c.getSurname()));
        }
        JsonArray array = jsonArray.build();
        StringWriter buffer = new StringWriter();
        Json.createWriter(buffer).writeArray(array);
        return buffer.toString();
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }

}
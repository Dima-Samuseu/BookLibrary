package com.example.catalog.util;

import com.example.catalog.model.Book;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class BookDeserializer extends JsonDeserializer<Book> {
    @Override
    public Book deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);
        return new Book(
                node.get("isbn").textValue(),
                node.get("name").textValue(),
                node.get("author").textValue(),
                node.get("numberOfPages").intValue(),
                node.get("weight").intValue(),
                node.get("price").decimalValue());
    }
}

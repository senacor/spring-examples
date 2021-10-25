package com.senacor.tecco.ilms.katas.example.e02_contentnegotiation.converter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class CsvConverter<T> extends AbstractHttpMessageConverter<T> {

    private final ObjectMapper objectMapper;

    CsvConverter(ObjectMapper objectMapper) {
        super(new MediaType("application", "csv"));
        this.objectMapper = objectMapper;
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    protected T readInternal(Class<? extends T> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(T object, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        try {
            ObjectWriter objectWriter = getCsvWriter(object);
            try (PrintWriter outputWriter = new PrintWriter(outputMessage.getBody())) {
                outputWriter.write(objectWriter.writeValueAsString(object));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    ObjectWriter getCsvWriter(T object) {
        Set<String> fields = getUniqueFieldNames(object);
        CsvSchema.Builder schemaBuilder = CsvSchema.builder().setUseHeader(false);
        for (String field : fields) {
            schemaBuilder.addColumn(field);
        }
        return new CsvMapper().writerFor(List.class).with(schemaBuilder.build());
    }

    Set<String> getUniqueFieldNames(T object) {
        try {
            JsonNode root = objectMapper.readTree(objectMapper.writeValueAsString(object));
            Set<String> uniqueFieldNames = new LinkedHashSet<>();
            root.forEach(element -> {
                Iterator<String> it = element.fieldNames();
                while (it.hasNext()) {
                    String field = it.next();
                    uniqueFieldNames.add(field);
                }
            });
            return uniqueFieldNames;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

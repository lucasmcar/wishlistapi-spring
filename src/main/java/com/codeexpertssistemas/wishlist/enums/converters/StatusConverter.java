package com.codeexpertssistemas.wishlist.enums.converters;


import com.codeexpertssistemas.wishlist.enums.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter  implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status attribute) {
        if(attribute == null){
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public Status convertToEntityAttribute(String dbData) {
        if(dbData == null){
            return null;
        }
        return Stream.of(Status.values())
                .filter(s -> s.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

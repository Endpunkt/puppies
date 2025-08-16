package de.irahi.welp.component;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.irahi.welp.props.AnimalCharacter;

@Converter
public class AnimalCharacterConverter implements AttributeConverter<Set<AnimalCharacter>, String> {

    @Override
    public String convertToDatabaseColumn(Set<AnimalCharacter> attribute) {
        // Konvertiere das Enum-Set zu einem kommagetrennten String
        return (attribute == null || attribute.isEmpty()) 
                ? "" 
                : attribute.stream()
                           .map(Enum::name)
                           .collect(Collectors.joining(","));
    }

    @Override
    public Set<AnimalCharacter> convertToEntityAttribute(String dbData) {
   
        if (dbData == null || dbData.isEmpty()) {
            return new HashSet<>();
        }
        return Arrays.stream(dbData.split(","))
                     .map(AnimalCharacter::valueOf)
                     .collect(Collectors.toSet());
    }
}

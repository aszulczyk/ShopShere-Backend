/**
 * @author: Anvar Szulczyk
 * @date: Feb 2, 2024
 */
package com.shop.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class IntegerSetConverter implements AttributeConverter<Set<Integer>, String> {
    private static final String SPLIT_CHAR = ";";

    @Override
    public String convertToDatabaseColumn(Set<Integer> integerList) {
    	List<String> stringList = integerList.stream().map(String::valueOf).collect(Collectors.toList());
        return stringList != null ? String.join(SPLIT_CHAR, stringList) : "";
    }

    @Override
    public Set<Integer> convertToEntityAttribute(String string) {
        List<String> stringList = string != null ? new ArrayList<String>(Arrays.asList(string.split(SPLIT_CHAR))) : new ArrayList<String>();
        return stringList.stream().map(Integer::valueOf).collect(Collectors.toSet());
    }
    
}

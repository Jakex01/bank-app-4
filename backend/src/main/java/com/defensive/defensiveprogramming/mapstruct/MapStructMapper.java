package com.defensive.defensiveprogramming.mapstruct;

import com.defensive.defensiveprogramming.model.BankClient;
import com.defensive.defensiveprogramming.model.request.BankClientRequest;
import jakarta.validation.constraints.NotNull;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
    MapStructMapper INSTANCE = Mappers.getMapper(MapStructMapper.class);



    @BeforeMapping
    default void validateInput(BankClientRequest request) {
        if (request == null) {
            throw new NullPointerException("BankClientRequest should not be null");
        }
    }

    BankClient bankClient(BankClientRequest bankClientRequest);

}
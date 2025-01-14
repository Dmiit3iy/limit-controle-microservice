package org.dmit3ii.limitcontrolmicroservice.model.mapper;

import org.dmit3ii.limitcontrolmicroservice.model.Limit;
import org.dmit3ii.limitcontrolmicroservice.model.LimitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LimitMapper {

    @Mapping(target = "limitSum", expression = "java(limit.getLimitSum().doubleValue())")
    LimitDTO toLimitDTO(Limit limit);

    @Mapping(source = "limitSum", target = "limitSum", expression = "java(new java.math.BigDecimal(limitDTO.getLimitSum()))")

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    Limit toLimit(LimitDTO limitDTO);
}

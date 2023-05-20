package com.bdg.domain.request_json;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public record PassengerRequest(
        @JsonProperty(value = "name", required = true)
        @NonNull
        @Length(min = 3, max = 64)
        @Pattern(regexp = "^[A-Za-z]*$")
        String name,

        @JsonProperty(value = "phone", required = true)
        @NonNull
        @Pattern(regexp = "^\\+?[0-9\\s-]+$")
        String phone,

        @JsonProperty(value = "address", required = true)
        @NonNull
        AddressRequest addressRequest
        ) {
}

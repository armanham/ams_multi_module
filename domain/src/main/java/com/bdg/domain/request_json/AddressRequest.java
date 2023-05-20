package com.bdg.domain.request_json;

import com.bdg.domain.model.AddressMod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public record AddressRequest(
        @JsonProperty(value = "country", required = true)
        @NonNull
        @Length(min = 3, max = 64)
        @Pattern(regexp = "^[A-Za-z]*$")
        String country,

        @JsonProperty(value = "city", required = true)
        @NonNull
        @Length(min = 3, max = 64)
        @Pattern(regexp = "^[A-Za-z]*$")
        String city) {

    public AddressMod getModel() {
        return new AddressMod(country, city);
    }
}

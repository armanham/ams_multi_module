package com.bdg.domain.request_json;

import com.bdg.domain.model.CompanyMod;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

import java.sql.Date;

public record CompanyRequest(
        @JsonProperty(value = "name", required = true)
        @NonNull
        @Length(min = 3, max = 64)
        @Pattern(regexp = "^[A-Za-z0-9\\s]*$")
        String name,

        @JsonProperty(value = "found_date", required = true)
        @NonNull
        Date foundDate) {

    public CompanyMod getModel(){
        return new CompanyMod(name, foundDate);
    }
}

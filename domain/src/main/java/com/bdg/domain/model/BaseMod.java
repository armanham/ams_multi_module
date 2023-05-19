package com.bdg.domain.model;


import com.bdg.exception.WrongIdException;
import jakarta.validation.constraints.NotNull;

public abstract class BaseMod {

    private Long id;

    public BaseMod(final Long id) {
        setId(id);
    }

    public BaseMod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(@NotNull final Long id) {
        if (id <= 0) {
            throw new WrongIdException(id);
        }
        this.id = id;
    }
}
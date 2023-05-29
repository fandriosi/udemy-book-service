package com.andriosi.udemy.bookservice.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cambio implements Serializable {
    private  static final long serialVersionUID=1L;

    private Long id;
    private String from;
    private String to;
    private Double convertionFactor;
    private Double convertedValue;
    private String enviroment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cambio cambio = (Cambio) o;
        return Objects.equals(id, cambio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

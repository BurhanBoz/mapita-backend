package com.mapita.mapita_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long companyId;
    private String companyName;
}

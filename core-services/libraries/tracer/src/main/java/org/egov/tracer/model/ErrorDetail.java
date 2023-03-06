package org.egov.tracer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDetail {

    @JsonProperty("apiDetails")
    private ApiDetails apiDetails = null;

    @JsonProperty("errors")
    private List<ErrorEntity> errors = null;

}
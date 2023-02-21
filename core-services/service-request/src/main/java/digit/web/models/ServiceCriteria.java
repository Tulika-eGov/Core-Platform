package digit.web.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
import lombok.Builder;

/**
 * The object will contain all the search parameters for Service .
 */
@Schema(description = "The object will contain all the search parameters for Service .")
@Validated
@javax.annotation.Generated(value = "org.egov.codegen.SpringBootCodegen", date = "2023-02-21T10:29:20.850+05:30[Asia/Kolkata]")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceCriteria {
    @JsonProperty("tenantId")
    @NotNull
    @Size(min = 2, max = 64)
    private String tenantId = null;

    @JsonProperty("ids")
    private List<String> ids = null;

    @JsonProperty("serviceDefIds")
    private List<String> serviceDefIds = null;

    @JsonProperty("referenceIds")
    private List<String> referenceIds = null;


    public ServiceCriteria addIdsItem(String idsItem) {
        if (this.ids == null) {
            this.ids = new ArrayList<>();
        }
        this.ids.add(idsItem);
        return this;
    }

    public ServiceCriteria addServiceDefIdsItem(String serviceDefIdsItem) {
        if (this.serviceDefIds == null) {
            this.serviceDefIds = new ArrayList<>();
        }
        this.serviceDefIds.add(serviceDefIdsItem);
        return this;
    }

    public ServiceCriteria addReferenceIdsItem(String referenceIdsItem) {
        if (this.referenceIds == null) {
            this.referenceIds = new ArrayList<>();
        }
        this.referenceIds.add(referenceIdsItem);
        return this;
    }

}

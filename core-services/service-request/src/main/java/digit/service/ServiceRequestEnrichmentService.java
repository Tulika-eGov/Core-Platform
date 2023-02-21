package digit.service;

import digit.models.coremodels.AuditDetails;
import digit.web.models.Service;
import digit.web.models.ServiceDefinition;
import digit.web.models.ServiceDefinitionRequest;
import org.egov.common.contract.request.RequestInfo;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ServiceRequestEnrichmentService {

    public void enrichServiceDefinitionRequest(ServiceDefinitionRequest serviceDefinitionRequest) {
        ServiceDefinition serviceDefinition = serviceDefinitionRequest.getServiceDefinition();
        RequestInfo requestInfo = serviceDefinitionRequest.getRequestInfo();

        // Enrich ID for service definition
        serviceDefinition.setId(UUID.randomUUID().toString());

        // Prepare audit details
        AuditDetails auditDetails = new AuditDetails();
        auditDetails.setCreatedBy(requestInfo.getUserInfo().getUuid());
        auditDetails.setLastModifiedBy(requestInfo.getUserInfo().getUuid());
        auditDetails.setCreatedTime(System.currentTimeMillis());
        auditDetails.setLastModifiedTime(System.currentTimeMillis());

        // Enrich audit details for attributes
        serviceDefinition.getAttributes().forEach(attribute -> {
            attribute.setId(UUID.randomUUID().toString());
            attribute.setAuditDetails(auditDetails);
            attribute.setReferenceId(serviceDefinition.getId());
        });

        // Enrich audit details for service definition
        serviceDefinition.setAuditDetails(auditDetails);

    }

    public void enrichServiceRequest(Service service) {
    }
}

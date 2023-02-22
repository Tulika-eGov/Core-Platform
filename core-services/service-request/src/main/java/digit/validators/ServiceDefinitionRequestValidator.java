package digit.validators;

import digit.repository.ServiceDefinitionRequestRepository;
import digit.web.models.*;
import org.egov.tracer.model.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static digit.error.ErrorCode.*;

@Component
public class ServiceDefinitionRequestValidator {

    @Autowired
    private ServiceDefinitionRequestRepository serviceDefinitionRequestRepository;

    public void validateServiceDefinitionRequest(ServiceDefinitionRequest serviceDefinitionRequest){
        ServiceDefinition serviceDefinition = serviceDefinitionRequest.getServiceDefinition();

        // Validate if a service definition with the same combination of tenantId and code already exists
        validateServiceDefinitionExistence(serviceDefinition);

        // Validate if all attribute definitions provided as part of service definitions have unique code
        validateAttributeDefinitionUniqueness(serviceDefinition);

    }

    private void validateAttributeDefinitionUniqueness(ServiceDefinition serviceDefinition) {
        Set<String> attributeCodes = new HashSet<>();

        serviceDefinition.getAttributes().forEach(attributeDefinition -> {
            if(attributeCodes.contains(attributeDefinition.getCode())){
                throw new CustomException(ATTRIBUTE_CODE_UNIQUENESS_ERR_CODE, ATTRIBUTE_CODE_UNIQUENESS_ERR_MSG);
            }else{
                attributeCodes.add(attributeDefinition.getCode());
            }
        });
    }

    private void validateServiceDefinitionExistence(ServiceDefinition serviceDefinition) {
        List<ServiceDefinition> serviceDefinitionList = serviceDefinitionRequestRepository.getServiceDefinitions(ServiceDefinitionSearchRequest.builder().serviceDefinitionCriteria(ServiceDefinitionCriteria.builder().tenantId(serviceDefinition.getTenantId()).code(Collections.singletonList(serviceDefinition.getCode())).build()).pagination(Pagination.builder().offset(0).limit(10).build()).build());
        if(!CollectionUtils.isEmpty(serviceDefinitionList)){
            throw new CustomException(SERVICE_DEFINITION_ALREADY_EXISTS_ERR_CODE, SERVICE_DEFINITION_ALREADY_EXISTS_ERR_MSG);
        }
    }

    public void validateUpdateRequest(ServiceDefinitionRequest serviceDefinitionRequest) {

        // TO DO

    }

}

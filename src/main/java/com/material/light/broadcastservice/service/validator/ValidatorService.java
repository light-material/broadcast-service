package com.material.light.broadcastservice.service.validator;

import com.material.light.broadcastservice.model.contract.BaseRequest;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
import com.material.light.broadcastservice.model.exception.InvalidParameterException;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ValidatorService {

    public void beanValidate(BaseRequest request) throws InvalidParameterException {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<BaseRequest>> constraintViolations = validator.validate(request);

        if (!constraintViolations.isEmpty()) {
            ConstraintViolation<BaseRequest> violation = constraintViolations.iterator().next();
            String constraintViolationMessage = String.format("Invalid value[%s] for %s.",
                    violation.getInvalidValue(), violation.getPropertyPath());
            throw new InvalidParameterException(ResponseEnum.INVALID_PARAMETER, constraintViolationMessage);
        }
    }
}

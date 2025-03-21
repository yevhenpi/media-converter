package ua.pidopryhora.mediaconverter.requestmanager.model.validation.jakarta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ua.pidopryhora.mediaconverter.requestmanager.model.RequestDTO;
import ua.pidopryhora.mediaconverter.requestmanager.service.IdempotencyService;
@RequiredArgsConstructor
public class IdempotencyValidator  implements ConstraintValidator<IdempotencyCheck, RequestDTO> {

    private final IdempotencyService idempotencyService;

    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return idempotencyService.addIdempotencyKey(requestDTO);
    }
}

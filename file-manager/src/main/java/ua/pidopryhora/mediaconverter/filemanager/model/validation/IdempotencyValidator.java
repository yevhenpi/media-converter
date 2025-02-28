package ua.pidopryhora.mediaconverter.filemanager.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import ua.pidopryhora.mediaconverter.common.model.RequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.IdempotencyService;
@RequiredArgsConstructor
public class IdempotencyValidator  implements ConstraintValidator<IdempotencyCheck, RequestDTO> {

    private final IdempotencyService idempotencyService;

    @Override
    public boolean isValid(RequestDTO requestDTO, ConstraintValidatorContext constraintValidatorContext) {
        return idempotencyService.addIdempotencyKey(requestDTO.toString());
    }
}

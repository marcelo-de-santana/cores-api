package ada.tech.controller.response;

import ada.tech.controller.request.ColorRequest;
import jakarta.validation.ConstraintViolation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private Collection<FieldErrorResponse> errors;

    public static ErrorResponse fromViolations(Set<ConstraintViolation<ColorRequest>> constraintViolations) {
        Collection<FieldErrorResponse> violations =
                constraintViolations.stream()
                        .map(constraintViolation ->
                                new FieldErrorResponse(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()))
                        .toList();

        return new ErrorResponse("Validation error", violations);
    }
}

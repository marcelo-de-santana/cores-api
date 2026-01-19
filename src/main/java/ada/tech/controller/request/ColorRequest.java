package ada.tech.controller.request;

import ada.tech.domain.entity.ColorEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ColorRequest(
        @NotBlank(message = "O campo não pode estar em branco")
        String name,
        @NotNull(message = "O campo não pode ser nulo")
        String hexadecimal
) {
}

package ifsc.edu.tpj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostRequestDTO(
        @NotBlank @NotNull
        String title,
        @NotBlank @NotNull
        String body,
        @NotNull
        Long userId
) {
}

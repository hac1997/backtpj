package ifsc.edu.tpj.dto;

import ifsc.edu.tpj.model.User;

public record UserResponseDTO(
        Long userId,
        String name,
        String email
) {
    public static UserResponseDTO fromEntity(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponseDTO(
                user.getUserId(),
                user.getName(),
                user.getEmail()
        );
    }
}

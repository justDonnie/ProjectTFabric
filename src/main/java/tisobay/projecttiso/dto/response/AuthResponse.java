package tisobay.projecttiso.dto.response;

import lombok.Builder;
@Builder
public record AuthResponse(
        String token,
        String email) {
}

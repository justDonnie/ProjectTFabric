package tisobay.projecttiso.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import tisobay.projecttiso.validation.PasswordValid;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    @NotBlank
    String email;
    @NotBlank
    @PasswordValid
    String password;

    public @NotBlank String getEmail() {
        return email;
    }

    public @NotBlank @PasswordValid String getPassword() {
        return password;
    }

    public void setEmail(@NotBlank String email) {
        this.email = email;
    }

    public void setPassword(@NotBlank @PasswordValid String password) {
        this.password = password;
    }
}

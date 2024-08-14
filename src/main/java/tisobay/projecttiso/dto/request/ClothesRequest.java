package tisobay.projecttiso.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClothesRequest {
    @NotBlank(message = "Image can't be blank")
    private String image;
    @NotBlank(message = "Name can't be blank")
    private String nameOfCloth;
    @NotBlank(message = "Description can't be blank")
    private String description;

    public ClothesRequest(String image, String nameOfCloth, String description) {
        this.image = image;
        this.nameOfCloth = nameOfCloth;
        this.description = description;
    }
}

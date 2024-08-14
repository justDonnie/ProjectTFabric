package tisobay.projecttiso.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class ClothesResponse {
    private Long id;
    private String image;
    private String nameOfCloth;
    private String description;

    public ClothesResponse(Long id,String image, String nameOfCloth, String description) {
        this.id=id;
        this.image = image;
        this.nameOfCloth = nameOfCloth;
        this.description = description;
    }
}

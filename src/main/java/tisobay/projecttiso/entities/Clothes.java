package tisobay.projecttiso.entities;

import jakarta.persistence.*;
import lombok.*;
import tisobay.projecttiso.dto.response.ClothesResponse;

@Entity
@Table(name = "clothes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clothes {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothes_gen")
    @SequenceGenerator(
            name = "clothes_gen",
            sequenceName = "clothes_seq",
            allocationSize = 1
    )
    private Long id;
    private String image;
    private String nameOfCloth;
    private String description;

    public static ClothesResponse entityToResponse(Clothes clothes){
        return ClothesResponse.builder()
                .id(clothes.id)
                .image(clothes.getImage())
                .nameOfCloth(clothes.getNameOfCloth())
                .description(clothes.getDescription())
                .build();
    }
}

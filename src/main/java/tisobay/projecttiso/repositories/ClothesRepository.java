package tisobay.projecttiso.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.entities.Clothes;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    @Query("SELECT new tisobay.projecttiso.dto.response.ClothesResponse(c.id,c.image, c.nameOfCloth, c.description) FROM Clothes c WHERE c.id = :id ")
    ClothesResponse getClothesById(@Param("id") Long id);


    @Query("select new tisobay.projecttiso.dto.response.ClothesResponse(c.id,c.image,c.nameOfCloth,c.description)FROM Clothes c ")
    List<ClothesResponse> getAllClothes();
}
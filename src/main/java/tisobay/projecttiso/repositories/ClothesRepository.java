package tisobay.projecttiso.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.entities.Clothes;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    ClothesResponse getClothesById(Long id);

    @Query(value = "select c.id,c.image,c.name_of_cloth,c.description from clothes c ",nativeQuery = true)
    List<ClothesResponse> getAllClothes();
}
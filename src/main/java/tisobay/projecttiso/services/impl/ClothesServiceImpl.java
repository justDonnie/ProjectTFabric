package tisobay.projecttiso.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tisobay.projecttiso.dto.request.ClothesRequest;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.dto.response.SimpleResponse;
import tisobay.projecttiso.entities.Clothes;
import tisobay.projecttiso.repositories.ClothesRepository;
import tisobay.projecttiso.services.ClothesService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository clothesRepository;

    @Override
    @Transactional
    public ClothesResponse addCloth(ClothesRequest request) {
        log.info("New Cloth is creating...");
        Clothes clothes = new Clothes();
        clothes.setImage(request.getImage());
        clothes.setNameOfCloth(request.getNameOfCloth());
        clothes.setDescription(request.getDescription());
        clothesRepository.save(clothes);
        log.info("Cloth is created and returning...");
        return Clothes.entityToResponse(clothes);
    }

    @Override
    public ClothesResponse getClothById(Long clothId) {
        return clothesRepository.getClothesById(clothId);
    }

    @Override
    public List<ClothesResponse> getAllClothes() {
        return clothesRepository.getAllClothes();
    }

    @Override
    public ClothesResponse updateClothById(Long clothId) {
        return null;
    }

    @Override
    public SimpleResponse deleteClothById(Long clothId) {
        return null;
    }
}

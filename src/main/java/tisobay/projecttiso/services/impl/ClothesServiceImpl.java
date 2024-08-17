package tisobay.projecttiso.services.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tisobay.projecttiso.dto.request.ClothesRequest;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.dto.response.SimpleResponse;
import tisobay.projecttiso.entities.Clothes;
import tisobay.projecttiso.enums.Role;
import tisobay.projecttiso.exceptions.NotFoundException;
import tisobay.projecttiso.repositories.ClothesRepository;
import tisobay.projecttiso.services.AuthenticationService;
import tisobay.projecttiso.services.ClothesService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClothesServiceImpl implements ClothesService {
    private final ClothesRepository clothesRepository;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public ClothesResponse addCloth(ClothesRequest request) {
        log.info("Checking authentication in add cloth method...");
        authenticationService.checkAuth(Role.ADMIN);
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
    public ClothesResponse updateClothById(Long clothId, ClothesRequest request) {
        log.info("Checking authentication in update cloth method...");
        authenticationService.checkAuth(Role.ADMIN);
        log.info("Method update clothes by id is started in service class...");
        Clothes clothes = clothesRepository.findById(clothId).orElseThrow(
                () -> new NotFoundException("There is no cloth with id " + clothId));
        if (clothes != null) {
            clothes.setImage(request.getImage());
            clothes.setNameOfCloth(request.getNameOfCloth());
            clothes.setDescription(request.getDescription());
            clothesRepository.save(clothes);
        }
        assert clothes != null;
        return Clothes.entityToResponse(clothes);
    }

    @Override
    public SimpleResponse deleteClothById(Long clothId) {
        log.info("Checking authentication in delete cloth method...");
        authenticationService.checkAuth(Role.ADMIN);
        clothesRepository.deleteById(clothId);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Clothes successfully deleted !!! ")
                .build();
    }
}

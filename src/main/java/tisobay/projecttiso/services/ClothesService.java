package tisobay.projecttiso.services;

import java.util.List;
import tisobay.projecttiso.dto.request.ClothesRequest;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.dto.response.SimpleResponse;

public interface ClothesService {
    ClothesResponse addCloth(ClothesRequest request);
    ClothesResponse getClothById(Long clothId);
    List<ClothesResponse> getAllClothes();
    ClothesResponse updateClothById(Long clothId, ClothesRequest request);
    SimpleResponse deleteClothById(Long clothId);
}

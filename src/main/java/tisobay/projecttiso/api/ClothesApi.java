package tisobay.projecttiso.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tisobay.projecttiso.dto.request.ClothesRequest;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.services.ClothesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clothes")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Clothes API")
public class ClothesApi {
    private final ClothesService clothesService;

    @PostMapping("/addCloth")
    @Operation(description = "Метод добавление одежду ")
    @PreAuthorize("hasAuthority('ADMIN')")
    ClothesResponse addCloth(@RequestBody ClothesRequest request){
        return clothesService.addCloth(request);
    }

    @GetMapping("/getClothById")
    @Operation(description = "Метод получения одежд по идентификатору ")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    ClothesResponse getById(@RequestParam Long id){
        return clothesService.getClothById(id);
    }

    @GetMapping("/getAllClothes")
    @Operation(description = "Метод получения всех одежд ")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    List<ClothesResponse> getAllClothes(){
        return clothesService.getAllClothes();
    }


}

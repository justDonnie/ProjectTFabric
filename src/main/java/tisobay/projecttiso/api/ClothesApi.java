package tisobay.projecttiso.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tisobay.projecttiso.dto.request.ClothesRequest;
import tisobay.projecttiso.dto.response.ClothesResponse;
import tisobay.projecttiso.dto.response.SimpleResponse;
import tisobay.projecttiso.services.ClothesService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clothes")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Clothes API")
@Slf4j
public class ClothesApi {
    private final ClothesService clothesService;

    @PostMapping("/addCloth")
    @Operation(description = "Метод добавление одежду ")
    ClothesResponse addCloth(@RequestBody ClothesRequest request){
        log.info("Clothes API: add cloth method...");
        return clothesService.addCloth(request);
    }

    @GetMapping("/getClothById")
    @Operation(description = "Метод получения одежд по идентификатору ")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    ClothesResponse getById(@RequestParam Long id){
        log.info("Clothes API: getClothById method...");
        return clothesService.getClothById(id);
    }

    @GetMapping("/getAllClothes")
    @Operation(description = "Метод получения всех одежд ")
    List<ClothesResponse> getAllClothes(){
        return clothesService.getAllClothes();
    }

    @PutMapping("/updateClothes")
    @Operation(description = "Метод для обновления одежд ")
    ClothesResponse updateCloth(@RequestParam Long id, @RequestBody ClothesRequest request){
        log.info("Clothes API: updateClothes method...");
        return clothesService.updateClothById(id, request);
    }

    @DeleteMapping("/deleteClothes")
    @Operation(description = "Метод для удаления одежд по идентификатору ")
    SimpleResponse deleteCloth(@RequestParam Long id){
        log.info("Clothes API: deleteCloth method...");
        return clothesService.deleteClothById(id);
    }
}
package kr.co.api.manager.db.favorite.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.favorite.model.FavoriteModel;
import kr.co.api.manager.db.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping(value = "/switchFavorite")
    @ApiOperation(value = "BoardController", response = Integer.class)
    public DataResponse insertBoard(
            @Valid @RequestParam String uid,
            @Valid @RequestParam String productId
            ) throws IOException {
        return DataResponse.builder().data(favoriteService.switchFavorite(uid, productId)).build();
    }

}
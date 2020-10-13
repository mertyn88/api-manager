package kr.co.api.manager.db.favorite.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.db.board.model.ProductModel;
import kr.co.api.manager.db.favorite.model.FavoriteModel;
import kr.co.api.manager.db.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/favorite")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @PostMapping(value = "/switchFavorite")
    @ApiOperation(value = "관심상품 토글", response = Integer.class)
    public DataResponse insertBoard(
            @Valid @RequestParam String uid,
            @Valid @RequestParam String productId
            ) throws IOException {
        return DataResponse.builder().data(favoriteService.switchFavorite(uid, productId)).build();
    }

    @GetMapping("/selectFavorite")
    @ApiOperation(value = "관심상품 리스트", response = ProductModel.class)
    public DataResponse selectFavorite(@Valid @RequestParam String uid) {
        return DataResponse.builder().data(favoriteService.selectFavorite(uid)).build();
    }

}
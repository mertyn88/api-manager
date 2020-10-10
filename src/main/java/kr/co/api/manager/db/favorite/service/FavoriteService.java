package kr.co.api.manager.db.favorite.service;

import kr.co.api.manager.db.favorite.model.FavoriteModel;

import java.util.List;

public interface FavoriteService {

    int switchFavorite(String uid, String productId);
    List<FavoriteModel> selectFavorite(String uid);
}

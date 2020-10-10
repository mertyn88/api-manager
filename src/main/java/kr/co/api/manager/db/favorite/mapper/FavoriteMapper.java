package kr.co.api.manager.db.favorite.mapper;

import kr.co.api.core.db.annotation.MasterConnection;
import kr.co.api.manager.db.favorite.model.FavoriteModel;

import java.util.List;

@MasterConnection
public interface FavoriteMapper {

    /** 관심상품 토글 **/
    int switchFavorite(String uid, String productId);
    /** 관심상품 리스트 **/
    List<FavoriteModel> selectFavorite(String uid);
}
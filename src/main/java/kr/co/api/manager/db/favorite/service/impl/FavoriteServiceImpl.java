package kr.co.api.manager.db.favorite.service.impl;

import kr.co.api.manager.db.favorite.mapper.FavoriteMapper;
import kr.co.api.manager.db.favorite.model.FavoriteModel;
import kr.co.api.manager.db.favorite.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteMapper favoriteMapper;

    @Override
    public int switchFavorite(String uid, String productId) {

        /** Merge Into **/
        try{
            favoriteMapper.switchFavorite(uid, productId);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return HttpStatus.OK.value();
    }

    @Override
    public List<FavoriteModel> selectFavorite(String uid) {
        return favoriteMapper.selectFavorite(uid);
    }


}

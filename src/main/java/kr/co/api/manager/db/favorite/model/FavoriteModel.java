package kr.co.api.manager.db.favorite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteModel {

    private String uid;
    private Integer productId;
    private String productName;
    private Integer ProductPrice;
    private String imageUrl;
    private String regDate;
}

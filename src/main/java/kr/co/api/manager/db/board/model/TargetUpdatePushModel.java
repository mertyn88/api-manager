package kr.co.api.manager.db.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetUpdatePushModel {
    private String title;
    private String baseUrl;
    private String imageUrl;
    private String targetMessageToken;
}

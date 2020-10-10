package kr.co.api.manager.db.board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetListModel {

   private String targetUid;
   private String targetName;
   private String targetImageUrl;
}

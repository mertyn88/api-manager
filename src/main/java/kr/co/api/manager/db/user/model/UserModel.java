package kr.co.api.manager.db.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserModel {
    private String uid;
    private String name;
    private String email;
    private String imageUrl;
    private String token;
}


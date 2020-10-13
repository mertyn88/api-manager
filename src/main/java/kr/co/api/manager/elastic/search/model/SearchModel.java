package kr.co.api.manager.elastic.search.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchModel {

    private String targetUrl;
    private String baseUrl;
    private String title;
    private String content;
    private String keyword;
    private String regDate;
    private String imageUrl;

    //검색어
    private String query;
}

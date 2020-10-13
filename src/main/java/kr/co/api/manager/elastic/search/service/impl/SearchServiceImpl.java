package kr.co.api.manager.elastic.search.service.impl;

import kr.co.api.core.config.ElasticConfig;
import kr.co.api.core.properties.ElasticProperties;
import kr.co.api.manager.elastic.search.model.SearchModel;
import kr.co.api.manager.elastic.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class SearchServiceImpl implements SearchService {

    // 검색 config
    private final ElasticConfig elasticConfig;

    // 검색 properties
    private final ElasticProperties elasticProperties;

    @Override
    public List<SearchModel> normalSearch(String query) {
        /** 검색 쿼리 **/
        SearchSourceBuilder sourceBuilder = keywordSearch(query);
        /** 검색 엔진 생성자 **/
        SearchRequest searchRequest = new SearchRequest(elasticProperties.getIndices());
        /** 검색 설정 셋 **/
        searchRequest.source(sourceBuilder);

        /** 검색 요청 **/
        SearchResponse searchResponse = null;
        try{
            searchResponse = elasticConfig.client().search(searchRequest, RequestOptions.DEFAULT);
        }catch (Exception e){
            e.printStackTrace();
        }

        //검색결과
        SearchHits hits = searchResponse.getHits();
        //검색결과저장
        List<SearchModel> searchResult = new ArrayList<>();

        TotalHits totalHits = hits.getTotalHits();
        // the total number of hits, must be interpreted in the context of totalHits.relation
        long numHits = totalHits.value;
        // whether the number of hits is accurate (EQUAL_TO) or a lower bound of the total (GREATER_THAN_OR_EQUAL_TO)
        TotalHits.Relation relation = totalHits.relation;
        float maxScore = hits.getMaxScore();

        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            // do something with the SearchHit
            String sourceAsString = hit.getSourceAsString();
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            String documentTitle = (String) sourceAsMap.get("title");

            //   Map<String, Object> innerObject =
            //   (Map<String, Object>) sourceAsMap.get("innerObject");

            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField highlight = highlightFields.get("content");
            Text[] fragments = highlight.fragments();
            String fragmentString = fragments[0].string();


            searchResult.add(
              new SearchModel(){{
                  setTitle((String)sourceAsMap.get("title"));
                  setBaseUrl((String)sourceAsMap.get("base_url"));
                  setTargetUrl((String)sourceAsMap.get("target_url"));
                  setKeyword((String)sourceAsMap.get("keyword"));
                  setRegDate((String)sourceAsMap.get("reg_date"));
                  setContent(fragmentString);
                  setImageUrl((String)sourceAsMap.get("image_path"));
                  setKeyword(query);
              }}
            );
        }

        return searchResult;
    }

    private SearchSourceBuilder keywordSearch(String query) {
        /** query **/
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("content", query);


        /** highlight **/
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        /** field **/
        HighlightBuilder.Field highlightContent = new HighlightBuilder.Field("content");
        highlightContent.preTags("<HS>");
        highlightContent.postTags("</HE>");
        highlightContent.numOfFragments(1);
        highlightContent.fragmentSize(150);
        highlightContent.highlighterType("fvh");
        highlightBuilder.field(highlightContent);

        /** 쿼리 생성 **/
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder
                .timeout(new TimeValue(elasticProperties.getTimeOut(), TimeUnit.SECONDS))
                .size(10)
                .query(matchQueryBuilder)
                .highlighter(highlightBuilder)
                .fetchSource(null, new String[]{"content"})
        ;
        return sourceBuilder;
    }

}

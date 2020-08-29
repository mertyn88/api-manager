package kr.co.api.manager.elastic.aggregation.service.impl;

import kr.co.api.core.config.ElasticConfig;
import kr.co.api.core.properties.ElasticProperties;
import kr.co.api.manager.elastic.aggregation.model.AggregationModel;
import kr.co.api.manager.elastic.aggregation.service.AggregationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AggregationServiceImpl implements AggregationService {

    // 검색 config
    private final ElasticConfig elasticConfig;

    // 검색 properties
    private final ElasticProperties elasticProperties;

    @Override
    public List<AggregationModel> aggregationKeyword() {

        /** 검색 쿼리 **/
        SearchSourceBuilder sourceBuilder = aggregationSearch();
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

        /** 결과 저장 **/
        List<AggregationModel> responseAggregation = new ArrayList<>();
        /** 집계 결과 **/
        ParsedStringTerms agg = searchResponse.getAggregations().get("agg");
        for (ParsedStringTerms.ParsedBucket pb : (List<ParsedStringTerms.ParsedBucket>) agg.getBuckets()) {

            System.out.println(pb.getKeyAsString());
            System.out.println(pb.getDocCount());

            /** 검색 결과 저장 **/
            responseAggregation.add(new AggregationModel(){{
                setKeyword(pb.getKeyAsString());
                setCount(pb.getDocCount());
            }});


            /*if (indexerPopularMap.get(key) != null) {
                IndexerPopular popular = indexerPopularMap.get(key);
                popular.setCount(popular.getCount() + pb.getDocCount());
                indexerPopularMap.put(key, popular);
            } else {
                IndexerPopular indexerPopular = new IndexerPopular();
                indexerPopular.setKeyword(pb.getKeyAsString());
                indexerPopular.setCount(pb.getDocCount());
                indexerPopularMap.put(key, indexerPopular);
            }*/
        }
        return responseAggregation;
    }


    private SearchSourceBuilder aggregationSearch() {

        /** query **/
        //BoolQueryBuilder query = baseQuery.filterQuery(filterParam);

        /** Aggregation 생성 **/
        TermsAggregationBuilder aggr = AggregationBuilders.terms("agg")
                .field(elasticProperties.getAggsFields())
                .size(elasticProperties.getSize());

        /** 쿼리 생성 **/
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder
                .timeout(new TimeValue(elasticProperties.getTimeOut(), TimeUnit.SECONDS))
                .size(0)
                //.query(query)
                .aggregation(aggr);

        log.debug("Aggregation Query {}",sourceBuilder.toString());

        return sourceBuilder;
    }
}

package kr.co.api.manager.elastic.aggregation.service;

import kr.co.api.manager.elastic.aggregation.model.AggregationModel;
import java.util.List;

public interface AggregationService {
    List<AggregationModel> aggregationKeyword();
}

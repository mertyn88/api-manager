package kr.co.api.manager.elastic.aggregation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggregationModel {
    private String keyword;
    private Long count;
}

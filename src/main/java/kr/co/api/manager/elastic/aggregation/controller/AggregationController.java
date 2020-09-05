package kr.co.api.manager.elastic.aggregation.controller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.elastic.aggregation.model.AggregationModel;
import kr.co.api.manager.elastic.aggregation.service.AggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aggregation")
@RequiredArgsConstructor
public class AggregationController {

    private final AggregationService aggregationService;


    @GetMapping("/keyword")
    @ApiOperation(value = "Elastic 카테고리 집계", response = AggregationModel.class)
    public DataResponse aggregationKeyword() {
        return DataResponse.builder().data(aggregationService.aggregationKeyword()).build();
    }

}



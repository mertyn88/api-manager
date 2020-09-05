package kr.co.api.manager.elastic.search.conroller;

import io.swagger.annotations.ApiOperation;
import kr.co.api.core.response.DataResponse;
import kr.co.api.manager.elastic.search.model.SearchModel;
import kr.co.api.manager.elastic.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @PostMapping("/keyword")
    @ApiOperation(value = "일반검색", response = SearchModel.class)
    public DataResponse keywordSearch(@Valid @RequestBody SearchModel searchModel) {
        return DataResponse.builder().data(searchService.normalSearch(searchModel.getQuery())).build();
    }

}

package kr.co.api.manager.elastic.search.service;

import kr.co.api.manager.elastic.search.model.SearchModel;

import java.util.List;

public interface SearchService {
    List<SearchModel> normalSearch(String query);
}

package com.zeowls.data.mapper;

import com.zeowls.data.source.local.database.SearchEntity;
import com.zeowls.domain.entity.Search;
import com.zeowls.domain.scope.ApplicationScope;

import javax.inject.Inject;

@ApplicationScope
public class SearchMapper extends Mapper<Search, SearchEntity> {
    @Inject
    SearchMapper() {
    }

    public Search map(SearchEntity searchEntity) {
        if (searchEntity == null) {
            return null;
        }
        Search search = new Search();
        search.setId(searchEntity.getId());
        search.setName(searchEntity.getName());
        search.setNameAr(searchEntity.getNameAr());
        search.setRecent(searchEntity.getRecent());
        search.setType(searchEntity.getType());
        return search;
    }

    public SearchEntity reverse(Search search) {
        if (search == null) {
            return null;
        }
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setId(search.getId());
        searchEntity.setName(search.getName());
        searchEntity.setNameAr(search.getNameAr());
        searchEntity.setRecent(search.getRecent());
        searchEntity.setType(search.getType());
        return searchEntity;
    }
}

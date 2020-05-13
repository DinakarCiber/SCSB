package org.recap.util;

import org.junit.Test;
import org.recap.BaseTestCase;
import org.recap.model.search.SearchRecordsRequest;
import org.recap.model.search.SearchRecordsResponse;
import org.recap.model.search.SearchResultRow;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by rajeshbabuk on 3/1/17.
 */
public class SearchUtilUT extends BaseTestCase {

    @Autowired
    SearchUtil searchUtil;

    @Test
    public void requestSearchResults() throws Exception {
        SearchRecordsRequest searchRecordsRequest = new SearchRecordsRequest();
        searchRecordsRequest.setFieldName("");
        searchRecordsRequest.setFieldValue("");
        searchRecordsRequest.setTotalBibRecordsCount("1");
        searchRecordsRequest.setTotalItemRecordsCount("1");
        searchRecordsRequest.setTotalRecordsCount("1");
        searchRecordsRequest.setTotalPageCount(1);
        SearchRecordsResponse searchRecordsResponse = searchUtil.requestSearchResults(searchRecordsRequest);
        assertNotNull(searchRecordsResponse);
        List<SearchResultRow> searchResultRows = searchRecordsResponse.getSearchResultRows();
        assertNotNull(searchResultRows);
    }
}

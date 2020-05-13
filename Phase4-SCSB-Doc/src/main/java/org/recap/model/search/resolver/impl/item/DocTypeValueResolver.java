package org.recap.model.search.resolver.impl.item;

import org.recap.model.search.resolver.ItemValueResolver;
import org.recap.model.solr.Item;

/**
 * Created by peris on 9/29/16.
 */
public class DocTypeValueResolver implements ItemValueResolver {
    @Override
    public Boolean isInterested(String field) {
        return "DocType".equals(field);
    }

    @Override
    public void setValue(Item item, Object value) {
        item.setDocType((String)value);
    }
}

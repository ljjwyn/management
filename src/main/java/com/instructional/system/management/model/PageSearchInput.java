package com.instructional.system.management.model;

import lombok.Data;
import lombok.NonNull;

/**
 * @author ljjwyn
 */
@Data
public class PageSearchInput {
    @NonNull
    private int page;
    @NonNull
    private String searchType;
}

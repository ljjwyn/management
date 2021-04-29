package com.instructional.system.management.model;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;

/**
 * @author ljjwyn
 */
@Data
public class TopTailScoresInput {
    @NonNull
    private String exam;
    @NonNull
    private String subject;
}

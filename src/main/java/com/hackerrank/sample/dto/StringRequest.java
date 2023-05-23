package com.hackerrank.sample.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@ApiModel
@Getter
@JsonDeserialize(builder = StringRequest.StringRequestBuilder.class)
public final class StringRequest {
    @NotBlank
    @ApiModelProperty("The name of the record.")
    private final String name;

    @ApiModelProperty("The message of the record.")
    private final String message;

    @Builder(toBuilder = true)
    private StringRequest(final String name, final String message) {
        this.name = name;
        this.message = message;
    }

    @JsonPOJOBuilder(withPrefix = StringUtils.EMPTY)
    public static final class StringRequestBuilder {
    }
}

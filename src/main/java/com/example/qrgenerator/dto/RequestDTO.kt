package com.example.qrgenerator.dto

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank

data class RequestDTO (
    @field:NotBlank
    @JsonProperty("data")
    val data : String
)
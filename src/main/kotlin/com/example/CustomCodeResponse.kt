package com.example

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable

@Introspected
@Serdeable
data class CustomCodeResponse(
    val customCode: Boolean
)
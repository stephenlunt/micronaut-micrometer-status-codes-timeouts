package com.example

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono


@Controller("/custom-status-code")
class CustomStatusCodeController(
    private val customStatusCodeClient: CustomStatusCodeClient
) {

    @Get("/520")
    @SingleResult
    fun customCode(): Mono<HttpResponse<CustomCodeResponse>> {
        return customStatusCodeClient.requestEndpoint()
    }
}
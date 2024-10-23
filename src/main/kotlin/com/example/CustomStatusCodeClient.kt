package com.example

import io.micronaut.core.async.annotation.SingleResult
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import reactor.core.publisher.Mono

@Client(id = "wiremock")
interface CustomStatusCodeClient {

    @Get("http://localhost:8086/wiremock-custom-status-code")
    @SingleResult
    fun requestEndpoint(): Mono<HttpResponse<CustomCodeResponse>>

}
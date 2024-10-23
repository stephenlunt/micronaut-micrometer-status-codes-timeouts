package com.example

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.common.SingleRootFileSource
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@MicronautTest(rebuildContext = true)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CustomStatusCodeTest {

    companion object {
        private val wiremockStub = WireMockServer(
            WireMockConfiguration().port(8086)
                .fileSource(SingleRootFileSource("src/test/resources/wiremock")),
        )

        @BeforeAll
        @JvmStatic
        fun setUpTest() {
            wiremockStub.start()
        }

        @AfterAll
        @JvmStatic
        fun tearDownTest() {
            wiremockStub.stop()
        }
    }

    @Test
    @Property(name = "micronaut.metrics.binders.web.enabled", value = "true")
    fun `a custom status code results in a read timeout exception with web metrics enabled`(spec: RequestSpecification) {
        val response = spec
            .given()
            .get("/custom-status-code/520")
            .then()
            .statusCode(500)
            .extract().body().jsonPath()

        println(response.prettify())
    }

    @Test
    @Property(name = "micronaut.metrics.binders.web.enabled", value = "false")
    fun `a custom status code results does not cause a read timeout exception with web metrics disabled`(spec: RequestSpecification) {
        val response = spec
            .given()
            .get("/custom-status-code/520")
            .then()
            .statusCode(500)
            .extract().body().jsonPath()

        println(response.prettify())
    }
}
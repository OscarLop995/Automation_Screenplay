package com.automation.performance;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import java.time.Duration;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;

public class SearchProductLoadTest extends Simulation {

    // Configuración HTTP
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://automationexercise.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/x-www-form-urlencoded")
            .userAgentHeader("Gatling Load Test");

    // Lista de productos para buscar
    String[] searchTerms = {"top", "tshirt", "jean", "dress", "shirt"};
    Random random = new Random();

    // Feeder para términos de búsqueda
    Iterator<Map<String, Object>> searchFeeder = new Iterator<Map<String, Object>>() {
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Map<String, Object> next() {
            return Map.of("searchTerm", searchTerms[random.nextInt(searchTerms.length)]);
        }
    };

    // Escenario de búsqueda
    ScenarioBuilder searchScenario = scenario("Search Product Load Test")
            .feed(searchFeeder)
            .exec(
                    http("Search Product - #{searchTerm}")
                            .post("/api/searchProduct")
                            .formParam("search_product", "#{searchTerm}")
                            .check(status().is(200))
                            .check(jsonPath("$.responseCode").is("200"))
                            .check(jsonPath("$.products").exists())
            )
            .pause(Duration.ofSeconds(1), Duration.ofSeconds(3));

    // Configuración de carga
    {
        setUp(
                searchScenario.injectOpen(
                        rampUsers(50).during(Duration.ofMinutes(1))
                ).protocols(httpProtocol)
        ).maxDuration(Duration.ofMinutes(5))
                .assertions(
                        global().responseTime().max().lt(5000),
                        global().responseTime().mean().lt(2000),
                        global().successfulRequests().percent().gt(95.0),
                        global().requestsPerSec().gte(5.0)
                );
    }
}
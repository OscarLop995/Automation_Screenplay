package com.automation.performance;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import java.time.Duration;

public class LoginLoadTest extends Simulation {

    // Configuración HTTP
    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://automationexercise.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/x-www-form-urlencoded")
            .userAgentHeader("Gatling Load Test");

    // Escenario de Login
    ScenarioBuilder loginScenario = scenario("Login Load Test")
            .exec(
                    http("Verify Login - Valid User")
                            .post("/api/verifyLogin")
                            .formParam("email", "testuser@test.com")
                            .formParam("password", "Test123456")
                            .check(status().in(200, 404))
                            .check(jsonPath("$.responseCode").exists())
                            .check(jsonPath("$.message").exists())
            )
            .pause(Duration.ofSeconds(1), Duration.ofSeconds(3));

    // Configuración de carga
    {
        setUp(
                loginScenario.injectOpen(
                        rampUsers(50).during(Duration.ofMinutes(1)) // 50 usuarios en 1 minuto
                ).protocols(httpProtocol)
        ).maxDuration(Duration.ofMinutes(5)) // Duración total: 5 minutos
                .assertions(
                        global().responseTime().max().lt(5000),
                        global().successfulRequests().percent().gt(95.0)
                );
    }
}
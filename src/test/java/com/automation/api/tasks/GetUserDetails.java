package com.automation.api.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class GetUserDetails implements Task {

    private final String email;

    public GetUserDetails(String email) {
        this.email = email;
    }

    public static GetUserDetails byEmail(String email) {
        return instrumented(GetUserDetails.class, email);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource("/api/getUserDetailByEmail")
                        .with(request -> request
                                .queryParam("email", email)
                        )
        );
    }
}
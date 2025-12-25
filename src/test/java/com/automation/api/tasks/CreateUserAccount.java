package com.automation.api.tasks;

import com.automation.api.models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import io.restassured.http.ContentType;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CreateUserAccount implements Task {

    private final User user;

    public CreateUserAccount(User user) {
        this.user = user;
    }

    public static CreateUserAccount with(User user) {
        return instrumented(CreateUserAccount.class, user);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("/api/createAccount")
                        .with(request -> request
                                .contentType(ContentType.URLENC)
                                .formParam("name", user.getName())
                                .formParam("email", user.getEmail())
                                .formParam("password", user.getPassword())
                                .formParam("title", user.getTitle())
                                .formParam("birth_date", user.getBirthDate())
                                .formParam("birth_month", user.getBirthMonth())
                                .formParam("birth_year", user.getBirthYear())
                                .formParam("firstname", user.getFirstname())
                                .formParam("lastname", user.getLastname())
                                .formParam("company", user.getCompany())
                                .formParam("address1", user.getAddress1())
                                .formParam("address2", user.getAddress2())
                                .formParam("country", user.getCountry())
                                .formParam("zipcode", user.getZipcode())
                                .formParam("state", user.getState())
                                .formParam("city", user.getCity())
                                .formParam("mobile_number", user.getMobileNumber())
                        )
        );
    }
}
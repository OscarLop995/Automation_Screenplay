package com.automation.api.runners;

import com.automation.api.models.User;
import com.automation.api.questions.UserDetailsAreCorrect;
import com.automation.api.questions.UserWasCreated;
import com.automation.api.tasks.CreateUserAccount;
import com.automation.api.tasks.GetUserDetails;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static net.serenitybdd.screenplay.GivenWhenThen.*;
import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class UserApiTest {

    private static final String BASE_URL = "https://automationexercise.com";

    private Actor apiUser;
    private User testUser;

    @Before
    public void setUp() {
        apiUser = Actor.named("API Test User")
                .whoCan(CallAnApi.at(BASE_URL));

        testUser = User.withTestData();
    }

    @Test
    public void shouldCreateUserAndVerifyDetails() {
        givenThat(apiUser).wasAbleTo(
                CreateUserAccount.with(testUser)
        );

        then(apiUser).should(
                seeThat("El usuario fue creado exitosamente",
                        UserWasCreated.successfully(),
                        is(true)
                )
        );

        when(apiUser).attemptsTo(
                GetUserDetails.byEmail(testUser.getEmail())
        );

        then(apiUser).should(
                seeThat("Los detalles del usuario son correctos",
                        UserDetailsAreCorrect.forUser(testUser),
                        is(true)
                )
        );
    }
}
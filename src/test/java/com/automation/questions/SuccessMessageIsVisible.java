package com.automation.questions;

import com.automation.ui.ContactUsPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;

public class SuccessMessageIsVisible {

    public static Question<Boolean> displayed() {
        return Visibility.of(ContactUsPage.SUCCESS_MESSAGE)
                .asBoolean();
    }
}

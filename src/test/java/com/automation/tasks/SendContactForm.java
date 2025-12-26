package com.automation.tasks;

import com.automation.ui.ContactUsPage;
import com.automation.interactions.AcceptAlert;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;
import com.automation.utils.TestData;
import java.nio.file.Paths;

public class SendContactForm {

    public static Task withValidData() {
        return Task.where(
                "{0} completa y envía el formulario Contact Us",
                Click.on(ContactUsPage.CONTACT_US_MENU),
                Enter.theValue(TestData.NAME).into(ContactUsPage.NAME),
                Enter.theValue(TestData.EMAIL).into(ContactUsPage.EMAIL),
                Enter.theValue(TestData.SUBJECT).into(ContactUsPage.SUBJECT),
                Enter.theValue(TestData.MESSAGE).into(ContactUsPage.MESSAGE),
                Upload.theFile(
                        Paths.get("src/test/resources/testdata/evidence.txt")
                                .toAbsolutePath()
                ).to(ContactUsPage.UPLOAD_FILE),
                Click.on(ContactUsPage.SUBMIT),
                AcceptAlert.now()
        );
    }
}

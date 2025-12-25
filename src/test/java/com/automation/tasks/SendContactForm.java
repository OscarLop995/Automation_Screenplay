package com.automation.tasks;

import com.automation.ui.ContactUsPage;
import com.automation.interactions.AcceptAlert;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.*;

import java.nio.file.Paths;

public class SendContactForm {

    public static Task withValidData() {
        return Task.where(
                "{0} completa y envía el formulario Contact Us",
                Click.on(ContactUsPage.CONTACT_US_MENU),
                Enter.theValue("Oscar QA").into(ContactUsPage.NAME),
                Enter.theValue("oscar@test.com").into(ContactUsPage.EMAIL),
                Enter.theValue("Automation Test").into(ContactUsPage.SUBJECT),
                Enter.theValue("Mensaje automatizado de prueba").into(ContactUsPage.MESSAGE),
                Upload.theFile(
                        Paths.get("src/test/resources/testdata/evidence.txt")
                                .toAbsolutePath()
                ).to(ContactUsPage.UPLOAD_FILE),
                Click.on(ContactUsPage.SUBMIT),
                AcceptAlert.now()
        );
    }
}

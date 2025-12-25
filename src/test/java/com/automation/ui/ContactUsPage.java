package com.automation.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ContactUsPage {

    public static final Target CONTACT_US_MENU =
            Target.the("menú Contact Us")
                    .located(By.xpath("//a[@href='/contact_us']"));

    public static final Target NAME =
            Target.the("campo nombre")
                    .located(By.name("name"));

    public static final Target EMAIL =
            Target.the("campo email")
                    .located(By.name("email"));

    public static final Target SUBJECT =
            Target.the("campo subject")
                    .located(By.name("subject"));

    public static final Target MESSAGE =
            Target.the("campo mensaje")
                    .located(By.id("message"));

    public static final Target UPLOAD_FILE =
            Target.the("carga de archivo")
                    .located(By.name("upload_file"));

    public static final Target SUBMIT =
            Target.the("botón submit")
                    .located(By.name("submit"));

    public static final Target SUCCESS_MESSAGE =
            Target.the("mensaje de éxito")
                    .located(By.xpath("//*[contains(text(),'Success!')]"));
}

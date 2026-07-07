package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;

public class PopupHandler {

    public static void closePopupIfPresent(WebDriver driver) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Object result = js.executeScript("" +
                    "let popup = document.querySelector('ct-web-popup-imageonly');" +
                    "if(popup && popup.shadowRoot){" +
                    "   let btn = popup.shadowRoot.querySelector('#close');" +
                    "   if(btn){" +
                    "       btn.click();" +
                    "       return true;" +
                    "   }" +
                    "}" +
                    "return false;"
            );
            if(Boolean.TRUE.equals(result)) {
                LoggerManager.info("Popup closed");
            }
        } catch (Exception e) {
            LoggerManager.info("Popup not present");
        }
    }
}
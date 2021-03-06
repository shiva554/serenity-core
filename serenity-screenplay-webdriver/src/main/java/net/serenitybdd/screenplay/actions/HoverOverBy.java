package net.serenitybdd.screenplay.actions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.serenitybdd.screenplay.targets.EnsureFieldVisible.ensureThat;

public class HoverOverBy extends Hover {

    private List<By> locators;
    protected WebElement resolveElementFor(Actor actor) {
        return resolveFor(actor);
    }

    @Override
    protected String getTarget() {
        return locators.toString();
    }

    public HoverOverBy(List<By> locators) {
        this.locators = locators;
    }

    protected WebElement resolveFor(Actor theActor) {
        WebElementFacade element = null;
        for(By locator : locators) {
            element = (element == null) ? BrowseTheWeb.as(theActor).find(locator) : element.find(locator);
        }
        ensureThat(theActor).canSee(element);
        return element;
    }
}

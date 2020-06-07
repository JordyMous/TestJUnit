package framework.elements;

import org.openqa.selenium.WebElement;

import framework.base.Element;
import framework.base.ImplementedBy;

import java.util.List;

/**
 * Select as custom WebElement
 */
@ImplementedBy(DropdownImpl.class)
public interface Dropdown extends Element {

    boolean isMultiple();

    void deselectByIndex(int index);

    void selectByValue(String value);

    WebElement getFirstSelectedOption();

    void selectByVisibleText(String text);

    void deselectByValue(String value);

    void deselectAll();

    List<WebElement> getAllSelectedOptions();

    List<WebElement> getOptions();

    void deselectByVisibleText(String text);

    void selectByIndex(int index);

}

package framework.elements;

import org.openqa.selenium.WebElement;

import framework.base.ElementImpl;

/**
 * Wrapper class with CheckBox functionalities
 */
public class CheckboxImpl extends ElementImpl implements Checkbox {

    public CheckboxImpl(WebElement element) {
        super(element);
    }

    public void toggle() {
        getWrappedElement().click();
    }
    
    public void check() {
        if (!isChecked()) {
            toggle();
        }
    }

    public void uncheck() {
        if (isChecked()) {
            toggle();
        }
    }

    public boolean isChecked() {
        return getWrappedElement().isSelected();
    }
}

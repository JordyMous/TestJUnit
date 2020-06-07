package framework.elements;

import org.openqa.selenium.WebElement;

import framework.base.ElementImpl;

/**
 * Wrapper class with TextInput functionalities
 */
public class TextInputImpl extends ElementImpl implements TextInput {
    
    public TextInputImpl(WebElement element) {
        super(element);
    }

    @Override
    public void clear() {
        getWrappedElement().clear();
    }

    @Override
    public void set(String text) {
    	getWrappedElement().clear();
    	getWrappedElement().sendKeys(text);
    }

    @Override
    public String getText() {
        return getWrappedElement().getAttribute("value");
    }
}

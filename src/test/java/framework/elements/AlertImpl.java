package framework.elements;

import org.openqa.selenium.WebElement;

import framework.base.ElementImpl;

/**
 * Wrapper class with Alert functionalities
 */
public class AlertImpl extends ElementImpl implements Alert {

	public AlertImpl(WebElement element) {
		super(element);
	}
	
    @Override
    public String getText() {
        return getWrappedElement().getAttribute("value");
    }
    
    @Override
    public String getInnerText() {
        return getAttribute("innerText");
    }
    
   
    
}

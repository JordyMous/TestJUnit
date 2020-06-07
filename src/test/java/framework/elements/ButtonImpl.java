package framework.elements;

import org.openqa.selenium.WebElement;

import framework.base.ElementImpl;

/**
 * Wrapper class with Button functionalities
 */
public class ButtonImpl extends ElementImpl implements Button {

	public ButtonImpl(WebElement element) {
		super(element);
	}
}

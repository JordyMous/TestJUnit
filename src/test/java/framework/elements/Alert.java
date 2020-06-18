package framework.elements;

import framework.base.Element;
import framework.base.ImplementedBy;

/**
 * Alert as custom WebElement
 */
@ImplementedBy(AlertImpl.class)
public interface Alert extends Element {

	String getInnerText();
	
	String getText();

  
}
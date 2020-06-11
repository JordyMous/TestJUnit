package framework.elements;

import framework.base.Element;
import framework.base.ImplementedBy;

/**
 * TextField as custom WebElement
 */
@ImplementedBy(TextInputImpl.class)
public interface TextInput extends Element {

    void set(String text);
}

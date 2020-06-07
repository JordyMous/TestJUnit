package framework.elements;

import framework.base.Element;
import framework.base.ImplementedBy;

/**
 * CheckBox as custom WebElement
 */
@ImplementedBy(CheckboxImpl.class)
public interface Checkbox extends Element {

    void toggle();

    void check();

    void uncheck();

    boolean isChecked();
}

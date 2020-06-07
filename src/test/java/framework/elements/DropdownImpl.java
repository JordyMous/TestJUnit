package framework.elements;

import java.util.List;
import java.util.StringTokenizer;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import framework.base.ElementImpl;

/**
 * Wrapper class with Dropdown functionalities
 */
public class DropdownImpl extends ElementImpl implements Dropdown {
	private final Select innerSelect;
    //private final org.openqa.selenium.support.ui.Select innerSelect;

    public DropdownImpl(WebElement element) {
        super(element);
        //this.innerSelect = new org.openqa.selenium.support.ui.Select(element);
        this.innerSelect = new Select(element);
    }

    public boolean isMultiple() {
        return innerSelect.isMultiple();
    }

    public void deselectByIndex(int index) {
        innerSelect.deselectByIndex(index);
    }

    public void selectByValue(String value) {
        String builder = ".//option[@value = " + escapeQuotes(value) +
                "]";
        List<WebElement> options =
                getWrappedElement().findElements(By.xpath(builder));

        State state = State.NOT_FOUND;
        for (WebElement option : options) {
            state = state.recognizeNewState(setSelected(option));
            if (!isMultiple() && state == State.SELECTED) {
                return;
            }
        }

        state.checkState("value: " + value);
    }

    public WebElement getFirstSelectedOption() {
        return innerSelect.getFirstSelectedOption();
    }

    public void selectByVisibleText(String text) {
        final WebElement element = getWrappedElement();
        // try to find the option via XPATH ...
        List<WebElement> options =
                element.findElements(By.xpath(".//option[normalize-space(.) = "
                        + escapeQuotes(text) + "]"));

        State state = State.NOT_FOUND;
        for (WebElement option : options) {
            state = state.recognizeNewState(setSelected(option));
            if (!isMultiple() && state == State.SELECTED) {                
                return;
            }
        }

        if (options.isEmpty() && text.contains(" ")) {
            String subStringWithoutSpace =
                    getLongestSubstringWithoutSpace(text);
            List<WebElement> candidates;
            if ("".equals(subStringWithoutSpace)) {
                // hmm, text is either empty or contains only spaces - get all
                // options ...
                candidates = element.findElements(By.tagName("option"));
            } else {
                // get candidates via XPATH ...
                candidates =
                        element.findElements(By.xpath(".//option[contains(., "
                                + escapeQuotes(subStringWithoutSpace) + ")]"));
            }
            for (WebElement option : candidates) {
                if (text.equals(option.getText())) {
                    state = state.recognizeNewState(setSelected(option));
                    if (!isMultiple()  && state == State.SELECTED) {
                        return;
                    }
                }
            }
        }

        state.checkState("text: " + text);
        
    }

    public void deselectByValue(String value) {
        innerSelect.deselectByValue(value);
    }

    public void deselectAll() {
        innerSelect.deselectAll();
    }

    public List<WebElement> getAllSelectedOptions() {
        return innerSelect.getAllSelectedOptions();
    }

    public List<WebElement> getOptions() {
        return innerSelect.getOptions();
    }

    public void deselectByVisibleText(String text) {
        innerSelect.deselectByVisibleText(text);
    }

    public void selectByIndex(int index) {
        String match = String.valueOf(index);

        State state = State.NOT_FOUND;
        for (WebElement option : getOptions()) {
            if (match.equals(option.getAttribute("index"))) {
                state = state.recognizeNewState(setSelected(option));
                if (!isMultiple()  && state == State.SELECTED) {
                    return;
                }
            }
        }
        state.checkState("index: " + index);
    }

    private String escapeQuotes(String toEscape) {
        // Convert strings with both quotes and ticks into: foo'"bar ->
        // concat("foo'", '"', "bar")
        if (toEscape.indexOf("\"") > -1 && toEscape.indexOf("'") > -1) {
            boolean quoteIsLast = false;
            if (toEscape.lastIndexOf("\"") == toEscape.length() - 1) {
                quoteIsLast = true;
            }
            String[] substrings = toEscape.split("\"");

            StringBuilder quoted = new StringBuilder("concat(");
            for (int i = 0; i < substrings.length; i++) {
                quoted.append("\"").append(substrings[i]).append("\"");
                quoted.append(((i == substrings.length - 1) ? (quoteIsLast ? ", '\"')"
                        : ")")
                        : ", '\"', "));
            }
            return quoted.toString();
        }

        // Escape string with just a quote into being single quoted:
        // f"oo -> 'f"oo'
        if (toEscape.indexOf("\"") > -1) {
            return String.format("'%s'", toEscape);
        }

        // Otherwise return the quoted string
        return String.format("\"%s\"", toEscape);
    }

    private State setSelected(WebElement option) {
        if (!option.isDisplayed()) {
            return State.NOT_VISIBLE;
        }
        if (!option.isEnabled()) {
            return State.DISABLED;
        }
        if (!option.isSelected()) {
            option.click();
        }
        return State.SELECTED;
    }

    private String getLongestSubstringWithoutSpace(String s) {
        String result = "";
        StringTokenizer st = new StringTokenizer(s, " ");
        while (st.hasMoreTokens()) {
            String t = st.nextToken();
            if (t.length() > result.length()) {
                result = t;
            }
        }
        return result;
    }

    private enum State {

        NOT_FOUND, NOT_VISIBLE, DISABLED, SELECTED;

        private State recognizeNewState(State newState) {
            if (this.ordinal() < newState.ordinal()) {
                return newState;
            } else {
                return this;
            }

        }

        private void checkState(String searchCriteria) {
            switch (this) {
            case NOT_VISIBLE:
                throw new ElementNotVisibleException(
                        "You may only interact with visible elements" + searchCriteria);
            case DISABLED:
                throw new InvalidElementStateException(
                        "You may only interact with enabled elements with " + searchCriteria);
            case NOT_FOUND:
                throw new NoSuchElementException(
                        "Cannot locate option with " + searchCriteria);
            case SELECTED:
                //DO_NOTHING;
            }
        }

    }
}

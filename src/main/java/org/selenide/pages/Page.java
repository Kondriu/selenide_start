package org.selenide.pages;

import org.selenide.core.BrowserActions;

public abstract class Page {

    public Page refreshPage() {
        BrowserActions.refreshPage();
        return this;
    }

    public Page back() {
        BrowserActions.back();
        return this;
    }

    public boolean isOpened() {
        return BrowserActions.checkCurrentPageAt(getClass());
    }

    public void waitPage(Class clazz) {
        BrowserActions.waitPage(clazz);
    }

    public void waitPage() {
        BrowserActions.waitPage(getClass());
    }

}

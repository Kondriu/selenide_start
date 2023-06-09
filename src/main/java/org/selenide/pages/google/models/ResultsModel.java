package org.selenide.pages.google.models;

import lombok.Data;
import lombok.Getter;

@Getter
public class ResultsModel {
    String title;
    String url;
    String description;

    public ResultsModel(String title, String url, String description) {
        this.title = title;
        this.url = url;
        this.description = description;
    }
}

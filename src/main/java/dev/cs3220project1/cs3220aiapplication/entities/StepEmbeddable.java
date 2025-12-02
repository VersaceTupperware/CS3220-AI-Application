package dev.cs3220project1.cs3220aiapplication.entities;

import dev.cs3220project1.cs3220aiapplication.models.Step;
import jakarta.persistence.*;

@Embeddable
public class StepEmbeddable {

    @Column(nullable = false, length = 2000)
    private String text;

    protected StepEmbeddable() {}

    public StepEmbeddable(Step step) {
        this.text = step.text();
    }

    public String getText() {
        return text;
    }
}

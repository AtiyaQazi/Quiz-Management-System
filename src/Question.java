import java.io.Serializable;

/**
 * Abstract base class representing a generic quiz question.
 * Demonstrates ABSTRACTION and ENCAPSULATION - the core fields are
 * private and only accessible through getters, while the actual
 * answer-checking logic is left to subclasses (POLYMORPHISM).
 */
public abstract class Question implements Serializable {
    private String questionText;
    private int marks;
    private String difficulty; // Easy, Medium, Hard

    public Question(String questionText, int marks, String difficulty) {
        this.questionText = questionText;
        this.marks = marks;
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public int getMarks() {
        return marks;
    }

    public String getDifficulty() {
        return difficulty;
    }

    // Each subclass decides how an answer is validated
    public abstract boolean checkAnswer(String userAnswer);

    // Each subclass reports its own type
    public abstract String getQuestionType();

    // Each subclass provides the choices shown to the user
    public abstract String[] getOptions();

    @Override
    public String toString() {
        return "[" + getQuestionType() + " | " + difficulty + " | " + marks + " marks] " + questionText;
    }
}

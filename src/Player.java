/**
 * Represents a person taking the quiz.
 * Pure ENCAPSULATION example: private fields, controlled access
 * through public methods only.
 */
public class Player {
    private String name;
    private int totalScore;
    private int correctAnswers;
    private int wrongAnswers;

    public Player(String name) {
        this.name = name;
        this.totalScore = 0;
        this.correctAnswers = 0;
        this.wrongAnswers = 0;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getWrongAnswers() {
        return wrongAnswers;
    }

    public void addScore(int marks) {
        this.totalScore += marks;
    }

    public void incrementCorrect() {
        this.correctAnswers++;
    }

    public void incrementWrong() {
        this.wrongAnswers++;
    }
}

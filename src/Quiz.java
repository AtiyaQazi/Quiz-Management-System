import java.util.List;

/**
 * Represents one running quiz attempt for a specific player.
 * Coordinates question flow and delegates scoring to the Player.
 */
public class Quiz {
    private List<Question> quizQuestions;
    private int currentIndex;
    private Player player;

    public Quiz(Player player, List<Question> questions) {
        this.player = player;
        this.quizQuestions = questions;
        this.currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < quizQuestions.size();
    }

    public Question getCurrentQuestion() {
        return quizQuestions.get(currentIndex);
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getTotalQuestions() {
        return quizQuestions.size();
    }

    /** Submits the user's answer for the current question, updates score, and advances. */
    public boolean submitAnswer(String answer) {
        Question q = getCurrentQuestion();
        boolean correct = q.checkAnswer(answer);
        if (correct) {
            player.addScore(q.getMarks());
            player.incrementCorrect();
        } else {
            player.incrementWrong();
        }
        currentIndex++;
        return correct;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMaxPossibleScore() {
        int total = 0;
        for (Question q : quizQuestions) total += q.getMarks();
        return total;
    }
}

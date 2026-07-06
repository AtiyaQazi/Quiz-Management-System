/**
 * True/False Question.
 * A second concrete subclass of Question, showing how the same
 * abstract contract (checkAnswer, getOptions, getQuestionType) can
 * be implemented completely differently (POLYMORPHISM).
 */
public class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(String questionText, boolean correctAnswer, int marks, String difficulty) {
        super(questionText, marks, difficulty);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        if (userAnswer == null) return false;
        return String.valueOf(correctAnswer).equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public String getQuestionType() {
        return "True/False";
    }

    @Override
    public String[] getOptions() {
        return new String[]{"True", "False"};
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }
}

/**
 * Multiple Choice Question.
 * Demonstrates INHERITANCE (extends Question) and POLYMORPHISM
 * (overrides checkAnswer/getQuestionType/getOptions differently
 * from other Question subtypes).
 */
public class MCQQuestion extends Question {
    private String[] options;
    private String correctAnswer;

    public MCQQuestion(String questionText, String[] options, String correctAnswer, int marks, String difficulty) {
        super(questionText, marks, difficulty);
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean checkAnswer(String userAnswer) {
        return userAnswer != null && correctAnswer.equalsIgnoreCase(userAnswer.trim());
    }

    @Override
    public String getQuestionType() {
        return "Multiple Choice";
    }

    @Override
    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

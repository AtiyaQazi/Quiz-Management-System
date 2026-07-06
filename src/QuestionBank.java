import java.util.*;

/**
 * Manages the master collection of all quiz questions.
 * Provides add/remove/fetch operations used by both the Admin
 * (question management) side and the Quiz engine (random question
 * selection) side of the application.
 */
public class QuestionBank {
    private List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        loadDefaultQuestions();
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public boolean removeQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
            return true;
        }
        return false;
    }

    public List<Question> getAllQuestions() {
        return questions;
    }

    public int size() {
        return questions.size();
    }

    /** Returns up to `count` random questions from the bank (shuffled). */
    public List<Question> getRandomQuestions(int count) {
        List<Question> copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        int limit = Math.min(count, copy.size());
        return new ArrayList<>(copy.subList(0, limit));
    }

    private void loadDefaultQuestions() {
        questions.add(new MCQQuestion(
                "What is the capital of Pakistan?",
                new String[]{"Karachi", "Lahore", "Islamabad", "Peshawar"},
                "Islamabad", 5, "Easy"));

        questions.add(new MCQQuestion(
                "Which language runs natively in a web browser?",
                new String[]{"Java", "C", "Python", "JavaScript"},
                "JavaScript", 5, "Easy"));

        questions.add(new TrueFalseQuestion(
                "Java is a platform-independent language.", true, 5, "Easy"));

        questions.add(new MCQQuestion(
                "Which keyword is used to inherit a class in Java?",
                new String[]{"implements", "extends", "inherits", "super"},
                "extends", 10, "Medium"));

        questions.add(new TrueFalseQuestion(
                "An abstract class can be instantiated directly using 'new'.", false, 10, "Medium"));

        questions.add(new MCQQuestion(
                "Which OOP principle allows one interface to be used for different data types?",
                new String[]{"Encapsulation", "Polymorphism", "Abstraction", "Inheritance"},
                "Polymorphism", 10, "Medium"));

        questions.add(new MCQQuestion(
                "Which collection class does NOT allow duplicate elements?",
                new String[]{"ArrayList", "LinkedList", "HashSet", "Stack"},
                "HashSet", 15, "Hard"));

        questions.add(new TrueFalseQuestion(
                "In Java, a class can extend multiple classes at once.", false, 10, "Medium"));
    }
}

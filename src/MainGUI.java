import javax.swing.*;
import java.awt.*;

/**
 * Entry point of the application.
 * Uses CardLayout to switch between Welcome, Admin, Quiz, and
 * Result screens inside a single JFrame.
 */
public class MainGUI extends JFrame {
    private CardLayout cardLayout;
    private JPanel container;

    private QuestionBank questionBank;
    private ScoreBoard scoreBoard;

    public static final String WELCOME = "WELCOME";
    public static final String ADMIN = "ADMIN";
    public static final String QUIZ = "QUIZ";
    public static final String RESULT = "RESULT";
    public static final String LEADERBOARD = "LEADERBOARD";

    private WelcomePanel welcomePanel;
    private AdminPanel adminPanel;
    private QuizPanel quizPanel;
    private ResultPanel resultPanel;
    private LeaderboardPanel leaderboardPanel;

    public MainGUI() {
        super("Quiz Management System");
        questionBank = new QuestionBank();
        scoreBoard = new ScoreBoard();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(720, 520);
        setMinimumSize(new Dimension(640, 480));
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        welcomePanel = new WelcomePanel(this);
        adminPanel = new AdminPanel(this, questionBank);
        quizPanel = new QuizPanel(this, questionBank);
        resultPanel = new ResultPanel(this);
        leaderboardPanel = new LeaderboardPanel(this, scoreBoard);

        container.add(welcomePanel, WELCOME);
        container.add(adminPanel, ADMIN);
        container.add(quizPanel, QUIZ);
        container.add(resultPanel, RESULT);
        container.add(leaderboardPanel, LEADERBOARD);

        add(container);
        showScreen(WELCOME);
    }

    public void showScreen(String name) {
        if (name.equals(ADMIN)) adminPanel.refreshList();
        if (name.equals(QUIZ)) quizPanel.promptStartNewQuiz();
        if (name.equals(LEADERBOARD)) leaderboardPanel.refreshList();
        cardLayout.show(container, name);
    }

    public void goToResult(Quiz finishedQuiz) {
        resultPanel.showResult(finishedQuiz);
        scoreBoard.addRecord(finishedQuiz.getPlayer());
        showScreen(RESULT);
    }

    public static void main(String[] args) {
        // Use the system look and feel for a native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        SwingUtilities.invokeLater(() -> {
            MainGUI app = new MainGUI();
            app.setVisible(true);
        });
    }
}

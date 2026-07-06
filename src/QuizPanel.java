import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Screen where the player actually takes the quiz: one question
 * at a time, with radio-button style options and a Next button.
 */
public class QuizPanel extends JPanel {
    private MainGUI app;
    private QuestionBank questionBank;
    private Quiz currentQuiz;

    private JLabel questionNumberLabel;
    private JLabel questionTextLabel;
    private JLabel scoreLabel;
    private ButtonGroup optionsGroup;
    private JPanel optionsPanel;
    private JButton nextButton;
    private JProgressBar progressBar;

    public QuizPanel(MainGUI app, QuestionBank bank) {
        this.app = app;
        this.questionBank = bank;
        setLayout(new BorderLayout(15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JPanel topPanel = new JPanel(new BorderLayout());
        questionNumberLabel = new JLabel("Question 1 of N");
        questionNumberLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        topPanel.add(questionNumberLabel, BorderLayout.WEST);
        topPanel.add(scoreLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);

        questionTextLabel = new JLabel("Question text here", SwingConstants.LEFT);
        questionTextLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(progressBar);
        centerPanel.add(Box.createVerticalStrut(15));
        centerPanel.add(questionTextLabel);
        centerPanel.add(Box.createVerticalStrut(15));

        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        centerPanel.add(optionsPanel);

        add(centerPanel, BorderLayout.CENTER);

        nextButton = new JButton("Submit & Next");
        nextButton.setFont(new Font("SansSerif", Font.BOLD, 15));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> handleNext());
    }

    /** Called every time the user navigates into the quiz screen. */
    public void promptStartNewQuiz() {
        String name = JOptionPane.showInputDialog(this, "Enter your name to start the quiz:", "Player");
        if (name == null || name.trim().isEmpty()) {
            name = "Guest";
        }

        if (questionBank.size() == 0) {
            JOptionPane.showMessageDialog(this, "No questions available. Please add questions first.");
            app.showScreen(MainGUI.WELCOME);
            return;
        }

        int count = Math.min(5, questionBank.size());
        List<Question> selected = questionBank.getRandomQuestions(count);
        Player player = new Player(name.trim());
        currentQuiz = new Quiz(player, selected);
        loadQuestion();
    }

    private void loadQuestion() {
        if (!currentQuiz.hasNext()) {
            app.goToResult(currentQuiz);
            return;
        }

        Question q = currentQuiz.getCurrentQuestion();
        int qNum = currentQuiz.getCurrentIndex() + 1;
        int total = currentQuiz.getTotalQuestions();

        questionNumberLabel.setText("Question " + qNum + " of " + total + "  (" + q.getDifficulty() + ", " + q.getMarks() + " marks)");
        scoreLabel.setText("Score: " + currentQuiz.getPlayer().getTotalScore());
        progressBar.setValue((int) (((qNum - 1) / (double) total) * 100));
        questionTextLabel.setText("<html><body style='width: 400px'>" + q.getQuestionText() + "</body></html>");

        optionsPanel.removeAll();
        optionsGroup = new ButtonGroup();
        for (String option : q.getOptions()) {
            JRadioButton rb = new JRadioButton(option);
            rb.setFont(new Font("SansSerif", Font.PLAIN, 15));
            rb.setActionCommand(option);
            optionsGroup.add(rb);
            optionsPanel.add(rb);
            optionsPanel.add(Box.createVerticalStrut(6));
        }

        revalidate();
        repaint();
    }

    private void handleNext() {
        if (currentQuiz == null) return;

        String selectedAnswer = null;
        for (java.util.Enumeration<javax.swing.AbstractButton> buttons = optionsGroup.getElements(); buttons.hasMoreElements(); ) {
            javax.swing.AbstractButton b = buttons.nextElement();
            if (b.isSelected()) {
                selectedAnswer = b.getActionCommand();
                break;
            }
        }

        if (selectedAnswer == null) {
            JOptionPane.showMessageDialog(this, "Please select an answer before continuing.");
            return;
        }

        currentQuiz.submitAnswer(selectedAnswer);
        loadQuestion();
    }
}

import javax.swing.*;
import java.awt.*;

/** Displays the final score and stats after a quiz attempt finishes. */
public class ResultPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JLabel statsLabel;
    private JLabel percentLabel;

    public ResultPanel(MainGUI app) {
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 250, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel header = new JLabel("Quiz Completed!");
        header.setFont(new Font("SansSerif", Font.BOLD, 26));
        gbc.gridy = 0;
        add(header, gbc);

        nameLabel = new JLabel();
        nameLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 1;
        add(nameLabel, gbc);

        scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        gbc.gridy = 2;
        add(scoreLabel, gbc);

        percentLabel = new JLabel();
        percentLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        gbc.gridy = 3;
        add(percentLabel, gbc);

        statsLabel = new JLabel();
        statsLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        gbc.gridy = 4;
        add(statsLabel, gbc);

        JButton retryBtn = new JButton("Take Another Quiz");
        JButton menuBtn = new JButton("Back to Main Menu");
        retryBtn.setPreferredSize(new Dimension(220, 38));
        menuBtn.setPreferredSize(new Dimension(220, 38));

        gbc.gridy = 5;
        add(retryBtn, gbc);
        gbc.gridy = 6;
        add(menuBtn, gbc);

        retryBtn.addActionListener(e -> app.showScreen(MainGUI.QUIZ));
        menuBtn.addActionListener(e -> app.showScreen(MainGUI.WELCOME));
    }

    public void showResult(Quiz quiz) {
        Player p = quiz.getPlayer();
        int max = quiz.getMaxPossibleScore();
        double percent = max == 0 ? 0 : (p.getTotalScore() * 100.0 / max);

        nameLabel.setText("Player: " + p.getName());
        scoreLabel.setText("Final Score: " + p.getTotalScore() + " / " + max);
        percentLabel.setText(String.format("Percentage: %.1f%%", percent));
        statsLabel.setText("Correct Answers: " + p.getCorrectAnswers() + "    |    Wrong Answers: " + p.getWrongAnswers());
    }
}

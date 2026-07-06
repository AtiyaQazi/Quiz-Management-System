import javax.swing.*;
import java.awt.*;

/** Landing screen with navigation buttons. */
public class WelcomePanel extends JPanel {
    public WelcomePanel(MainGUI app) {
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = 1;
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.gridx = 0;

        JLabel title = new JLabel("Quiz Management System");
        title.setFont(new Font("SansSerif", Font.BOLD, 28));
        gbc.gridy = 0;
        add(title, gbc);

        JLabel subtitle = new JLabel("Java OOP based GUI Quiz Application");
        subtitle.setFont(new Font("SansSerif", Font.ITALIC, 14));
        gbc.gridy = 1;
        add(subtitle, gbc);

        JButton takeQuizBtn = new JButton("Take Quiz");
        JButton adminBtn = new JButton("Manage Questions (Admin)");
        JButton leaderboardBtn = new JButton("View Leaderboard");
        JButton exitBtn = new JButton("Exit");

        for (JButton b : new JButton[]{takeQuizBtn, adminBtn, leaderboardBtn, exitBtn}) {
            b.setFont(new Font("SansSerif", Font.PLAIN, 16));
            b.setPreferredSize(new Dimension(280, 42));
        }

        gbc.gridy = 2;
        add(takeQuizBtn, gbc);
        gbc.gridy = 3;
        add(adminBtn, gbc);
        gbc.gridy = 4;
        add(leaderboardBtn, gbc);
        gbc.gridy = 5;
        add(exitBtn, gbc);

        takeQuizBtn.addActionListener(e -> app.showScreen(MainGUI.QUIZ));
        adminBtn.addActionListener(e -> app.showScreen(MainGUI.ADMIN));
        leaderboardBtn.addActionListener(e -> app.showScreen(MainGUI.LEADERBOARD));
        exitBtn.addActionListener(e -> System.exit(0));
    }
}

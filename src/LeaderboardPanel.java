import javax.swing.*;
import java.awt.*;
import java.util.List;

/** Shows the top scoring players recorded in the ScoreBoard. */
public class LeaderboardPanel extends JPanel {
    private ScoreBoard scoreBoard;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public LeaderboardPanel(MainGUI app, ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("Leaderboard - Top Scores", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(header, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setFont(new Font("Monospaced", Font.PLAIN, 15));
        add(new JScrollPane(list), BorderLayout.CENTER);

        JButton backBtn = new JButton("Back to Menu");
        JPanel bottom = new JPanel(new FlowLayout());
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> app.showScreen(MainGUI.WELCOME));
    }

    public void refreshList() {
        listModel.clear();
        List<Player> top = scoreBoard.getTopScores(10);
        if (top.isEmpty()) {
            listModel.addElement("No quiz attempts recorded yet.");
            return;
        }
        int rank = 1;
        for (Player p : top) {
            listModel.addElement(String.format("%2d. %-15s Score: %-4d Correct: %-3d Wrong: %-3d",
                    rank++, p.getName(), p.getTotalScore(), p.getCorrectAnswers(), p.getWrongAnswers()));
        }
    }
}

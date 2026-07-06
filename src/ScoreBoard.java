import java.io.*;
import java.util.*;

/**
 * Keeps a persistent leaderboard of all quiz attempts, saved to a
 * simple text file (scoreboard.txt) so results survive between runs.
 */
public class ScoreBoard {
    private List<Player> records;
    private static final String FILE_NAME = "scoreboard.txt";

    public ScoreBoard() {
        records = new ArrayList<>();
        loadScores();
    }

    public void addRecord(Player p) {
        records.add(p);
        appendScoreToFile(p);
    }

    public List<Player> getTopScores(int n) {
        List<Player> sorted = new ArrayList<>(records);
        sorted.sort((a, b) -> b.getTotalScore() - a.getTotalScore());
        int limit = Math.min(n, sorted.size());
        return new ArrayList<>(sorted.subList(0, limit));
    }

    public List<Player> getAllRecords() {
        return records;
    }

    private void loadScores() {
        File f = new File(FILE_NAME);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    Player p = new Player(parts[0]);
                    p.addScore(Integer.parseInt(parts[1]));
                    for (int i = 0; i < Integer.parseInt(parts[2]); i++) p.incrementCorrect();
                    for (int i = 0; i < Integer.parseInt(parts[3]); i++) p.incrementWrong();
                    records.add(p);
                }
            }
        } catch (IOException e) {
            // If the file is unreadable, just start with an empty scoreboard.
        }
    }

    private void appendScoreToFile(Player p) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(p.getName() + "," + p.getTotalScore() + "," + p.getCorrectAnswers() + "," + p.getWrongAnswers());
            bw.newLine();
        } catch (IOException e) {
            // Non-fatal: scoreboard just won't persist this run.
        }
    }
}

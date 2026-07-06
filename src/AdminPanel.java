import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Admin screen: lets the user manage the question bank
 * (add new MCQ / True-False questions, delete existing ones).
 */
public class AdminPanel extends JPanel {
    private QuestionBank questionBank;
    private DefaultListModel<String> listModel;
    private JList<String> questionList;

    public AdminPanel(MainGUI app, QuestionBank bank) {
        this.questionBank = bank;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel header = new JLabel("Manage Questions", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(header, BorderLayout.NORTH);

        listModel = new DefaultListModel<>();
        questionList = new JList<>(listModel);
        questionList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        add(new JScrollPane(questionList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addMcqBtn = new JButton("Add MCQ");
        JButton addTfBtn = new JButton("Add True/False");
        JButton deleteBtn = new JButton("Delete Selected");
        JButton backBtn = new JButton("Back to Menu");

        buttonPanel.add(addMcqBtn);
        buttonPanel.add(addTfBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(backBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        addMcqBtn.addActionListener(e -> addMCQDialog());
        addTfBtn.addActionListener(e -> addTrueFalseDialog());
        deleteBtn.addActionListener(e -> deleteSelected());
        backBtn.addActionListener(e -> app.showScreen(MainGUI.WELCOME));

        refreshList();
    }

    public void refreshList() {
        listModel.clear();
        List<Question> all = questionBank.getAllQuestions();
        for (int i = 0; i < all.size(); i++) {
            listModel.addElement((i + 1) + ". " + all.get(i).toString());
        }
    }

    private void addMCQDialog() {
        JTextField questionField = new JTextField();
        JTextField opt1 = new JTextField();
        JTextField opt2 = new JTextField();
        JTextField opt3 = new JTextField();
        JTextField opt4 = new JTextField();
        JTextField correctField = new JTextField();
        JTextField marksField = new JTextField("10");
        JComboBox<String> difficultyBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});

        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.add(new JLabel("Question:"));
        panel.add(questionField);
        panel.add(new JLabel("Option 1:"));
        panel.add(opt1);
        panel.add(new JLabel("Option 2:"));
        panel.add(opt2);
        panel.add(new JLabel("Option 3:"));
        panel.add(opt3);
        panel.add(new JLabel("Option 4:"));
        panel.add(opt4);
        panel.add(new JLabel("Correct Answer (must match one option exactly):"));
        panel.add(correctField);
        panel.add(new JLabel("Marks:"));
        panel.add(marksField);
        panel.add(new JLabel("Difficulty:"));
        panel.add(difficultyBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add Multiple Choice Question",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String qText = questionField.getText().trim();
                String[] options = {opt1.getText().trim(), opt2.getText().trim(), opt3.getText().trim(), opt4.getText().trim()};
                String correct = correctField.getText().trim();
                int marks = Integer.parseInt(marksField.getText().trim());
                String difficulty = (String) difficultyBox.getSelectedItem();

                if (qText.isEmpty() || correct.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Question text and correct answer cannot be empty.");
                    return;
                }

                questionBank.addQuestion(new MCQQuestion(qText, options, correct, marks, difficulty));
                refreshList();
                JOptionPane.showMessageDialog(this, "Question added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Marks must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addTrueFalseDialog() {
        JTextField questionField = new JTextField();
        JComboBox<String> answerBox = new JComboBox<>(new String[]{"True", "False"});
        JTextField marksField = new JTextField("10");
        JComboBox<String> difficultyBox = new JComboBox<>(new String[]{"Easy", "Medium", "Hard"});

        JPanel panel = new JPanel(new GridLayout(0, 1, 4, 4));
        panel.add(new JLabel("Statement:"));
        panel.add(questionField);
        panel.add(new JLabel("Correct Answer:"));
        panel.add(answerBox);
        panel.add(new JLabel("Marks:"));
        panel.add(marksField);
        panel.add(new JLabel("Difficulty:"));
        panel.add(difficultyBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add True/False Question",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String qText = questionField.getText().trim();
                boolean correct = answerBox.getSelectedItem().equals("True");
                int marks = Integer.parseInt(marksField.getText().trim());
                String difficulty = (String) difficultyBox.getSelectedItem();

                if (qText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Statement cannot be empty.");
                    return;
                }

                questionBank.addQuestion(new TrueFalseQuestion(qText, correct, marks, difficulty));
                refreshList();
                JOptionPane.showMessageDialog(this, "Question added successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Marks must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteSelected() {
        int index = questionList.getSelectedIndex();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Please select a question to delete.");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this, "Delete this question?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            questionBank.removeQuestion(index);
            refreshList();
        }
    }
}

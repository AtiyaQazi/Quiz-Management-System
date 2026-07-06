# Quiz Management System (Java OOP + Swing GUI)

A complete GUI-based Quiz Management System built in core Java, demonstrating
all four major Object-Oriented Programming principles.

## Features
- Take a randomized 5-question quiz (Multiple Choice + True/False)
- Live scoring system with progress bar and running score
- Admin panel to add/delete questions (question management)
- Persistent leaderboard saved to `scoreboard.txt`
- Clean multi-screen GUI using Java Swing (CardLayout navigation)

## OOP Principles Used
| Principle      | Where it's used |
|-----------------|-----------------|
| **Abstraction**  | `Question` is an abstract class defining a contract (`checkAnswer`, `getOptions`, `getQuestionType`) without implementation. |
| **Inheritance**  | `MCQQuestion` and `TrueFalseQuestion` both extend `Question`. |
| **Polymorphism** | `Quiz` and `QuestionBank` work with the abstract `Question` type; the actual `checkAnswer()` logic that runs depends on the real subtype at runtime. |
| **Encapsulation**| All classes (`Player`, `Question`, `Quiz`, etc.) keep fields `private` and expose controlled access via getters/setters. |

## Project Structure
```
QuizManagementSystem/
├── src/
│   ├── Question.java          # abstract base class
│   ├── MCQQuestion.java       # multiple-choice question type
│   ├── TrueFalseQuestion.java # true/false question type
│   ├── QuestionBank.java      # stores & manages all questions
│   ├── Player.java            # tracks a player's score/stats
│   ├── Quiz.java              # runs a single quiz attempt
│   ├── ScoreBoard.java        # persistent leaderboard (file I/O)
│   ├── MainGUI.java           # app entry point / screen navigation
│   ├── WelcomePanel.java      # home screen
│   ├── AdminPanel.java        # add/delete questions UI
│   ├── QuizPanel.java         # question-answering UI
│   ├── ResultPanel.java       # final score screen
│   └── LeaderboardPanel.java  # top scores screen
├── QuizManagementSystem.jar   # pre-built runnable jar
└── README.md
```

## How to Run

### Option 1: Run the pre-built JAR (easiest)
Make sure you have **Java 17+** installed, then:
```bash
java -jar QuizManagementSystem.jar
```

### Option 2: Compile from source yourself
```bash
cd src
javac -d ../bin *.java
cd ../bin
java MainGUI
```

## How to Use
1. Launch the app — you'll see the main menu.
2. Click **"Manage Questions (Admin)"** to add your own MCQ or True/False
   questions (a few sample Java/OOP questions are pre-loaded).
3. Click **"Take Quiz"**, enter your name, and answer 5 randomly selected
   questions.
4. See your final score and percentage on the results screen.
5. Check the **"View Leaderboard"** screen to see top scores across all
   past attempts (saved automatically to `scoreboard.txt`).

## Possible Extensions (great for a viva/project defense)
- Add a timer per question
- Add categories/subjects for questions
- Add a `Serializable` save/load for the full question bank (not just scores)
- Add an interface `Scorable` to formalize the scoring contract
- Add login system with separate Admin/Student roles

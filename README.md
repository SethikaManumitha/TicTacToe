# 🕹️ Tic Tac Toe - Java Console Game with AI

This is a simple **Java console-based and GUI-based Tic Tac Toe** game where a human player competes against an AI. The AI uses the **Minimax algorithm** to make optimal decisions, providing a challenging gameplay experience.

---

## ✨ Features

- ✅ Human vs AI gameplay
- 🤖 AI powered by the **Minimax algorithm**
- 📦 Pure Java (No external libraries required)
- 🎲 Randomized first move (Human or AI)
- 📋 Console and GUI options for playing

---

## 🎮 How to Play - Console

- Run the game.
- The board positions are numbered
- You'll be prompted to enter a number (0–8) to place your move (`O`).
- The AI (`X`) will automatically make its move.
- The game ends with either a **win**, **loss**, or **draw**.

---

## 🖱 How to Play - GUI

- Run the game.
- A window with 9 buttons would be shown
- Click on a button(`O`)
- The AI (`X`) will automatically make its move.
- The game ends with either a **win**, **loss**, or **draw**.
- Click to play again
  
---
## 🛠️ Installation & Running

1. Make sure Java is installed on your system. You can check with:
   ```bash
   java -version
   javac -version

2. Save the game code in a file named TicTacToe.java.
   ```bash
   javac TicTacToe.java
   javac TicTacToeGUI.java
   javac -version
3. Run the game:
   ```bash
   java TicTacToe
   java TicTacToeGUI.java

---

## 🛠️ How the AI Works

The AI uses the **Minimax algorithm**, a recursive decision-making algorithm used in turn-based games. 
It evaluates all possible future game states to choose the move that maximizes its chances of winning (or minimizes loss).

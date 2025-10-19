# 💣 Minesweeper – Game Board Creation 💣

## 🎯 Description

This module handles **creating and filling the Minesweeper game board**.
It allows the player or program to initialize a board of any size and populate it with mines using **one of three methods**, depending on the chosen mode.

---

### 🐇 Rabbit Mode

* The player **fills the board manually** with mines.
* Each mine (`*`) is placed in the positions selected by the player.

**Example – width 5:**

```
    . . * . .
    * . . * .
    . * . . *
    . . * . .
```

---

### 🐢 Turtle Mode

* The program **automatically fills the board** with mines (`*`) based on **specified column numbers**.
* Columns may differ **between rows**.

**Example – width 10, different columns per row:**

```
    . * . . * . * . . .
    * . * . . * . . * .
    . * . * . . * . . *
    * . * . . * . * . .
```

---

### 🦥 Sloth Mode

* The most flexible automatic filling method.
* Allows specifying **individual columns or entire column ranges**, which can differ **between rows**.

**Example – width 35, different ranges per row:**

```
    * * * . . * * * . . . . * * * . . . . . . . . . . . . . .
    * * . . * * . . . . * * * . . . . . . . . . . . . . . .
    * * * . . * * * . . . . * * . . . . . . . . . . . . . .
    * * . * . * * . . . . * * * . . . . . . . . . . . . . .
```

**Example – width 18, different ranges per row:**

```
    . * * . . * . . . * * . . . . . . .
    * * . . . * . . * * . . . . . . . .
    . * . * . * . . . * * . . . . . . .
    * . * . . * . . . * . * . . . . . .
```

---

### 📝 Availability Guide 

| Mode           | Filling Method                      | Available Width   |
| -------------- | ----------------------------------- | ----------------- |
| 🐇 Rabbit Mode | Player fills the board manually     | Any width         |
| 🐢 Turtle Mode | Automatic filling by column numbers | Width > 5 && ≤ 20 |
| 🦥 Sloth Mode  | Automatic filling by columns/ranges | Width > 20        |

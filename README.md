# 💣 Minesweeper – Game Board Creation 💣

## 🎯 Description

This module handles **creating and filling the Minesweeper game board**.
It allows the player or program to initialize a board of any size and populate it with mines using **one of three methods**, depending on the chosen mode.
**Digits represent the number of mines in the neighboring cells**, while `*` denotes a mine and `0` denotes a cell with no neighboring mines.

---

### 🐇 Rabbit Mode

* The player **fills the board manually** with mines.
* Each mine (`*`) is placed in the positions selected by the player.

**Example – width 5:**

```
12*21
*33*2
2*33*
24*31
1**20
```

---

### 🐢 Turtle Mode

* The program **automatically fills the board** with mines (`*`) based on **specified column numbers**.
* Columns may differ **between rows**.

**Example – width 10, different columns per row:**

```
222***2**2
**45*533*2
3**4**2332
2435*43**1
*3*3*33*31
*3133*2110
1213*42111
13*5**22*1
*5*6*6*211
*4*4**2100
```

---

### 🦥 Sloth Mode

* The most flexible automatic filling method.
* Allows specifying **individual columns or entire column ranges**, which can differ **between rows**.

**Example – width 35, different ranges per row:**

```
0002*32********100000012***11110000
0002*4*5*****5443333334*65311*10000
23344*35**5433************311111110
*****43***21*22333333334***10001*10
2334**34654444321011100234221112321
2334432*********201*2101*101*22*3*2
*****1123333334*2012*10111012*213*2
```

---

### 📝 Availability Guide

| Mode           | Filling Method                      | Available Width   |
| -------------- | ----------------------------------- | ----------------- |
| 🐇 Rabbit Mode | Player fills the board manually     | Any width         |
| 🐢 Turtle Mode | Automatic filling by column numbers | Width > 5 && ≤ 20 |
| 🦥 Sloth Mode  | Automatic filling by columns/ranges | Width > 20        |

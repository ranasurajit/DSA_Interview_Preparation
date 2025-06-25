# 🐸 Frog Jump with K Distances

This repository contains the solution to the dynamic programming problem **"Frog Jump with K Distances"** from [Take U Forward DSA Sheet](https://takeuforward.org/plus/dsa/problems/frog-jump-with-k-distances).

## 🧩 Problem Statement

A frog is trying to reach the last stair (`n-1`) from the 0th stair. Each stair has a height associated with it, given in the array `heights[]`.

The frog can jump to any of the next `k` stairs from its current stair `i`, i.e., `i+1`, `i+2`, ..., `i+k`. The cost of a jump from stair `i` to stair `j` is `abs(heights[i] - heights[j])`.

Your task is to find the **minimum total cost** for the frog to reach the last stair.

---

### 🧮 Example

**Input:**

```
heights = [10, 40, 50, 20]
k = 3
```

**Output:**

```
30
```

**Explanation:**

- Jump from stair 0 → 1 → 3  
  - Cost = |10 - 40| + |40 - 20| = 30

---

## ✅ Constraints

- `1 <= n <= 10^5`  
- `0 <= heights[i] <= 10^9`  
- `1 <= k <= 100`

---

## 🔍 Approaches

### 1. Recursion (TLE)
- Explores all `k` jumps recursively.
- **Time Complexity:** `O(k^n)` (Exponential)
- **Space Complexity:** `O(n)` (recursion stack)

### 2. Recursion + Memoization (Top-Down DP)
- Stores results of subproblems to avoid recomputation.
- **Time Complexity:** `O(n * k)`
- **Space Complexity:** `O(n)` + recursion stack

### 3. Tabulation (Bottom-Up DP)
- Iteratively computes the minimum cost using a DP array.
- **Time Complexity:** `O(n * k)`
- **Space Complexity:** `O(n)`

### 4. Space Optimization
- Reduce the DP array size depending on k.
- **Time Complexity:** `O(n * k)`
- **Space Complexity:** `O(k)`

---

## 📁 Files

| File | Description |
|------|-------------|
| `FrogJumpKRecursive.java` | Pure recursive solution (TLE) |
| `FrogJumpKMemo.java` | Top-down memoization solution |
| `FrogJumpKTabulation.java` | Bottom-up tabulated DP solution |
| `FrogJumpKOptimized.java` | Space-optimized DP solution |

---

## 🚀 How to Run

```bash
javac FrogJumpKMemo.java
java FrogJumpKMemo
```

---

## 🙌 Credits

This problem is part of the [Striver's A2Z DSA Sheet](https://takeuforward.org/).  
Thanks to [Take U Forward](https://takeuforward.org/) for the awesome content!

---

## 📜 License

This project is open source and available under the [MIT License](LICENSE).

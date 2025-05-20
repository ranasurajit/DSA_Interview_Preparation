# Row of a Matrix with Maximum Ones

## Problem Statement

Given a binary matrix `ARR` of size `N x M`, where each element is either `0` or `1`, determine the index of the row that contains the maximum number of `1`s. If multiple rows have the same maximum number of `1`s, return the index of the first such row.

## Input Format

- The first line contains two integers `N` and `M`, representing the number of rows and columns in the matrix, respectively.
- The next `N` lines each contain `M` integers (either `0` or `1`), representing the elements of each row in the matrix.

## Output Format

- Output a single integer: the index (0-based) of the row with the maximum number of `1`s.

## Constraints

- 1 ≤ N, M ≤ 10^3
- Each element of the matrix is either 0 or 1.

## Sample Input

```
3 3
1 1 1
0 0 1
0 0 0
```

## Sample Output

```
0
```

## Explanation

In the given matrix:
- Row 0 has three `1`s.
- Row 1 has one `1`.
- Row 2 has zero `1`s.

Therefore, row 0 has the maximum number of `1`s.

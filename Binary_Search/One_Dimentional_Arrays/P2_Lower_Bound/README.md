# Implement Lower Bound

### Problem Statement
You are given an array 'arr' sorted in non-decreasing order and a number 'x'. You must return the index of the lower bound of 'x'.

### Note:
- For a sorted array 'arr', 'lower_bound' of a number 'x' is defined as the smallest index 'idx' such that the value 'arr[idx]' is not less than 'x'.If all numbers are smaller than 'x', then 'n' should be the 'lower_bound' of 'x', where 'n' is the size of array.

- Try to do this in O(log(n)).

### Input

    6
    1 2 2 3 3 5
    2

### Output

    1

### Constraints
- 1 <= ‘n’ <= 10^5
- 0 <= ‘arr[i]’ <= 10^5
- 1 <= ‘x’ <= 10^5

### Examples

#### Example 1
- **Input**: ‘arr’ = [1, 2, 2, 3] and 'x' = 0
- **Output**: 0
- **Explanation**: Index '0' is the smallest index such that 'arr[0]' is not less than 'x'.

# Implement Upper Bound

### Problem Statement
You are given a sorted array ‘arr’ containing ‘n’ integers and an integer ‘x’.Implement the ‘upper bound’ function to find the index of the upper bound of 'x' in the array.

### Note:
- The upper bound in a sorted array is the index of the first value that is greater than a given value. 
- If the greater value does not exist then the answer is 'n', Where 'n' is the size of the array.
- Try to write a solution that runs in log(n) time complexity.


### Input

    5 7
    1 4 7 8 10

### Output

    3

### Constraints
- 1 <= ‘n’ <= 10^5
- 1 <= ‘x’ <= 10^9
- 1 <= ‘arr[i]’ <= 10^9

#### Time Limit: 
    1 sec

### Examples

#### Example 1
- **Input**: ‘arr’ = {2,4,6,7} and ‘x’ = 5,
- **Output**: 2
- **Explanation**: The upper bound of 5 is 6 in the given array, which is at index 2 (0-indexed).

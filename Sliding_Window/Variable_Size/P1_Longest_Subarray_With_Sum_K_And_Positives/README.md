
# Longest Subarray with Sum K

## Problem Statement

Given an array of integers and an integer `K`, determine the length of the longest contiguous subarray that sums to `K`.

## Example

### Input:
- Array: `[1, 2, 3, 4, 5]`
- `K`: `9`

### Output:
- Length: `2`

### Explanation:
The subarray `[4, 5]` sums to `9` and has a length of `2`.

## Approach

To solve this problem efficiently, we can use a hashmap to store the cumulative sum up to each index. By iterating through the array and maintaining the cumulative sum, we can check if there exists a subarray that sums to `K`. The steps are as follows:

### Steps:
1. **Initialize Variables:**
   - A hashmap to store the cumulative sum and its corresponding index.
   - A variable to keep track of the cumulative sum.
   - A variable to store the maximum length of the subarray found.

2. **Iterate Through the Array:**
   - For each element in the array, add it to the cumulative sum.
   - Check if the cumulative sum equals `K`. If it does, update the maximum length.
   - If the cumulative sum minus `K` exists in the hashmap, it means there is a subarray that sums to `K`. Update the maximum length accordingly.
   - If the cumulative sum is not present in the hashmap, add it with its index.

## Algorithm

```python
def longest_subarray_with_sum_k(arr, K):
    # Initialize hashmap to store cumulative sum and its index
    cum_sum_map = {}
    # Initialize variables
    cum_sum = 0
    max_len = 0
    # Iterate through the array
    for i in range(len(arr)):
        # Update cumulative sum
        cum_sum += arr[i]
        # Check if cumulative sum equals K
        if cum_sum == K:
            max_len = i + 1
        # Check if (cum_sum - K) exists in the hashmap
        if (cum_sum - K) in cum_sum_map:
            max_len = max(max_len, i - cum_sum_map[cum_sum - K])
        # Add cumulative sum to hashmap if not present
        if cum_sum not in cum_sum_map:
            cum_sum_map[cum_sum] = i
    return max_len
```

## Complexity

- **Time Complexity:** `O(n)`
  - The algorithm involves a single pass through the array with constant-time operations for each element.
- **Space Complexity:** `O(n)`
  - Due to the storage requirements of the hashmap.

## References

For more in-depth explanation and additional examples, check the following resources:
- [GeeksforGeeks: Longest sub-array having sum K](https://www.geeksforgeeks.org/longest-sub-array-sum-k/)
- [LeetCode: Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k/)

## Visual Explanation

For a visual walkthrough, refer to this [YouTube video](https://www.youtube.com/watch?v=frf7qxiN2qU).

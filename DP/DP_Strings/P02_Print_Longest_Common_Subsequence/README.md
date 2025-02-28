# Print Longest Common Subsequence

## Problem Statement

You are given two strings ‘s1’ and ‘s2’.

Return the longest common subsequence of these strings.

If there’s no such string, return an empty string. If there are multiple possible answers, return any such string.


**Note:**
**Longest common subsequence** of string ‘s1’ and ‘s2’ is the longest subsequence of ‘s1’ that is also a subsequence of ‘s2’. A ‘subsequence’ of ‘s1’ is a string that can be formed by deleting one or more (possibly zero) characters from ‘s1’.

## Example

### Example 1
**Input:**
```
5 6
ababa
cbbcad
```
**Output:**
```
bba
```
**Explanation:**
```
“bba” is only possible longest subsequence present in both s1 = “ababa” and s2 = “cbbcad”. '1' is printed if the returned string is equal to "bba".
```

### Example 2
**Input:**
```
3 3
xyz
abc
```
**Output:**
```

```
**Explanation:**
```
There’s no subsequence of ‘s1’ that is also present in ‘s2’. Thus an empty string is returned and '1' is printed.
```

**Expected Time Complexity:**
```
Try to solve this in O(n*m). Where ‘n’ is the length of ‘s1’ and ‘m’ is the length of ‘s2’. 
```

**Constraints:**
```
1 <= n, m <= 10^3

Time Limit: 1 sec
```

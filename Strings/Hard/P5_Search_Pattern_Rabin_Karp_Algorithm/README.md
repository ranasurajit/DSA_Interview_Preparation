### Search Pattern (Rabin-Karp Algorithm)

Difficulty: **Hard**Accuracy: **34.53%**Submissions: **88K+**Points: **8**Average Time: **20m**

Given two strings:

*   A **`text`** string in which you want to search.
    
*   A `**pattern**` string that you are looking for within the **`text`**.
    

Return all **positions** (0-based indexing) where the `**pattern**` occurs as a substring in the **`text`**.

**Note:** If the pattern does not occur in text, return an empty list.

**Examples:**

**Input**: text = "geeksforgeeks", pattern = "geek"  
**Output:** \[0, 8\]
**Explanation**: The string "geek" occurs twice in text, one starts at index 0 and the other at index 8.

**Input:** text = "aabaacaadaabaaba", pattern = "aaba"
**Output:** \[0, 9, 12\]
**Explanation**:   
![](https://media.geeksforgeeks.org/img-practice/prod/addEditProblem/897091/Web/Other/blobid0_1753437427.jpg) 

**Constraints:**  
1 ≤ text.size() ≤ 106  
1 ≤ pattern.size() ≤ text.size()  
Both the strings consist of lowercase English alphabets.

Try more examples

**Expected Complexities**![Dropdown Icon](https://media.geeksforgeeks.org/img-practice/prod/courses/3454/Web/Content/Vector_1743491619.png)

Time Complexity: O(n + m)

Auxiliary Space: O(n + m)


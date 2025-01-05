370\. Range Addition
====================

Assume you have an array of length **_n_** initialized with all **0**'s and are given **_k_** update operations.

Each operation is represented as a triplet: **\[startIndex, endIndex, inc\]** which increments each element of subarray **A\[startIndex ... endIndex\]** (startIndex and endIndex inclusive) with **inc**.

Return the modified array after all **_k_** operations were executed.

---

**Example:**

**Input:** length = 5, updates = \[\[1,3,2\],\[2,4,3\],\[0,2,-2\]\]
**Output:** \[-2,0,3,5,3\]

---

**Explanation:**

Initial state:
\[0,0,0,0,0\]

After applying operation \[1,3,2\]:
\[0,2,2,2,0\]

After applying operation \[2,4,3\]:
\[0,2,5,5,3\]

After applying operation \[0,2,-2\]:
\[-2,0,3,5,3\]

---

### Difficulty:

Medium

### Lock:

Prime

### Company:

[Google](https://leetcode.ca/tags/#Google)

### Problem Solution

[370-Range-Addition](https://leetcode.ca/2016-12-04-370-Range-Addition)

Print Longest Common Subsequence
================================

Problem statement
-----------------

You are given two strings **_‘s1’_** and **_‘s2’_**.

  

Return the longest common subsequence of these strings.

  

If there’s no such string, return an empty string. If there are multiple possible answers, return any such string.

  

**Note:**

    Longest common subsequence of string ‘s1’ and ‘s2’ is the longest subsequence of ‘s1’ that is also a subsequence of ‘s2’. A ‘subsequence’ of ‘s1’ is a string that can be formed by deleting one or more (possibly zero) characters from ‘s1’.
    

  

**Example:**

    Input: ‘s1’  = “abcab”, ‘s2’ = “cbab”
    
    Output: “bab”
    
    Explanation:
    “bab” is one valid longest subsequence present in both strings ‘s1’ , ‘s2’.
    

  

Detailed explanation ( Input/output format, Notes, Images )

**Input format:**

    The first line of input contains two integers ‘n’ and ‘m’, length of ‘s1’ and length of ‘s2’.
    
    The second line of the input contains the string ‘s1’.
    
    The third line of the input contains the string ‘s2’.
    

  

**Output Format:**

    Return a string which is the longest common subsequence of ‘s1’ and ‘s2’. If there’s no such string return an empty string.
    

  

**Note**

    You don’t need to print anything, it has already been taken care of, just complete the given function. ‘1’ will be printed if the given subsequence is present in both the strings and length of the string is equal to the longest common subsequence and ‘0’ otherwise.
    

**Sample Input 1:**

    5 6
    ababa
    cbbcad
    

  

**Expected Answer:**

    "bba"
    

  

**Output on console:**

    1
    

  

**Explanation of sample output 1:**

    “bba” is only possible longest subsequence present in both s1 = “ababa” and s2 = “cbbcad”. '1' is printed if the returned string is equal to "bba". 
    

  

**Sample Input 2:**

    3 3
    xyz
    abc
    

  

**Expected Answer:**

    ""
    

  

**Output on console:**

    1
    

  

**Explanation of sample output 2:**

    There’s no subsequence of ‘s1’ that is also present in ‘s2’. Thus an empty string is returned and '1' is printed.
    

  

**Expected Time Complexity:**

    Try to solve this in O(n*m). Where ‘n’ is the length of ‘s1’ and ‘m’ is the length of ‘s2’. 
    

  

**Constraints:**

    1 <= n, m <= 10^3
    
    Time Limit: 1 sec


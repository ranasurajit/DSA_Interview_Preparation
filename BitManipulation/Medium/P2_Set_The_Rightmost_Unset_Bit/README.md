Set The Rightmost Unset Bit
===========================
Problem statement
-----------------

Send feedback

The problem is to find the rightmost bit of a non-negative number _**'N'**_ that is currently unset (i.e., has a value of 0) in its binary representation and set it to 1.

  

Return the number after setting the rightmost unset bit of 'N'. If there are no unset bits in N's binary representation, then the number should remain unchanged.

  

**Example:**

    N = 5
    Output: 7
    Explanation: The binary representation of 5 is 101. After setting the rightmost unset bit it becomes 111 which is 7.
    

Detailed explanation ( Input/output format, Notes, Images )

**Input format:**

    The first line of each test case contains an integer 'N’.
    

**Output Format:**

    The output contains an integer, which is the number after setting the rightmost unset bit.
    

**Note:-**

    You don’t need to print anything. Just implement the given function.
    

##### Sample Input 1:

    10
    

##### Sample Output 1:

    11
    

##### Explanation Of Sample Input 1:

    N=10
    The binary representation of 10 is 1010. After setting the rightmost unset bit it becomes 1011 which is 11.
    

##### Sample Input 2:

    7
    

##### Sample Output 2:

    7
    

##### Explanation Of Sample Input 2:

    N=7
    The binary representation of 7 is 111. As there is no unset bit it remains the same.
    

##### Constraints:

    1 <= N <= 10^9
    Time Limit: 1 sec


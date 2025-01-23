### Problem statement
You are given an integer N. Your task is to return a 2-D ArrayList containing the pascal’s triangle till the row N.

A Pascal's triangle is a triangular array constructed by summing adjacent elements in preceding rows. Pascal's triangle contains the values of the binomial coefficient. For example in the figure below.

<p><img src="https://files.codingninjas.in/image1-7089.png" alt="" style="cursor: zoom-in;"></p>

**For example:**

Given integer N= 4 then you have to print.

1  
1 1 
1 2 1 
1 3 3 1

Here for the third row, you will see that the second element is the summation of the above two-row elements i.e. 2=1+1, and similarly for row three 3 = 1+2 and 3 = 1+2.

**Constraints:**

```
1 <= T <= 40
1 <= N <= 50
```

**Sample Input:**
```
3
1
2
3
```

**Sample Output:**
```
1
1 
1 1 
1 
1 1 
1 2 1 
```

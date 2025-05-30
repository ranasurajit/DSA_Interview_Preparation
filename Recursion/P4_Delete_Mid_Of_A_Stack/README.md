<h2><a href="https://www.geeksforgeeks.org/problems/delete-middle-element-of-a-stack/1?page=1&category=Recursion&sortBy=submissions">Delete Mid of a Stack</a></h2><h3>Difficulty Level : Difficulty: Easy</h3><hr><div class="problems_problem_content__Xm_eO"><p><span style="font-size: 18px;">Given a stack, delete the&nbsp;<strong>middle element </strong>of the stack&nbsp;without using any additional data structure.<br><strong>Middle element:- </strong>floor((size_of_stack+1)/2) (1-based indexing) from bottom of the stack.</span></p>
<p><strong style="font-size: 18px;">Note</strong><span style="font-size: 18px;">: The output shown by the compiler is the stack from top to bottom.</span><br>&nbsp;<br><strong><span style="font-size: 18px;">Example 1:</span></strong></p>
<pre><span style="font-size: 18px;"><strong>Input</strong>: 
Stack = {10, 20, 30, 40, 50}
<strong>Output</strong>:
ModifiedStack = {10, 20, 40, 50}
<strong>Explanation</strong>:
If you print the stack the bottom-most element will be 10 and the top-most element will be 50. Middle element will be element at index 3 from bottom, which is 30. Deleting 30, stack will look like {10 20 40 50}.</span>
</pre>
<p><strong><span style="font-size: 18px;">Example 2:</span></strong></p>
<pre><span style="font-size: 18px;"><strong>Input</strong>: 
Stack = {10 20 30 40}
<strong>Output</strong>:
ModifiedStack = {10 30 40}
<strong>Explanation</strong>:<br></span><span style="font-size: 14pt;">If you print the stack the bottom-most element will be 10 and the top-most element will be 40. Middle element will be element at index 2 from bottom, which is 20. Deleting 20, stack will look like {10 30 40}.</span></pre>
<p><strong><span style="font-size: 18px;">Your Task:</span></strong><br><span style="font-size: 18px;">You don't need to read input or print anything.&nbsp;Complete the function <strong>deleteMid() </strong>which takes the stack and its size as input parameters and modifies the stack in-place.</span></p>
<p><span style="font-size: 18px;"><strong>Expected Time Complexity: </strong>O(N)<br><strong>Expected Auxiliary Space:&nbsp;</strong>O(N)</span></p>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>2 ≤ size of stack ≤ 10<sup>5</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Amazon</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Recursion</code>&nbsp;<code>Stack</code>&nbsp;<code>STL</code>&nbsp;<code>Data Structures</code>&nbsp;<code>Algorithms</code>&nbsp;

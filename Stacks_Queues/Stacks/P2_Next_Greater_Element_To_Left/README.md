<h2><a href="https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1">Next Greater Element To Left</a></h2><h3>Difficulty Level : Difficulty: Medium</h3><hr><div class="problems_problem_content__Xm_eO"><p><span style="font-size: 18px;">Given an array <strong>arr[ ]</strong> of integers, the task is to find the next greater element for each element of the array in opposite order of their appearance in the array. Next greater element of an element in the array is the nearest element on the left which is greater than the current element.<br>If there does not exist next greater of current element, then next greater element for current element is -1. For example, next greater of the first element is always -1.</span></p>
<p><span style="font-size: 18px;"><strong>Examples<br></strong></span></p>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [1, 3, 2, 4]
<strong>Output</strong>: [-1, -1, 3, -1]
<strong>Explanation</strong>: The next larger element to 1 is -1, 3 is -1, 2 is 3 and for 4, since it doesn't exist, it is -1.
</span></pre>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [6, 8, 0, 1, 3]
<strong>Output</strong>: [-1, -1, 8, 8, 8]
<strong>Explanation</strong>: The next larger element to -1 is 8, for 8 there is no larger elements hence it is -1, for 0 it is 8 , for 1 it is 8 and then for 3 it is 8.</span></pre>
<pre><span style="font-size: 18px;"><strong>Input</strong>: arr[] = [10, 20, 30, 50]
<strong>Output</strong>: [-1, 50, 40, 30]
</span></pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ arr.size() ≤ 10<sup>6</sup><br>0 ≤ arr[i] ≤ 10<sup>9</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Flipkart</code>&nbsp;<code>Amazon</code>&nbsp;<code>Microsoft</code>&nbsp;<code>MakeMyTrip</code>&nbsp;<code>Adobe</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Stack</code>&nbsp;<code>Data Structures</code>&nbsp;

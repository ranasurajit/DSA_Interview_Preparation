<h2><a href="https://www.geeksforgeeks.org/problems/alien-dictionary/1">Alien Dictionary</a></h2><h3>Difficulty Level : Difficulty: Hard</h3><hr><div class="problems_problem_content__Xm_eO"><p><span style="font-size: 18px;">Given a sorted dictionary of an alien language having N words and&nbsp;k starting alphabets of standard dictionary. Find the order of characters in the alien language.<br><strong>Note:</strong>&nbsp;Many orders may be&nbsp;possible for a particular test case, thus&nbsp;you may return any valid order and&nbsp;output will be 1 if the order of string returned by the function is correct else 0 denoting incorrect string returned.</span><br>&nbsp;</p>
<p><span style="font-size: 18px;"><strong>Examples :</strong></span></p>
<pre><span style="font-size: 18px;"><strong>Input:  </strong>N = 5, K = 4, dict = {"baa","abcd","abca","cab","cad"</span><span style="font-size: 18px;">}
<strong>Output: </strong>1
<strong>Explanation: </strong>Here order of characters is 'b', 'd', 'a', 'c' Note that words are sorted and in the given language "baa" comes before "abcd", therefore 'b' is before 'a' in output.
Similarly we can find other orders.</span></pre>
<pre><span style="font-size: 18px;"><strong>Input: </strong>N = 3, K = 3, dict = {"caa","aaa","aab"}
<strong>Output: </strong>1
<strong>Explanation: </strong>Here order of characters is 'c', 'a', 'b' Note that words are sorted and in the given language "caa" comes before "aaa", therefore 'c' is before 'a' in output.
Similarly we can find other orders.
</span></pre>
<p><span style="font-size: 18px;"><strong>Expected Time Complexity:&nbsp;</strong>O(N * |S| + K) , where |S| denotes maximum length.<br><strong>Expected Space Compelxity:&nbsp;</strong>O(K)</span></p>
<p><br><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ N≤ 10<sup>4</sup><br>1 ≤ K&nbsp;≤ 26<br>1 ≤ Length of words&nbsp;≤ 50</span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Flipkart</code>&nbsp;<code>Amazon</code>&nbsp;<code>Microsoft</code>&nbsp;<code>OYO Rooms</code>&nbsp;<code>Walmart</code>&nbsp;<code>Google</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Strings</code>&nbsp;<code>Sorting</code>&nbsp;<code>Graph</code>&nbsp;<code>Data Structures</code>&nbsp;<code>Algorithms</code>&nbsp;

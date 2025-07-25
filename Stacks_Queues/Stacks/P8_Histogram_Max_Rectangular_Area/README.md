<h2><a href="https://www.geeksforgeeks.org/problems/maximum-rectangular-area-in-a-histogram-1587115620/1">Histogram Max Rectangular Area</a></h2><h3>Difficulty Level : Difficulty: Hard</h3><hr><div class="problems_problem_content__Xm_eO"><p><span style="font-size: 18px;">You are given a <strong>histogram</strong> represented by an array <strong>arr</strong>, where each element of the array denotes the <strong>height</strong> of the bars in the histogram. All bars have the same <strong>width of 1 unit</strong>. </span></p>
<p><span style="font-size: 18px;">Your task is to find the <strong>largest</strong> rectangular area possible in the given histogram, where the rectangle can be formed using a number of contiguous bars.</span></p>
<p><span style="font-size: 18px;"><strong>Examples:</strong></span></p>
<pre><span style="font-size: 18px;"><strong>Input: </strong>arr[] = <span style="font-family: 'andale mono', monospace;">[</span></span><span style="color: #273239; font-family: 'andale mono', monospace; font-size: 18px; letter-spacing: 0.162px; text-wrap: wrap; background-color: #f9f9f9;">60, 20, 50, 40, 10, 50, 60]<br></span><span style="font-size: 18px;"> <img src="https://media.geeksforgeeks.org/wp-content/uploads/20240924161857/Largest-Rectangular-Area-in-a-Histogram.webp" alt="Largest-Rectangular-Area-in-a-Histogram" width="437" height="210">
<strong>Output: </strong>100<strong>
Explanation: </strong></span><span style="font-size: 18px;">We get the maximum by picking bars highlighted above in green (50, and 60). The area is computed (smallest height) * (no. of the picked bars) = 50 * 2 = 100.</span><img src="http://d1hyf4ir1gqw6c.cloudfront.net/wp-content/uploads/histogram1.png" alt="">
</pre>
<pre><span style="font-size: 18px;"><span style="font-size: 18px;"><span style="font-size: 18px;"><strong>Input: </strong>arr[] = [3, 5, 1, 7, 5, 9]<strong>
Output: </strong>15<strong>
Explanation:  </strong>We get the maximum by picking bars 7, 5 and 9. The area is computed (smallest height) * (no. of the picked bars) = 5 * 3 = 15.</span></span></span></pre>
<pre><span style="font-size: 18px;"><strong>Input: </strong>arr[] = [3]</span><span style="font-size: 18px;">
<strong>Output: </strong>3<strong>
Explanation: </strong>In this example the largest area would be 3 of height 3 and width 1.</span></pre>
<p><span style="font-size: 18px;"><strong>Constraints:</strong><br>1 ≤ arr.size() ≤ 10<sup>5</sup><br>0 ≤ arr[i] ≤ 10<sup>4</sup></span></p></div><p><span style=font-size:18px><strong>Company Tags : </strong><br><code>Microsoft</code>&nbsp;<code>Google</code>&nbsp;<br><p><span style=font-size:18px><strong>Topic Tags : </strong><br><code>Stack</code>&nbsp;<code>Data Structures</code>&nbsp;

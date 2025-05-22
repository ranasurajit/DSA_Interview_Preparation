package Binary_Search.Search_On_Answers.P6_Capacity_To_Ship_Packages_Within_D_Days;

public class Capacity_To_Ship_Packages_Within_D_Days {
    public static void main(String[] args) {
        Capacity_To_Ship_Packages_Within_D_Days solution = new Capacity_To_Ship_Packages_Within_D_Days();

        int[] weights = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int days = 5;

        int minCapacity = solution.shipWithinDays(weights, days);
        System.out.println(minCapacity);
    }

    /**
     * Approach : Using Binary Search on Answers Approach
     * 
     * TC: O(N + N x (Sum(weights) - Max(weights))) ~ O(N x (Sum(weights) -
     * Max(weights)))
     * SC: O(1)
     * 
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        long low = Integer.MIN_VALUE;
        long high = 0L;
        for (int weigh : weights) { // TC: O(N)
            low = Math.max(low, (long) weigh);
            high += (long) weigh;
        }
        // Applying Binary Search
        long minCapacity = high;
        while (low <= high) { // TC: O(Sum(weights) - Max(weights))
            long mid = low + (high - low) / 2;
            // calculate days needed when capacity of ship = mid
            long daysNeeded = getNumberOfDaysToShipAllPackages(weights, mid); // TC: O(N)
            if (daysNeeded <= days) {
                minCapacity = Math.min(minCapacity, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return (int) minCapacity;
    }

    /**
     * Using Simulation Approach
     * 
     * TC: O(N)
     * SC: O(1)
     * 
     * @param weights
     * @param capacity
     * @return
     */
    private long getNumberOfDaysToShipAllPackages(int[] weights, long capacity) {
        long days = 1L;
        long sum = 0L;
        for (int weigh : weights) { // TC: O(N)
            if (sum + (long) weigh <= capacity) {
                sum += (long) weigh;
            } else {
                days++;
                sum = (long) weigh;
            }
        }
        return days;
    }
}

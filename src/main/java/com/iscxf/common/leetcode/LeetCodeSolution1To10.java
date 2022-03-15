package com.iscxf.common.leetcode;
import java.util.*;

public class LeetCodeSolution1To10 {

    public static int[] twoSum(int[] nums, int target) {
//        int[] result = new int[2];;
//        for(int i=0; i < nums.length-1;i++){
//            if(nums[i] > target){
//                continue;
//            }
//            for(int k=i+1;k<nums.length; k++){
//                if(nums[k] > target){
//                    continue;
//                }
//                if(nums[i] + nums[k] == target){
//                    result[0] = i;
//                    result[1] = k;
//                    break;
//                }
//            }
//        }
//        return result;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<nums.length; i++){
            if (map.containsKey(target - nums[i])){
                return new int[] {map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[] {0};
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
        ListNode(List<Integer> list){
            ListNode ln = new ListNode();
            for (Integer i : list) {
                ln.val = i;
                ln.next = new ListNode();
            }
        }

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode();
        int addNextNum = 0;
        int sum = l1.val + l2.val + addNextNum;
        int addNext = 0;
        if (sum >= 10){
            sum = sum - 10;
            addNext = 1;
        }
        l3.val = sum;
        add(l1.next, l2.next, l3, addNext);
        return l3;
    }

    public static void add(ListNode l1, ListNode l2, ListNode l3, int addNextNum){
        if (null == l1 && null == l2 && addNextNum!=1){
            return;
        }
        ListNode node = new ListNode();
        int l1Val = 0;
        int l2Val = 0;
        if (null != l1){
            l1Val = l1.val;
        }
        if (null != l2){
            l2Val = l2.val;
        }
        int sum = l1Val + l2Val + addNextNum;
        int addNext = 0;
        if (sum >= 10){
            sum = sum - 10;
            addNext = 1;
        }
        node.val = sum;
        l3.next = node;
        ListNode l1Next = new ListNode(0);
        ListNode l2Next = new ListNode(0);
        if (null != l1 && null != l1.next){
            l1Next = l1.next;
        }
        if (null != l2 && null != l2.next){
            l2Next = l2.next;
        }
        if ((null != l1 && null != l1.next) || (null != l2 && null != l2.next)){
            add(l1Next, l2Next, l3.next, addNext);
        }else if (addNext != 0){
            l3.next.next = new ListNode(1);
        }
    }

    public static int lengthOfLongestSubstring(String s) {
        Set count = new HashSet<>();
        int length = 0;
        for (int i=0;i<s.length();i++){
            boolean isAdd = count.add(s.charAt(i));
            if (count.size() > length){
                length = count.size();
            }
            if (!isAdd){
                System.out.println(count);
                i = i - count.size() + 1;
                count.clear();
                count.add(s.charAt(i));
            }
        }
        return length;
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组nums1 和nums2。请你找出并返回这两个正序数组的 中位数 。
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * 解法1：归并排序后找中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums3 = new int[nums1.length + nums2.length];
        int i=0, m=0, n=0;
        while (m < nums1.length || n < nums2.length){
            if (m == nums1.length){
                nums3[i] = nums2[n];
                n++;
                i++;
                continue;
            }
            if (n == nums2.length){
                nums3[i] = nums1[m];
                m++;
                i++;
                continue;
            }
            if (nums1[m] < nums2[n]){
                nums3[i] = nums1[m];
                m++;
            }else {
                nums3[i] = nums2[n];
                n++;
            }
            i++;
        }
        System.out.println(Arrays.toString(nums3));
        if (nums3.length % 2 != 0){
            return nums3[nums3.length / 2];
        }else {
            return (nums3[(nums3.length + 1) / 2] + nums3[(nums3.length - 1) / 2]) / 2.0;
        }
    }

    /**
     * 解法2：查找第k个最小的数
     * @return
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int sum = nums1.length + nums2.length;
        int k = sum / 2;
        int before = 0;
        int now = 0;
        int l1=0, l2=0;
        for (int i=0; i<= k; i++){
            before = now;
            if (l1 < nums1.length && (l2 >= nums1.length || nums1[l1] < nums2[l2])){
                now = nums1[l1];
                l1++;
            }else {
                now = nums2[l2];
                l2++;
            }
        }
//        while (i <= k ){
//            before = now;
////            aStart < m && (bStart >= n || A[aStart] < B[bStart])
////            if (l2 >= nums2.length || (l1 < nums1.length && nums1[l1] < nums2[l2])){
//            if (l1 < nums1.length && (l2 >= nums1.length || nums1[l1] < nums2[l2])){
//                now = nums1[l1];
//                l1++;
//            }else {
//                now = nums2[l2];
//                l2++;
//            }
//            i++;
//        }
        if (sum % 2 == 0){
            return (now + before)/2.0;
        }else {
            return now;
        }
    }

    /**
     * 解法3：二分法
     * @return
     */
    public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        return 0;
    }

    public static String longestPalindrome(String s) {
        int length = s.length();
        String result = "";
        for (int i=0; i<length; i++){
            int left = i;
            int right = i;
//            right = i;
            char now = s.charAt(i);
            int min = Math.min(i, length - i);
//             && left >= 0 && length >= right
            while (left > 0 && length > right){
                if (left == i && length > right+1 && s.charAt(left-1) == s.charAt(right+1)){
                    left = left - 1;
                    right = right + 1;
                }
                if (s.charAt(left) != s.charAt(right)) {
                    left = left +1;
                    right = right-1;
                    break;
                }
                if (left-1 < 0 || right+1 >= length) {
                    break;
                }
                left--;
                right++;
                min--;
            }
//            for (int k=1; k<min; k++){
//                right = i+1;
//                if (left > 0 && right < length && left == i && s.charAt(left-1) == s.charAt(right)){
//                    left = left - 1;
//                }
//                if (left <= 0 || right >= length-1 || s.charAt(left) != s.charAt(right)) {
//                   break;
//                }
//                left--;
//                right++;
//            }
            if (right - left >= result.length()){
                result = s.substring(left, right+1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        int[] nums1 = {};
//        int[] nums2 = {3};
//        System.out.println(findMedianSortedArrays2(nums1, nums2));

        System.out.println(longestPalindrome("cbbd"));
    }
}

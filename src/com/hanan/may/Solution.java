package com.hanan.may;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    public static void main(String s[]) {
       // System.out.println(new Solution().firstBadVersion(5));
        System.out.println(new Solution().findComplement(2));

    }
    public int findComplement(int num) {

        int bitlength = ((int)(Math.log(num)/Math.log(2)) + 1 );
        int bitMask = (1 << bitlength)-1 ;

        return num ^ bitMask;
    }
    public int numJewelsInStones(String J, String S) {
        int res = 0;
        Set<Character> unique = new HashSet<Character>();
        for (char c : J.toCharArray()) {
            unique.add(c);
        }
        for (int i =0 ; i <S.toCharArray().length ; i++){
            if (unique.contains(S.toCharArray()[i])){
                res ++;
            }
        }

        return res;
    }

    public int firstBadVersion(int n) {
        int res = -1;
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (new VersionControl().isBadVersion(mid)) {
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return res;
    }
}

class VersionControl {
    boolean isBadVersion(int version) {

        if (version == 4) {
            return true;
        }
        return false;
    }
}

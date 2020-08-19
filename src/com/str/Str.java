package com.str;

import java.util.HashSet;
import java.util.Set;

public class Str {
    public static void main(String[] args) {
        System.out.println(countSubstrings("12345654321"));
        lengthOfLongestSubstring("abcdefghijkaabc");
    }

    static int num = 0;

    //判断回文子串
    public static int countSubstrings(String s) {
        for (int i = 0; i < s.length(); i++) {
            count(s, i, i);//如果是奇数 对称中心是当前这个
            count(s, i, i + 1); //如果是偶数 对称为前后两个
        }

        return num;
    }


    public static void count(String s, int pre, int next) {
        while (pre >= 0 && next <= s.length() - 1 && s.charAt(pre) == s.charAt(next)) {
            pre--; //如果当前两个相等 找下一个相等的 前面指针向前 后面指针向后 等于说是对称的
            next++;
            num++;
        }
    }


    //无重复字符的最长子串
    //和答案不一样 时间复杂度为 o(n2)  时间复杂度太高 接下来继续优化
    public static int lengthOfLongestSubstring(String s) {

        char[] chs = s.toCharArray();

        Set<String> list = new HashSet<>();
        for (int i = 0; i < chs.length; i++) {
            String s1 = "";
            Set<Character> set = new HashSet<>();
            for (int j = i; j < chs.length; j++) {
                if (set.contains(chs[j])) {
                    set = new HashSet<Character>();
                    list.add(s1);
                    s1 = "";
                    s1 += chs[j];
                    set.add(chs[j]);
                } else {
                    s1 += chs[j];
                    set.add(chs[j]);
                }
            }
            if (s1.length() > 0)
                list.add(s1);
        }
        int length = 0;
        String[] lista = list.toArray(new String[0]);
        for (int i = 0; i < lista.length; i++) {
            if (length < lista[i].length())
                length = lista[i].length();
            System.out.println(lista[i]);

        }
        return length;


    }


}

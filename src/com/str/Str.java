package com.str;

public class Str {
    public static void main(String[] args) {
        System.out.println(countSubstrings("12345654321"));
    }
    static int num = 0 ;
    //判断回文子串
    public static int countSubstrings(String s){
        for (int i = 0; i < s.length() ; i++) {
            count(s,i,i);//如果是奇数 对称中心是当前这个
            count(s,i,i+1); //如果是偶数 对称为前后两个
        }

        return num ;
    }


    public static void count(String  s , int pre , int next){
        while(pre >= 0 && next <=s.length() -1  && s.charAt(pre) == s.charAt(next)){
            pre -- ; //如果当前两个相等 找下一个相等的 前面指针向前 后面指针向后 等于说是对称的
            next++ ;
            num++;
        }




    }



}

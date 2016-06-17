package com.xc;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        int userNum = args.length>1?Integer.valueOf(args[1]):100;

        System.out.println(userNum);


        System.out.println("Hello World!");
    }
}

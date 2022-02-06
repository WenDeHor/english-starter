package com.example.englishstarter.ivan;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("Number");
        Scanner sc = new Scanner(System.in);

        int number = sc.nextInt();
        System.out.println("Number");
        int number2 = sc.nextInt();

        for (int i = number; i <= number2; i++) {
            System.out.println(i);
        }
        System.out.println("Ivan right code+++");
    }
}

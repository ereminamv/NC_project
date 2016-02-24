package ru.UNCedu.phonebook;

/**
 * Created by 1345 on 28.01.2016.
 */
public class Number {
    private String number;
    private String holder;
    private int balance;

    public String getNumber() {
        return number;
    }

    public String getHolder() {
        return holder;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return number + " " + holder + " " + balance;
    }
}

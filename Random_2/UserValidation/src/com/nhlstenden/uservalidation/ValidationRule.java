package com.nhlstenden.uservalidation;

public interface ValidationRule
{
    boolean isValid(UserAccount account);
}
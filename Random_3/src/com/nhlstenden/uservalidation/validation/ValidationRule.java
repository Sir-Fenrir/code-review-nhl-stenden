package com.nhlstenden.uservalidation.validation;

import com.nhlstenden.uservalidation.User;

public interface ValidationRule
{
    boolean validate(User user);
}
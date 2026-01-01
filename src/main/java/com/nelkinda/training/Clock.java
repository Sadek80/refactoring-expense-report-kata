package com.nelkinda.training;

import java.util.Date;

public class Clock implements IClock{
    @Override
    public Date getDate() {
        return new Date();
    }
}

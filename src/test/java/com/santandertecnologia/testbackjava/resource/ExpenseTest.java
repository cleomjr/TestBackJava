package com.santandertecnologia.testbackjava.resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ExpenseTest {

    private Expense expense;

    @Before
    public void init() {
        expense = new Expense();
    }

    @Test
    public void expense_setDate_whenEnteredDateString() {
        expense.setDate("2019-01-08T12:32:58.596Z");
        Assert.assertTrue(1546957978596L == expense.getDate());
    }

    @Test
    public void expense_setDate_whenEnteredLong() {
        expense.setDate(1546957978596L);
        Assert.assertTrue(1546957978596L == expense.getDate());
    }
}

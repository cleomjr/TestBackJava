package com.santandertecnologia.testbackjava.service;

import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.resource.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExpenseService {
    public static final int MINIMUM_LENGTH = 3;
    private ExpenseRepository expenseRepository;
    private RedisTemplate<String, Expense> expenseRedisTemplate;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository, RedisTemplate<String, Expense> expenseRedisTemplate) {
        this.expenseRepository = expenseRepository;
        this.expenseRedisTemplate = expenseRedisTemplate;
    }

    public Expense createExpense(Expense expense) {
        // Usually it is recommended sanitize the data before persisting, in order to avoid XSS injections
        //
        // Expense expense = new Expense();
        // expense.setCategory(sanitize(receivedExpense.getCategory()));
        // ...
        Expense persistedExpense = new Expense(
                expenseRepository.count(),
                expense.getDescription(),
                expense.getValue(),
                expense.getUserCode(),
                expense.getDate(),
                expense.getCategory());

        return expenseRepository.save(persistedExpense);
    }

    public Iterable<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Set<String> getCategories(String category) {
        Iterable<Expense> expenses = getAllExpenses();
        Set<String> categories = new HashSet<>();
        if(category.length() >= MINIMUM_LENGTH) {
            getAllExpenses().forEach(e -> {
                if(e.getCategory().contains(category)) {
                    categories.add(e.getCategory());
                }
            });
        }
        return categories;
    }

    public List<Expense> getExpensesByDescription(String description) {
        return expenseRepository.findAllByDescription(description);
    }

    public List<Expense> getExpensesByPeriod(String fromDate, String toDate) {
        return expenseRepository.findAllByDateTimeBetween(Expense.convertStringToMillis(fromDate), Expense.convertStringToMillis(toDate));
    }

    public void setExpense(Integer expenseId, Expense newExpenseData) {
        StringBuffer expenseKey = new StringBuffer();
        expenseKey.append(Expense.class.getSimpleName()).append(":").append(expenseId.toString());

        Optional<Expense> expense = expenseRepository.findById(expenseId.toString());

        if(expense.isPresent()) {
            if(newExpenseData.getCategory() != null) {
                expense.get().setCategory(newExpenseData.getCategory());
            }

            if(newExpenseData.getDate() != null) {
                expense.get().setDate(newExpenseData.getDate());
            }

            if(newExpenseData.getDescription() != null) {
                expense.get().setDescription(newExpenseData.getDescription());
            }

            if(newExpenseData.getUserCode() != 0) {
                expense.get().setUserCode(newExpenseData.getUserCode());
            }

            if(newExpenseData.getValue() != 0.0) {
                expense.get().setValue(newExpenseData.getValue());
            }

            expenseRedisTemplate.opsForValue().set(expenseKey.toString(), expense.get());

        } else {
            throw new NoSuchElementException();
        }
    }


}

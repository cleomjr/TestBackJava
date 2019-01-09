package com.santandertecnologia.testbackjava.resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, String>, QueryByExampleExecutor<Expense> {

    List<Expense> findAllByCategory(String category);
    List<Expense> findAllByDescription(String description);
    List<Expense> findAllByDateTimeBetween(Long fromDate, Long toDate);
}
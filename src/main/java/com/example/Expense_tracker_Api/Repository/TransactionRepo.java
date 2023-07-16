package com.example.Expense_tracker_Api.Repository;

import com.example.Expense_tracker_Api.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    @Query(value = "select * from transactions t where t.category_id=?1 and t.transaction_id=?2",nativeQuery = true)
    Transaction getTransactionByID(int category_id,int transaction_id);
}

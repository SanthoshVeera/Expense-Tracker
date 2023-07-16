package com.example.Expense_tracker_Api.Resources;

import com.example.Expense_tracker_Api.Model.Category;
import com.example.Expense_tracker_Api.Model.Transaction;
import com.example.Expense_tracker_Api.Model.User;
import com.example.Expense_tracker_Api.Repository.CategoryRepo;
import com.example.Expense_tracker_Api.Repository.TransactionRepo;
import com.example.Expense_tracker_Api.Repository.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/ExpenseTracker")
public class TransactionResource {

    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    userRepo userRepo;
    @Autowired
    TransactionRepo transactionRepo;

    @PostMapping("/Transaction")
    public Transaction addNewTransaction(HttpServletRequest request, @RequestBody Map<String ,Object> map)
    {
        int user_id=(Integer)request.getAttribute("id");
        int category_id= (Integer) map.get("category_id");
        int transaction_id = (Integer) map.get("transaction_id");
        double amount = (double) map.get("amount");
        String note= (String) map.get("note");

        Category category=categoryRepo.findByUserIdAndCategoryId(user_id,category_id);
        User user = userRepo.findByUser_id(user_id);

        Transaction transaction = new Transaction(transaction_id,amount,note,category,user);
        return transactionRepo.save(transaction);

    }


    @GetMapping("/Category/{cid}/Transaction/{tid}")
    public Transaction getTransactionDetails(HttpServletRequest request,
                                             @PathVariable("cid") int category_id,
                                             @PathVariable("tid") int transaction_id)
    {
        return transactionRepo.getTransactionByID(category_id,transaction_id);
    }
}

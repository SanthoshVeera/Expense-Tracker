package com.example.Expense_tracker_Api.Repository;

import com.example.Expense_tracker_Api.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface userRepo extends JpaRepository<User,Integer> {
    User findByEmail(String email);
    @Query("select u from User u where u.user_id = ?1")
    User findByUser_id(int user_id);
    long countByEmail(String email);


}

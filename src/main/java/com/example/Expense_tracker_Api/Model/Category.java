package com.example.Expense_tracker_Api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Categories")
public class Category {

    @Id
    int Category_id;
    String title;
    String Description;

   @ManyToOne
   @JoinColumn(name = "user_id")
   User user;

   @Transient
   double total_expense;

    public Category(int category_id, String title, String descpriction, User user) {
        this.user =user;
//        this.user.user_id = user_id;
        this.Category_id = category_id;
        this.title = title;
        this.Description = descpriction;
    }
}

package com.example.Expense_tracker_Api.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    int user_id;
    int Category_id;
    String title;
    String Description;




}

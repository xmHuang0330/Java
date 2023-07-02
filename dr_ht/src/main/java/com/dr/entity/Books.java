package com.dr.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Books {

  private int bookId;
  private String bookName;
  private int bookCounts;
  private String detail;


    public int getBookID() {
        return 0;
    }
}

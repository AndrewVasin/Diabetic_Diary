package com.diary.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "glycemia")
public class Employee  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    // форматирование ввода даты для html-кода <input type="datetime-local"...
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_time")
    private LocalDateTime date;

    @NonNull
    @Column(name = "before_meals")
    private Float beforeMeals;

    @NonNull
    @Column(name = "after_meals")
    private Float afterMeals;

    @Column(name = "comment")
    private String comment;
}

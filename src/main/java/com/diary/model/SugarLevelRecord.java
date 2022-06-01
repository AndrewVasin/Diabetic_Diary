package com.diary.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "glycemia")
public class SugarLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //@NonNull
    @NotEmpty(message = "Пожалуйста, введите дату и время")
    // форматирование ввода даты для html-кода <input type="datetime-local"...
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_time")
    private LocalDateTime date;

    @NotEmpty(message = "Пожалуйста, введите уровень сахара")
    @Range(min = 0, max = 35, message = "Уровень сахара не может быть отрицательным или превышать 35 ммоль/л")
    @Column(name = "sugar_level")
    private Float sugarLevel;

    @NotEmpty(message = "Пожалуйста, введите вид измерения")
    @Size(min = 3, max = 35, message = "Пожалуйста, введите вид измерения (от 3 до 35 символов)")
    @Column(name = "measuring_type")
    private String measuringType;

    @Size(max = 255, message = "Длина коментария не должна превышать 255 символов")
    @Column(name = "comment")
    private String comment;
}

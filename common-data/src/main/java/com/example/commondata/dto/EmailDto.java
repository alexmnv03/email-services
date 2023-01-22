package com.example.commondata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // адрес получателя
    private String to;
    // тема письма
    private String subject;
    // Текс сообщения
    private String body;
    // полное имя пользователя
    private String fullName;
    // код авторизации
    private Integer code;
}

package org.skypro.coursework_2.exeption;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException() {
        super("Сообщение для пользователя");
    }
}

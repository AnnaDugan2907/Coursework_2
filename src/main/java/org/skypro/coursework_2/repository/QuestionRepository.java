package org.skypro.coursework_2.repository;

import org.skypro.coursework_2.model.Question;

import java.util.Collection;

public interface QuestionRepository {

    public Question add(String question, String answer);

    public Question remove(Question question);

    public Collection<Question> getAll();

}

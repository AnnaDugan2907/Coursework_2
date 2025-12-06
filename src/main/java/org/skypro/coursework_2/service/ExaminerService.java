package org.skypro.coursework_2.service;

import org.skypro.coursework_2.model.Question;

import java.util.Collection;

public interface ExaminerService {

    public Collection<Question> getQuestions(int amount);
}

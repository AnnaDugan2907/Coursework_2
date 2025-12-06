package org.skypro.coursework_2.model;

import org.skypro.coursework_2.exeption.QuestionNotFoundException;
import org.skypro.coursework_2.repository.QuestionRepository;
import org.skypro.coursework_2.service.QuestionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService {

    private static QuestionRepository repository;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository repository) {
        this.repository = repository;
    }

    public static int greet(String name) {
        return 0;
    }

    @Override
    public Question add(String question, String answer) {
        return repository.add(question, answer);
    }

    @Override
    public Question remove(Question question) {
        if (!repository.getAll().contains(question)) {
            throw new QuestionNotFoundException();
        }
        repository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }


    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int questionIndex = random.nextInt(repository.getAll().size());
        List<Question> questionsWithIndex = new ArrayList<>(repository.getAll());
        return questionsWithIndex.get(questionIndex);
    }
}

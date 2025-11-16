package org.skypro.coursework_2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework_2.model.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MathQuestionRepositoryTest {

    private MathQuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MathQuestionRepository();
    }

    @Test
    void testAddQuestion() {
        Question question = repository.add("Math вопрос 1", "Math ответ 1");
        assertNotNull(question);
        assertTrue(repository.getAll().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = repository.add("Math вопрос 2", "Math ответ 2");
        Question removedQuestion = repository.remove(question);
        assertEquals(question, removedQuestion);
        assertFalse(repository.getAll().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        Question q1 = repository.add("Math вопрос 3", "Math ответ 3");
        Question q2 = repository.add("Math вопрос 4", "Math ответ 4");
        Collection<Question> questions = repository.getAll();
        assertTrue(questions.contains(q1));
        assertTrue(questions.contains(q2));
    }
}

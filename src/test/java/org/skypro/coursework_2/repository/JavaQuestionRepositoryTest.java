package org.skypro.coursework_2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework_2.model.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaQuestionRepositoryTest {

    private JavaQuestionRepository repository;

    @BeforeEach
    void setUp() {
        repository = new JavaQuestionRepository();
    }

    @Test
    void testAddQuestion() {
        Question question = repository.add("Java вопрос 1", "Java ответ 1");
        assertNotNull(question);
        assertTrue(repository.getAll().contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = repository.add("Java вопрос 2", "Java ответ 2");
        Question removedQuestion = repository.remove(question);
        assertEquals(question, removedQuestion);
        assertFalse(repository.getAll().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        Question q1 = repository.add("Java вопрос 3", "Java ответ 3");
        Question q2 = repository.add("Java вопрос 4", "Java ответ 4");
        Collection<Question> questions = repository.getAll();
        assertTrue(questions.contains(q1));
        assertTrue(questions.contains(q2));
    }

    @Test
    void testGetAllWhenEmpty() {
        Collection<Question> questions = repository.getAll();
        assertTrue(questions.isEmpty());
    }
}

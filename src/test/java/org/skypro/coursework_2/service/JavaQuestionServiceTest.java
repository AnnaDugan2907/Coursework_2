package org.skypro.coursework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework_2.exeption.QuestionNotFoundException;
import org.skypro.coursework_2.model.JavaQuestionService;
import org.skypro.coursework_2.model.Question;
import org.skypro.coursework_2.repository.QuestionRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class JavaQuestionServiceTest {

    private QuestionRepository repository;
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        repository = mock(QuestionRepository.class);
        javaQuestionService = new JavaQuestionService(repository);
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("Java вопрос 1", "Java ответ 1");
        when(repository.add(anyString(), anyString())).thenReturn(question);

        Question result = javaQuestionService.add("Java вопрос 1", "Java ответ 1");

        assertEquals(question, result);
        verify(repository).add("Java вопрос 1", "Java ответ 1");
    }

    @Test
    void testRemoveQuestionSuccess() {
        Question question = new Question("Java вопрос 2", "Java ответ 2");
        when(repository.getAll()).thenReturn(Collections.singletonList(question));

        Question result = javaQuestionService.remove(question);

        assertEquals(question, result);
        verify(repository).remove(question);
    }

    @Test
    void testRemoveQuestionNotFound() {
        Question question = new Question("Java вопрос 3", "Java ответ 3");
        when(repository.getAll()).thenReturn(Collections.emptyList());

        assertThrows(QuestionNotFoundException.class, () -> javaQuestionService.remove(question));
    }

    @Test
    void testGetAll() {
        List<Question> questions = Arrays.asList(new Question("Java вопрос 4", "Java ответ 4"), new Question("Java вопрос 5", "Java ответ 5"));
        when(repository.getAll()).thenReturn(questions);

        Collection<Question> result = javaQuestionService.getAll();

        assertEquals(questions, result);
    }

    @Test
    void testGetRandomQuestion() {
        List<Question> questions = Arrays.asList(new Question("Java вопрос 6", "Java ответ 6"), new Question("Java вопрос 7", "Java ответ 7"));
        when(repository.getAll()).thenReturn(questions);

        Question randomQuestion = javaQuestionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(questions.contains(randomQuestion));
    }
}

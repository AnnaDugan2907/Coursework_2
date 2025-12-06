package org.skypro.coursework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework_2.exeption.QuestionNotFoundException;
import org.skypro.coursework_2.model.MathQuestionService;
import org.skypro.coursework_2.model.Question;
import org.skypro.coursework_2.repository.QuestionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class MathQuestionServiceTest {

    private QuestionRepository repository;
    private MathQuestionService mathQuestionService;

    @BeforeEach
    void setUp() {
        repository = mock(QuestionRepository.class);
        mathQuestionService = new MathQuestionService(repository);
    }

    @Test
    void testAddQuestion() {
        Question question = new Question("Math вопрос 1", "Math ответ 1");
        when(repository.add(anyString(), anyString())).thenReturn(question);

        Question result = mathQuestionService.add("Math вопрос 1", "Math ответ 1");

        assertEquals(question, result);
        verify(repository).add("Math вопрос 1", "Math ответ 1");
    }

    @Test
    void testRemoveQuestionSuccess() {
        Question question = new Question("Math вопрос 2", "Math ответ 2");
        when(repository.getAll()).thenReturn(Collections.singletonList(question));

        Question result = mathQuestionService.remove(question);

        assertEquals(question, result);
        verify(repository).remove(question);
    }

    @Test
    void testRemoveQuestionNotFound() {
        Question question = new Question("Math вопрос 2", "Math ответ 2");
        when(repository.getAll()).thenReturn(Collections.emptyList());

        assertThrows(QuestionNotFoundException.class, () -> mathQuestionService.remove(question));
    }

    @Test
    void testGetAll() {
        List<Question> questions = Arrays.asList(new Question("Math вопрос 3", "Math ответ 3"), new Question("Math вопрос 4", "Math ответ 4"));
        when(repository.getAll()).thenReturn(questions);

        List<Question> result = (List<Question>) mathQuestionService.getAll();

        assertEquals(questions, result);
    }

    @Test
    void testGetRandomQuestion() {
        List<Question> questions = Arrays.asList(new Question("Math вопрос 3", "Math ответ 3"), new Question("Math вопрос 4", "Math ответ 4"));
        when(repository.getAll()).thenReturn(questions);

        Question randomQuestion = mathQuestionService.getRandomQuestion();

        assertNotNull(randomQuestion);
        assertTrue(questions.contains(randomQuestion));
    }
}

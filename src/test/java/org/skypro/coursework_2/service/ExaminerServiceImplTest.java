package org.skypro.coursework_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skypro.coursework_2.model.ExaminerServiceImpl;
import org.skypro.coursework_2.model.Question;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ExaminerServiceImplTest {

    private QuestionService javaQuestionService;
    private QuestionService mathQuestionService;
    private ExaminerServiceImpl examinerService;

    @BeforeEach
    void setUp() {
        javaQuestionService = mock(QuestionService.class);
        mathQuestionService = mock(QuestionService.class);
        examinerService = new ExaminerServiceImpl(javaQuestionService, mathQuestionService);
    }

    @Test
    void testGetQuestionsEnoughQuestions() {
        Question q1 = new Question("Examiner вопрос 1", "Examiner ответ 1");
        Question q2 = new Question("Examiner вопрос 2", "Examiner ответ 2");
        when(javaQuestionService.getAll()).thenReturn(Arrays.asList(q1));
        when(mathQuestionService.getAll()).thenReturn(Arrays.asList(q2));
        when(javaQuestionService.getRandomQuestion()).thenReturn(q1);
        when(mathQuestionService.getRandomQuestion()).thenReturn(q2);

        Collection<Question> questions = examinerService.getQuestions(2);

        assertEquals(2, questions.size());
        verify(javaQuestionService, atLeast(1)).getRandomQuestion();
        verify(mathQuestionService, atLeast(1)).getRandomQuestion();
    }

    @Test
    void testGetQuestionsNotEnoughQuestions() {
        when(javaQuestionService.getAll()).thenReturn(Collections.emptyList());
        when(mathQuestionService.getAll()).thenReturn(Collections.emptyList());

        assertThrows(org.skypro.coursework_2.exeption.QuestionIllegalArgumentException.class, () -> {
            examinerService.getQuestions(1);
        });
    }
}

package com.wxthxy.zj.controller;

import com.wxthxy.zj.entity.ApplicationQuestion;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PageBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class QuestionControllerTest {

    private MockMvc mockMvc;
    private QuestionService questionService;

    @Before
    public void setUp() {
        QuestionController controller = new QuestionController();
        questionService = Mockito.mock(QuestionService.class);
        ReflectionTestUtils.setField(controller, "service", questionService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldRenderQuestionManagement() throws Exception {
        when(questionService.getQuestionCounts()).thenReturn(new Object());

        mockMvc.perform(get("/zj/question"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/QuestionManagement"))
                .andExpect(request().attributeExists("questions"));

        verify(questionService, times(1)).getQuestionCounts();
    }

    @Test
    public void shouldRenderChoiceQuestionInfoWithDefaultPage() throws Exception {
        when(questionService.findAllQuestions("选择题", 1)).thenReturn(new PageBean<>(1, 5, 10));

        mockMvc.perform(get("/zj/questionInfo/选择题"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/info/ChoicequestionInfo"))
                .andExpect(request().attributeExists("pageBean"));

        verify(questionService, times(1)).findAllQuestions("选择题", 1);
    }

    @Test
    public void shouldRenderApplicationQuestionInfoWithPage() throws Exception {
        when(questionService.findAllQuestions("应用题", 2)).thenReturn(new PageBean<>(2, 5, 10));

        mockMvc.perform(get("/zj/questionInfo/应用题").param("currentPage", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/info/ApplicationquestionInfo"))
                .andExpect(request().attributeExists("pageBean"));

        verify(questionService, times(1)).findAllQuestions("应用题", 2);
    }

    @Test
    public void shouldRenderOtherQuestionInfo() throws Exception {
        when(questionService.findAllQuestions("简答题", 3)).thenReturn(new PageBean<>(3, 5, 10));

        mockMvc.perform(get("/zj/questionInfo/简答题").param("currentPage", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/info/OtherInfo"))
                .andExpect(request().attributeExists("pageBean"));

        verify(questionService, times(1)).findAllQuestions("简答题", 3);
    }

    @Test
    public void shouldAddChoiceQuestion() throws Exception {
        when(questionService.addChoiceQuestion(any(Choicequestion.class))).thenReturn("ok");

        mockMvc.perform(post("/zj/choiceQuestion")
                        .param("title", "t1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).addChoiceQuestion(any(Choicequestion.class));
    }

    @Test
    public void shouldDeleteChoiceQuestion() throws Exception {
        when(questionService.delChoiceQuestion(10)).thenReturn("ok");

        mockMvc.perform(delete("/zj/choiceQuestion/10"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).delChoiceQuestion(10);
    }

    @Test
    public void shouldGetChoiceQuestionById() throws Exception {
        Choicequestion q = new Choicequestion();
        when(questionService.getChoicequestion(5)).thenReturn(q);

        mockMvc.perform(get("/zj/choiceQuestion/5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ChoiceQuestion")));

        verify(questionService, times(1)).getChoicequestion(5);
    }

    @Test
    public void shouldUpdateChoiceQuestion() throws Exception {
        when(questionService.updateChoiceQuestion(any(Choicequestion.class))).thenReturn("ok");

        mockMvc.perform(post("/zj/upchoiceQuestion")
                        .param("id", "7")
                        .param("title", "t2"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).updateChoiceQuestion(any(Choicequestion.class));
    }

    @Test
    public void shouldAddApplicationQuestion() throws Exception {
        when(questionService.addApplicationQue(any(ApplicationQuestion.class))).thenReturn("ok");
        MockMultipartFile file = new MockMultipartFile("imgfile", "a.png", "image/png", "123".getBytes());

        mockMvc.perform(multipart("/zj/applicationQue")
                        .file(file)
                        .param("title", "t3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).addApplicationQue(any(ApplicationQuestion.class));
        deleteAppImgIfExists("a.png");
    }

    @Test
    public void shouldUpdateApplicationQuestion() throws Exception {
        when(questionService.updateApplication(any(ApplicationQuestion.class))).thenReturn("ok");
        MockMultipartFile file = new MockMultipartFile("imgfile", "b.png", "image/png", "456".getBytes());

        mockMvc.perform(multipart("/zj/upapplicationQue")
                        .file(file)
                        .param("id", "12"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).updateApplication(any(ApplicationQuestion.class));
        deleteAppImgIfExists("b.png");
    }

    @Test
    public void shouldGetApplicationQuestionById() throws Exception {
        when(questionService.loadApplication(9)).thenReturn(new ApplicationQuestion());

        mockMvc.perform(get("/zj/applicationQue/9"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("application")));

        verify(questionService, times(1)).loadApplication(9);
    }

    @Test
    public void shouldDeleteApplicationQuestion() throws Exception {
        when(questionService.deleteAppQue(6)).thenReturn("ok");

        mockMvc.perform(delete("/zj/ApplicationQuestion/6"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).deleteAppQue(6);
    }

    @Test
    public void shouldAddOtherQuestion() throws Exception {
        when(questionService.addQuestion(any(Question.class))).thenReturn("ok");

        mockMvc.perform(post("/zj/addOthers")
                        .param("type", "简答题")
                        .param("question", "q1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).addQuestion(any(Question.class));
    }

    @Test
    public void shouldDeleteOtherQuestion() throws Exception {
        when(questionService.delQuestion(3, "简答题")).thenReturn("ok");

        mockMvc.perform(delete("/zj/delOthers")
                        .param("type", "简答题")
                        .param("id", "3"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).delQuestion(3, "简答题");
    }

    @Test
    public void shouldUpdateOtherQuestion() throws Exception {
        when(questionService.updateQuestion(any(Question.class))).thenReturn("ok");

        mockMvc.perform(post("/zj/updateOthers")
                        .param("id", "4")
                        .param("type", "简答题"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ok")));

        verify(questionService, times(1)).updateQuestion(any(Question.class));
    }

    @Test
    public void shouldFindOtherQuestionById() throws Exception {
        when(questionService.findQuestionByid(8, "填空题")).thenReturn(new Question());

        mockMvc.perform(get("/zj/findOther")
                        .param("type", "填空题")
                        .param("id", "8"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("otherQue")));

        verify(questionService, times(1)).findQuestionByid(8, "填空题");
    }

    private void deleteAppImgIfExists(String filename) throws Exception {
        String path = QuestionController.class.getResource("QuestionController.class").toString();
        path = URLDecoder.decode(path);
        path = path.replaceAll("\\\\", "/");
        path = path.substring(path.indexOf(":") + 2, path.indexOf("target"));
        path = path + "src/main/resources/static/appimg/" + filename;
        Path filePath = Paths.get(path);
        Files.deleteIfExists(filePath);
    }
}

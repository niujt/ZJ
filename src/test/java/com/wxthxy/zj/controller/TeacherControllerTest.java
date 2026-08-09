package com.wxthxy.zj.controller;

import com.wxthxy.zj.entity.Answer;
import com.wxthxy.zj.entity.HomeWork;
import com.wxthxy.zj.entity.Teacher;
import com.wxthxy.zj.service.HomeworkService;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.TeacherService;
import com.wxthxy.zj.utils.PageBean;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class TeacherControllerTest {

    private static final Path INPUT_EXCEL = Paths.get(
            "src", "test", "java", "com", "wxthxy", "zj", "controller", "TeacherControllerCases.xlsx");
    private static final Path OUTPUT_EXCEL = Paths.get(
            "src", "test", "java", "com", "wxthxy", "zj", "controller", "TeacherControllerResults.xlsx");

    // Mock Keys
    private static final String TEACHER_LIST_DEFAULT = "teacher_list_default";
    private static final String TEACHER_LIST_PAGE3 = "teacher_list_page3";
    private static final String ADD_TEACHER = "add_teacher";
    private static final String DELETE_TEACHER = "delete_teacher";
    private static final String HOMEWORK_LIST = "homework_list";
    private static final String HOMEWORK_LIST_DEFAULT = "homework_list_default";
    private static final String HOMEWORK_DETAIL = "homework_detail";
    private static final String TEACHER_INDEX = "teacher_index";
    private static final String CHECK_PAGE = "check_page";
    private static final String SUBHOMEWORK = "subhomework";
    private static final String EVA_HOMEWORK = "eva_homework";

    private MockMvc mockMvc;
    private TeacherService teacherService;
    private HomeworkService homeworkService;
    private PaperService paperService;
    private Map<String, Consumer<Void>> mockSetups;
    private Map<String, Consumer<Void>> verifications;

    @Before
    public void setUp() {
        TeacherController controller = new TeacherController();
        teacherService = Mockito.mock(TeacherService.class);
        homeworkService = Mockito.mock(HomeworkService.class);
        paperService = Mockito.mock(PaperService.class);

        ReflectionTestUtils.setField(controller, "service", teacherService);
        ReflectionTestUtils.setField(controller, "homeworkService", homeworkService);
        ReflectionTestUtils.setField(controller, "paperService", paperService);

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        
        initializeMockSetups();
        initializeVerifications();
    }

    private void initializeMockSetups() {
        mockSetups = new HashMap<>();
        
        mockSetups.put(TEACHER_LIST_DEFAULT, v -> 
            when(teacherService.getAllTeacher(1)).thenReturn(new PageBean<>(1, 5, 10))
        );
        
        mockSetups.put(TEACHER_LIST_PAGE3, v -> 
            when(teacherService.getAllTeacher(3)).thenReturn(new PageBean<>(3, 5, 20))
        );
        
        mockSetups.put(ADD_TEACHER, v -> 
            when(teacherService.addTea(any(Teacher.class))).thenReturn("保存成功")
        );
        
        mockSetups.put(DELETE_TEACHER, v -> 
            when(teacherService.delTea(10)).thenReturn("删除成功")
        );
        
        mockSetups.put(HOMEWORK_LIST, v -> 
            when(homeworkService.getAll(2, "Java卷", "张三")).thenReturn(new PageBean<>(2, 5, 8))
        );
        
        mockSetups.put(HOMEWORK_LIST_DEFAULT, v -> 
            when(homeworkService.getAll(1, null, null)).thenReturn(new PageBean<>(1, 5, 8))
        );
        
        mockSetups.put(HOMEWORK_DETAIL, v -> {
            Map<String, Object> answerMap = new HashMap<>();
            answerMap.put("cpanswers", Collections.singletonList("A"));
            answerMap.put("cqanswers", Collections.singletonList("B"));
            answerMap.put("jqanswers", Collections.singletonList("T"));
            answerMap.put("dpanswers", Collections.singletonList("设计题答案"));
            answerMap.put("aqanswers", Collections.singletonList("应用题答案"));

            HomeWork homeWork = new HomeWork();
            homeWork.setId(100);
            homeWork.setPaperid(88);

            Answer answer = new Answer();
            answer.setCpanwsers(Collections.singletonList("1:A"));
            answer.setCqanwsers(Collections.singletonList("1:B"));
            answer.setJqanwsers(Collections.singletonList("1:T"));

            Map<String, Object> paperResult = new HashMap<>();
            paperResult.put("answers", answer);

            when(homeworkService.getHomeworkAnswer(100)).thenReturn(answerMap);
            when(homeworkService.findHomeWork(100)).thenReturn(homeWork);
            when(paperService.getPaperById(88)).thenReturn(paperResult);
        });
        
        mockSetups.put(CHECK_PAGE, v -> {
            HomeWork hw1 = new HomeWork();
            hw1.setDpanswer("abcdef;xyzab");
            hw1.setApanswer("mnopq;mnopz");

            HomeWork hw2 = new HomeWork();
            hw2.setDpanswer("12abcde;uvwxy");
            hw2.setApanswer("ttmnop;hello");

            when(homeworkService.getAll()).thenReturn(Arrays.asList(hw1, hw2));
        });
    }

    private void initializeVerifications() {
        verifications = new HashMap<>();
        
        verifications.put(TEACHER_LIST_DEFAULT, v -> 
            verify(teacherService, times(1)).getAllTeacher(1)
        );
        
        verifications.put(TEACHER_LIST_PAGE3, v -> 
            verify(teacherService, times(1)).getAllTeacher(3)
        );
        
        verifications.put(ADD_TEACHER, v -> 
            verify(teacherService, times(1)).addTea(any(Teacher.class))
        );
        
        verifications.put(DELETE_TEACHER, v -> 
            verify(teacherService, times(1)).delTea(10)
        );
        
        verifications.put(HOMEWORK_LIST, v -> 
            verify(homeworkService, times(1)).getAll(2, "Java卷", "张三")
        );
        
        verifications.put(HOMEWORK_LIST_DEFAULT, v -> 
            verify(homeworkService, times(1)).getAll(1, null, null)
        );
        
        verifications.put(HOMEWORK_DETAIL, v -> {
            verify(homeworkService, times(1)).getHomeworkAnswer(100);
            verify(homeworkService, times(1)).findHomeWork(100);
            verify(paperService, times(1)).getPaperById(88);
        });
        
        verifications.put(SUBHOMEWORK, v -> 
            verify(homeworkService, times(1)).score(any())
        );
        
        verifications.put(EVA_HOMEWORK, v -> 
            verify(homeworkService, times(1)).eva(any(HomeWork.class))
        );
        
        verifications.put(CHECK_PAGE, v -> 
            verify(homeworkService, times(1)).getAll()
        );
    }

    @Test
    public void runTeacherControllerCasesFromExcelAndWriteResults() throws Exception {
        assertTrue("输入用例文件不存在: " + INPUT_EXCEL.toAbsolutePath(), Files.exists(INPUT_EXCEL));
        List<CaseRow> cases = readCases(INPUT_EXCEL);

        List<ResultRow> results = new ArrayList<>();
        List<String> failedCases = new ArrayList<>();

        for (CaseRow c : cases) {
            if (!c.enabled) {
                continue;
            }
            Mockito.reset(teacherService, homeworkService, paperService);
            try {
                prepareMocks(c.mockKey);
                MvcResult mvcResult = execute(c);
                validate(c, mvcResult);
                verifyInteractions(c.mockKey);
                results.add(ResultRow.pass(c, mvcResult));
            } catch (Throwable t) {
                results.add(ResultRow.fail(c, t));
                failedCases.add(c.caseId + ": " + t.getMessage());
            }
        }

        writeResults(OUTPUT_EXCEL, results);
        assertTrue("存在失败用例，请查看结果文件: " + OUTPUT_EXCEL.toAbsolutePath() + " -> " + failedCases, failedCases.isEmpty());
    }

    private List<CaseRow> readCases(Path path) throws IOException {
        List<CaseRow> cases = new ArrayList<>();
        try (InputStream in = Files.newInputStream(path); Workbook wb = new XSSFWorkbook(in)) {
            Sheet sheet = wb.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                String caseId = readString(row.getCell(0));
                if (isBlank(caseId)) {
                    continue;
                }
                CaseRow c = new CaseRow();
                c.caseId = caseId;
                c.caseName = readString(row.getCell(1));
                c.mockKey = readString(row.getCell(2));
                c.method = readString(row.getCell(3));
                c.url = readString(row.getCell(4));
                c.params = parseParams(readString(row.getCell(5)));
                c.expectedStatus = parseIntOrDefault(readString(row.getCell(6)), 200);
                c.expectedView = readString(row.getCell(7));
                c.expectedRedirect = readString(row.getCell(8));
                c.expectedContains = readString(row.getCell(9));
                c.enabled = !"N".equalsIgnoreCase(readString(row.getCell(10)));
                cases.add(c);
            }
        }
        return cases;
    }

    private void prepareMocks(String mockKey) {
        Consumer<Void> setup = mockSetups.get(mockKey);
        if (setup != null) {
            setup.accept(null);
        }
    }

    private MvcResult execute(CaseRow c) throws Exception {
        MockHttpServletRequestBuilder builder = createRequestBuilder(c.method, c.url);

        for (Map.Entry<String, String> e : c.params.entrySet()) {
            builder.param(e.getKey(), e.getValue());
        }

        if (TEACHER_INDEX.equals(c.mockKey)) {
            setupTeacherSession(builder);
        }

        return mockMvc.perform(builder).andReturn();
    }

    private MockHttpServletRequestBuilder createRequestBuilder(String method, String url) {
        if ("GET".equalsIgnoreCase(method)) {
            return get(url);
        } else if ("POST".equalsIgnoreCase(method)) {
            return post(url);
        } else if ("DELETE".equalsIgnoreCase(method)) {
            return delete(url);
        }
        throw new IllegalArgumentException("不支持的方法: " + method);
    }

    private void setupTeacherSession(MockHttpServletRequestBuilder builder) {
        Teacher teacher = new Teacher();
        teacher.setId(7);
        teacher.setName("李老师");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("message", teacher);
        builder.session(session);
    }

    private void validate(CaseRow c, MvcResult result) throws Exception {
        validateStatus(c, result);
        validateView(c, result);
        validateRedirect(c, result);
        validateContent(c, result);
    }

    private void validateStatus(CaseRow c, MvcResult result) {
        int actualStatus = result.getResponse().getStatus();
        if (actualStatus != c.expectedStatus) {
            throw new AssertionError("状态码不匹配, expected=" + c.expectedStatus + ", actual=" + actualStatus);
        }
    }

    private void validateView(CaseRow c, MvcResult result) {
        if (isBlank(c.expectedView)) {
            return;
        }
        String actualView = "";
        if (result.getModelAndView() != null && result.getModelAndView().getViewName() != null) {
            actualView = result.getModelAndView().getViewName();
        }
        if (!c.expectedView.equals(actualView)) {
            throw new AssertionError("视图不匹配, expected=" + c.expectedView + ", actual=" + actualView);
        }
    }

    private void validateRedirect(CaseRow c, MvcResult result) {
        if (isBlank(c.expectedRedirect)) {
            return;
        }
        String actualRedirect = result.getResponse().getRedirectedUrl();
        if (actualRedirect == null || !c.expectedRedirect.equals(actualRedirect)) {
            throw new AssertionError("重定向不匹配, expected=" + c.expectedRedirect + ", actual=" + actualRedirect);
        }
    }

    private void validateContent(CaseRow c, MvcResult result) throws Exception {
        if (isBlank(c.expectedContains)) {
            return;
        }
        String body = result.getResponse().getContentAsString();
        if (body == null || !body.contains(c.expectedContains)) {
            throw new AssertionError("响应内容不包含期望文本, expectedContains=" + c.expectedContains + ", actual=" + body);
        }
    }

    private void verifyInteractions(String mockKey) {
        Consumer<Void> verification = verifications.get(mockKey);
        if (verification != null) {
            verification.accept(null);
        }
    }

    private void writeResults(Path outputPath, List<ResultRow> rows) throws IOException {
        Files.createDirectories(outputPath.getParent());
        try (Workbook wb = new XSSFWorkbook()) {
            Sheet sheet = wb.createSheet("results");
            Row header = sheet.createRow(0);
            writeCell(header, 0, "case_id");
            writeCell(header, 1, "case_name");
            writeCell(header, 2, "status");
            writeCell(header, 3, "detail");
            writeCell(header, 4, "actual_http_status");
            writeCell(header, 5, "actual_view");
            writeCell(header, 6, "actual_redirect");
            writeCell(header, 7, "actual_content");

            int r = 1;
            for (ResultRow row : rows) {
                Row data = sheet.createRow(r++);
                writeCell(data, 0, row.caseId);
                writeCell(data, 1, row.caseName);
                writeCell(data, 2, row.status);
                writeCell(data, 3, row.detail);
                writeCell(data, 4, row.actualHttpStatus == null ? "" : String.valueOf(row.actualHttpStatus));
                writeCell(data, 5, row.actualView);
                writeCell(data, 6, row.actualRedirect);
                writeCell(data, 7, row.actualContent);
            }

            for (int i = 0; i <= 7; i++) {
                sheet.autoSizeColumn(i);
            }
            try (OutputStream out = Files.newOutputStream(outputPath)) {
                wb.write(out);
            }
        }
    }

    private Map<String, String> parseParams(String text) {
        Map<String, String> map = new LinkedHashMap<>();
        if (isBlank(text)) {
            return map;
        }
        String[] pairs = text.split(";");
        for (String pair : pairs) {
            String p = pair.trim();
            if (p.isEmpty()) {
                continue;
            }
            int idx = p.indexOf('=');
            if (idx <= 0) {
                continue;
            }
            String key = p.substring(0, idx).trim();
            String value = p.substring(idx + 1).trim();
            map.put(key, value);
        }
        return map;
    }

    private void writeCell(Row row, int col, String val) {
        Cell cell = row.createCell(col);
        cell.setCellValue(val == null ? "" : val);
    }

    private String readString(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            double d = cell.getNumericCellValue();
            long l = (long) d;
            if (Math.abs(d - l) < 0.000001d) {
                return String.valueOf(l);
            }
            return String.valueOf(d);
        }
        if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        return cell.toString().trim();
    }

    private int parseIntOrDefault(String text, int def) {
        if (isBlank(text)) {
            return def;
        }
        return Integer.parseInt(text.trim());
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static class CaseRow {
        String caseId;
        String caseName;
        String mockKey;
        String method;
        String url;
        Map<String, String> params;
        int expectedStatus;
        String expectedView;
        String expectedRedirect;
        String expectedContains;
        boolean enabled;
    }

    private static class ResultRow {
        String caseId;
        String caseName;
        String status;
        String detail;
        Integer actualHttpStatus;
        String actualView;
        String actualRedirect;
        String actualContent;

        static ResultRow pass(CaseRow c, MvcResult result) throws Exception {
            ResultRow r = new ResultRow();
            r.caseId = c.caseId;
            r.caseName = c.caseName;
            r.status = "PASS";
            r.detail = "";
            r.actualHttpStatus = result.getResponse().getStatus();
            String viewName = "";
            if (result.getModelAndView() != null && result.getModelAndView().getViewName() != null) {
                viewName = result.getModelAndView().getViewName();
            }
            r.actualView = viewName;
            r.actualRedirect = result.getResponse().getRedirectedUrl() != null ? result.getResponse().getRedirectedUrl() : "";
            r.actualContent = result.getResponse().getContentAsString();
            return r;
        }

        static ResultRow fail(CaseRow c, Throwable t) {
            ResultRow r = new ResultRow();
            r.caseId = c.caseId;
            r.caseName = c.caseName;
            r.status = "FAIL";
            r.detail = t.getClass().getSimpleName() + ": " + (t.getMessage() == null ? "" : t.getMessage());
            r.actualView = "";
            r.actualRedirect = "";
            r.actualContent = "";
            return r;
        }
    }
}

package test;

import com.wxthxy.zj.ZjApp;
import com.wxthxy.zj.dao.*;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Completion;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.PaperService;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PaperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = ZjApp.class)
@RunWith(SpringRunner.class)
public class TestCase {
    @Autowired
    QuestionService service;
    @Autowired
    PaperDAO paperDAO;
    @Autowired
    ChoicequestionDAO choicequestionDAO;
    @Autowired
    CompletionDAO completionDAO;
    @Autowired
    JudgementquestionDAO judgementquestionDAO;
    @Autowired
    DesignproblemDAO designproblemDAO;
    @Autowired
    PaperService paperService;
    @Test
    public void test1(){
        List<Choicequestion> list=service.findAllQuestions("选择题");
        for(Choicequestion cq:list){
            System.out.println( cq.getText());
        }
    }
    @Test
    public void test2(){
        Choicequestion q=new Choicequestion();
        q.setId(10);
        q.setAnswer("1111");
       System.out.println(service.updateChoiceQuestion(q));
    }
    @Test
    public void test3(){
        Question q=new Question();
        q.setAnswer("fsdfsdfsdfsdfsd");
        q.setText("sdfsdfsd");
       // q.setType("简答题");
        q.setType("判断题");
        System.out.println(service.addQuestion(q));
    }
    @Test
    public void test4(){
        List<Paper> papers=paperDAO.findAllPapers();
        PaperUtils.doPaper(papers);
        papers.forEach(System.out::println);
    }
    @Test
    public void test5(){
        //List<Integer> ids=new ArrayList<>();
        //ids.add(1);
        //ids.add(2);
        //ids.add(3);
        //choicequestionDAO.findChoicequestion4Paper(ids).forEach(System.out::println);
        //completionDAO.findCompletion4Paper(ids).forEach(System.out::println);
        //judgementquestionDAO.findJudgementQuestion4Paper(ids).forEach(System.out::println);
        //designproblemDAO.findDesignProblem4Paper(ids).forEach(System.out::println);
        System.out.println(paperService.getPaperById(1).get("answers"));
    }
}

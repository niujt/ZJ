package test;

import com.wxthxy.zj.ZjApp;
import com.wxthxy.zj.dao.PaperDAO;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Paper;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
import com.wxthxy.zj.utils.PaperUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = ZjApp.class)
@RunWith(SpringRunner.class)
public class TestCase {
    @Autowired
    QuestionService service;
    @Autowired
    PaperDAO paperDAO;
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
}

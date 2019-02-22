package test;

import com.wxthxy.zj.ZjApp;
import com.wxthxy.zj.entity.Choicequestion;
import com.wxthxy.zj.entity.Question;
import com.wxthxy.zj.service.QuestionService;
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
}

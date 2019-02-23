package test;

import com.wxthxy.zj.utils.PaperUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UtilTest {
    @Test
    public void test1(){
        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        ids.add(8);
        ids.add(9);
        ids.add(10);
        ids.add(11);
        System.out.println(PaperUtils.autoQustionId(ids,5));
    }
}

package ptest;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.BeanUtils;

public class BeanUtilsTest {
 //테스트 입니다.asdfadsf 
	//adf
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException  {

    	Map<String, Object> map = createMapListForTest() ;
        User u = new User();
        User2 u2 = new User2();

        org.springframework.beans.BeanUtils.copyProperties(map, u);
        System.out.println(u);
        org.springframework.beans.BeanUtils.copyProperties(u, u2);
        System.out.println(u2);
        org.apache.commons.beanutils.BeanUtils.copyProperties(u, map);
        System.out.println(u);
        org.springframework.beans.BeanUtils.copyProperties(u, u2);
        System.out.println(u2);




    }


    public static  Map<String, Object> createMapListForTest() {
		Map<String, Object> user = new HashMap<String, Object>();
		user.put("id", 1);
		user.put("age", 1);
		user.put("name", "내이름");
		user.put("name1", "내이름");
		user.put("name2", "내이름");
		user.put("name3", "내이름");
		user.put("name4", "내이름");
		user.put("name5", "내이름");
		user.put("name6", "내이름");
		user.put("name7", "내이름");
		user.put("name8", "내이름");
		user.put("name9", "내이름");
		user.put("name10", "내이름");
		user.put("income", new BigDecimal("1000100100"));
		user.put("address", "오늘 아침 내가 행복한 이유는 이런거지 오늘아침 내가 서러운 이유는 그런거야 ");
		user.put("introduce", "오늘 아침 내가 행복한 이유는 이런거지 오늘아침 내가 서러운 이유는 그런거야 ");
		user.put("married", true);
		user.put("nickName", "뻐꾸기");
		return user;
	}
}

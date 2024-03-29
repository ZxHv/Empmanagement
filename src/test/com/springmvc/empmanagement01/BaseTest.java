package test.com.springmvc.empmanagement01;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:main/resources/spring/spring-dao.xml", "classpath:main/resources/spring/spring-service.xml" })
public class BaseTest
{

}

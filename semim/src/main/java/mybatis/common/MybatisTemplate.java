package mybatis.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisTemplate {
	public static SqlSession getSqlSession() {
		String resource = "mybatis-donfig.xml";
		SqlSession session = null;
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			session = sqlSessionFactory.openSession(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return session;
	}
}
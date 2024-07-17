package projectMybatis.mybatisConfig;



import org.apache.ibatis.session.*;


import java.io.Reader;

import org.apache.ibatis.io.Resources;


public class MybatisManager {
	
	private static SqlSessionFactory instance;
	
	private MybatisManager() {
	} 
	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
			String resource = "projectMybatis/mybatisConfig/mapperConfig.xml";
			
			reader = Resources.getResourceAsReader(resource);
			instance = new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(reader != null) {reader.close();}
			}catch (Exception e) {
			}
		}
		return instance;
	}
}

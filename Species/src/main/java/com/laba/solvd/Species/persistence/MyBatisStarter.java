package com.laba.solvd.Species.persistence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class MyBatisStarter {
    private static final Logger logger = Logger.getLogger("GLOBAL");
    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSession() {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (IOException e) {
            logger.error(e);
        }
        return sqlSessionFactory.openSession();
    }
}

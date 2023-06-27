package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Class;
import com.laba.solvd.Species.persistence.ClassRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;

public class ClassMapperImpl implements ClassRepository {
    private SqlSession sqlSession;

    public ClassMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Class oclass) {
        sqlSession.insert("create", oclass);
    }
}

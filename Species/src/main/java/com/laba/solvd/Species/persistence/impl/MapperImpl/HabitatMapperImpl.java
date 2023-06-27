package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Habitat;
import com.laba.solvd.Species.persistence.HabitatRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;

public class HabitatMapperImpl implements HabitatRepository {
    private SqlSession sqlSession;

    public HabitatMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Habitat habitats) {
        sqlSession.insert("create", habitats);
    }
}

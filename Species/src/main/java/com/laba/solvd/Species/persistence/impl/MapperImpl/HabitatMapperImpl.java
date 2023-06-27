package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Habitat;
import com.laba.solvd.Species.persistence.HabitatRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class HabitatMapperImpl implements HabitatRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public HabitatMapperImpl() {
        this.sqlSession = sqlSession;
    }

    @Override
    public void create(Habitat habitats) {
        try {
            sqlSession.insert("createHabitat", habitats);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}

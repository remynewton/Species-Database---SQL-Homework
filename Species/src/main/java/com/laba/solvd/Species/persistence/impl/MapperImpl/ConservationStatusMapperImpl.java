package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.ConservationStatus;
import com.laba.solvd.Species.persistence.ConservationStatusRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class ConservationStatusMapperImpl implements ConservationStatusRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public ConservationStatusMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(ConservationStatus conservationStatus) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.insert("createConservationStatus", conservationStatus);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}

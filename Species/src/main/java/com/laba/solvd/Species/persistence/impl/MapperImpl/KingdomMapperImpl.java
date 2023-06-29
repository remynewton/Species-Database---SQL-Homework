package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Kingdom;
import com.laba.solvd.Species.persistence.KingdomRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class KingdomMapperImpl implements KingdomRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public KingdomMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Kingdom kingdom) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.insert("createKingdom", kingdom);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
}

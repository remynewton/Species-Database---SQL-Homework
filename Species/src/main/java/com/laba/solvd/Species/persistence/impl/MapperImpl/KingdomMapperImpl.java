package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Kingdom;
import com.laba.solvd.Species.persistence.KingdomRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;

public class KingdomMapperImpl implements KingdomRepository {
    private SqlSession sqlSession;

    public KingdomMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Kingdom kingdom) {
        sqlSession.insert("create", kingdom);
    }
}

package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import com.laba.solvd.Species.persistence.ReferenceRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ReferenceMapperImpl implements ReferenceRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public ReferenceMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Reference reference) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.insert("createReference", reference);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Reference> findByID(int id) {
        sqlSession = MyBatisStarter.getSession();
        Reference obj;
        try {
            obj = sqlSession.selectOne("findByIDReference", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return Optional.of(obj);
    }
}

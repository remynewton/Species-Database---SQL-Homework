package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import com.laba.solvd.Species.persistence.ReferenceRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.Optional;

public class ReferenceMapperImpl implements ReferenceRepository {
    private SqlSession sqlSession;

    public ReferenceMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Reference reference) {
        sqlSession.insert("create", reference);
    }

    @Override
    public Optional<Reference> findByID(int id) {
        return Optional.of(sqlSession.selectOne("findByID", id));
    }
}

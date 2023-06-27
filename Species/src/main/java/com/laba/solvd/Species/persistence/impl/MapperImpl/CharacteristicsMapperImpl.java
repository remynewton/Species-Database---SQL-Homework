package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.persistence.CharacteristicRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Optional;

public class CharacteristicsMapperImpl implements CharacteristicRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public CharacteristicsMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Characteristic characteristic) {
        sqlSession.insert("create", characteristic);
    }

    @Override
    public void update(Characteristic characteristic) {
        sqlSession.update("update", characteristic);
    }

    @Override
    public Optional<Characteristic> findByCategory(String category) {
        Characteristic obj = sqlSession.selectOne("findByCategory", category);
        return Optional.of(obj);
    }

    @Override
    public Optional<Characteristic> findByID(int id) {
        return Optional.of(sqlSession.selectOne("findByID", id));
    }

    @Override
    public void deleteByID(int id) {
        sqlSession.delete("delete", id);
    }
}

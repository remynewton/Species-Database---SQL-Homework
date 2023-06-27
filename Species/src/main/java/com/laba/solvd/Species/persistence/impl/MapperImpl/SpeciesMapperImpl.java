package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.domain.Image;
import com.laba.solvd.Species.domain.Reference;
import com.laba.solvd.Species.domain.Species;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class SpeciesMapperImpl implements SpeciesRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public SpeciesMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();;
    }

    @Override
    public void create(Species species) {
        sqlSession.insert("insert", species);
    }

    @Override
    public List<Species> findAll() {
        return sqlSession.selectList("findAll");
    }

    @Override
    public Optional<Species> findByID(int ID) {
        return Optional.of(sqlSession.selectOne("findByID", ID));
    }

    @Override
    public void update(Species species) {
        sqlSession.update("update", species);
    }

    @Override
    public void deleteByID(int id) {
        sqlSession.delete("deleteByID", id);
    }

    @Override
    public void setReference(Species species, Reference reference) {
        try (SqlSession sqlSession1 = MyBatisStarter.getSession()) {
            SpeciesRepository speciesRepository = sqlSession1.getMapper(SpeciesRepository.class);
            speciesRepository.setReference(species, reference);
            sqlSession1.commit();
        }
    }

    @Override
    public void setCharacteristic(Species species, Characteristic characteristic) {
        try (SqlSession sqlSession1 = MyBatisStarter.getSession()) {
            SpeciesRepository speciesRepository = sqlSession1.getMapper(SpeciesRepository.class);
            speciesRepository.setCharacteristic(species, characteristic);
            sqlSession1.commit();
        }
    }

    @Override
    public void setImage(Species species, Image image) {
        try (SqlSession sqlSession1 = MyBatisStarter.getSession()) {
            SpeciesRepository speciesRepository = sqlSession1.getMapper(SpeciesRepository.class);
            speciesRepository.setImage(species, image);
            sqlSession1.commit();
        }
    }
}

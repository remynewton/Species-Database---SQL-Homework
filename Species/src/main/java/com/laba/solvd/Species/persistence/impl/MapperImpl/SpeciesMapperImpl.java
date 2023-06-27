package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.*;
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
        try {
            sqlSession.insert("createSpecies", species);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Species> findAll() {
        List<Species> obj;
        try {
            obj = sqlSession.selectList("findAllSpecies");
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return obj;
    }

    @Override
    public Optional<Species> findByID(int ID) {
        Species obj;
        try {
            obj = sqlSession.selectOne("findByIDSpecies", ID);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return Optional.of(obj);
    }

    @Override
    public void update(Species species) {
        try {
            sqlSession.update("updateSpecies", species);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteByID(int id) {
        try {
            sqlSession.delete("deleteByIDSpecies", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
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

    @Override
    public void setConservationStatus(Species species, ConservationStatus conservationStatus) {
        try (SqlSession sqlSession1 = MyBatisStarter.getSession()) {
            SpeciesRepository speciesRepository = sqlSession1.getMapper(SpeciesRepository.class);
            speciesRepository.setConservationStatus(species, conservationStatus);
            sqlSession1.commit();
        }
    }
}

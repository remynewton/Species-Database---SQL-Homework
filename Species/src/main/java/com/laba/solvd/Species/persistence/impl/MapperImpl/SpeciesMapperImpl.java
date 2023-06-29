package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.*;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import com.laba.solvd.Species.persistence.SpeciesRepository;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;

public class SpeciesMapperImpl implements SpeciesRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public SpeciesMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Species species) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.insert("createSpecies", species);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Species> findAll() {
        sqlSession = MyBatisStarter.getSession();
        try {
            List<Species> speciesList = sqlSession.selectList("findAllSpecies");
            sqlSession.commit();
            return speciesList;
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Species findByID(int ID) {
        sqlSession = MyBatisStarter.getSession();
        Species species;
        try {
            species = sqlSession.selectOne("findByIDSpecies", ID);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return species;
    }

    @Override
    public void update(Species species) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.update("updateSpecies", species);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void deleteByID(int id) {
        sqlSession = MyBatisStarter.getSession();
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
        SqlSession sqlSession1 = MyBatisStarter.getSession();
        try {
            SpeciesRepository speciesRepository = sqlSession1.getMapper(SpeciesRepository.class);
            speciesRepository.setCharacteristic(species, characteristic);
            sqlSession1.commit();
        } finally {
            sqlSession1.close();
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

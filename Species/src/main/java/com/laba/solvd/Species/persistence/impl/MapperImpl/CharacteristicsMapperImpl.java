package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.persistence.CharacteristicRepository;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Optional;

public class CharacteristicsMapperImpl implements CharacteristicRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public CharacteristicsMapperImpl() {
        this.sqlSession = sqlSession;
    }

    @Override
    public void create(Characteristic characteristic) {
        try {
            sqlSession.insert("createCharacteristic", characteristic);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

        @Override
        public void update (Characteristic characteristic){
            try {
                sqlSession.update("updateCharacteristic", characteristic);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
        }

        @Override
        public Optional<Characteristic> findByCategory (String category) {
            Characteristic obj;
            try {
                obj = sqlSession.selectOne("findByCategoryCharacteristic", category);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
            return Optional.of(obj);
        }

        @Override
        public Optional<Characteristic> findByID ( int id){
            Characteristic obj;
            try {
                obj = sqlSession.selectOne("findByIDCharacteristic", id);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
            return Optional.of(obj);
        }

        @Override
        public void deleteByID (int id) {
            try {
                sqlSession.delete("deleteCharacteristic", id);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
        }
    }

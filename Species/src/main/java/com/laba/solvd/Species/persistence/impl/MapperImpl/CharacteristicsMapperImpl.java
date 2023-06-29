package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Characteristic;
import com.laba.solvd.Species.persistence.CharacteristicRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CharacteristicsMapperImpl implements CharacteristicRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public CharacteristicsMapperImpl() {
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Characteristic characteristic) {
        sqlSession = MyBatisStarter.getSession();
        try {
            sqlSession.insert("createCharacteristic", characteristic);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

        @Override
        public void update (Characteristic characteristic){
            sqlSession = MyBatisStarter.getSession();
            try {
                sqlSession.update("updateCharacteristic", characteristic);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
        }

        @Override
        public List<Characteristic> findByCategory (String category) {
            sqlSession = MyBatisStarter.getSession();
            List<Characteristic> characteristics;
            try {
                characteristics = sqlSession.selectList("findByCategoryCharacteristic", category);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
            return characteristics;
        }

        @Override
        public Optional<Characteristic> findByID ( int id){
            sqlSession = MyBatisStarter.getSession();
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
            sqlSession = MyBatisStarter.getSession();
            try {
                sqlSession.delete("deleteCharacteristic", id);
                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
        }
    }

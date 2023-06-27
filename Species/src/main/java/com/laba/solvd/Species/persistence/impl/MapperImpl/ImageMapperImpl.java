package com.laba.solvd.Species.persistence.impl.MapperImpl;

import com.laba.solvd.Species.domain.Image;
import com.laba.solvd.Species.persistence.ImageRepository;
import com.laba.solvd.Species.persistence.MyBatisStarter;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.util.Optional;

public class ImageMapperImpl implements ImageRepository {
    private final Logger logger = Logger.getLogger("GLOBAL");
    private SqlSession sqlSession;

    public ImageMapperImpl() {
        this.sqlSession = sqlSession;
    }

    @Override
    public void create(Image image) {
        try {
            sqlSession.insert("createImage", image);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public void update(Image image) {
        try {
            sqlSession.update("updateImage", image);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public Optional<Image> findByID(int id) {
        Image obj;
        try {
            obj = sqlSession.selectOne("findByIDImage", id);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
        return Optional.of(obj);
    }
}

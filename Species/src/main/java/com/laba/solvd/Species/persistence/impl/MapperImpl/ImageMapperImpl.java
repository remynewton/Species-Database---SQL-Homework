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
        this.sqlSession = MyBatisStarter.getSession();
    }

    @Override
    public void create(Image image) {
        sqlSession.insert("create", image);
    }

    @Override
    public void update(Image image) {
        sqlSession.update("update", image);
    }

    @Override
    public Optional<Image> findByID(int id) {
        return Optional.of(sqlSession.selectOne("findByID", id));
    }
}

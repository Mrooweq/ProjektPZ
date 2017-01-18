package com.malinki.pz.dal.operations;

import java.io.InputStream;

import com.malinki.pz.lib.entity.MalinkiComplexResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

public abstract class DatabaseComplexResponseOperation <T extends Mapper>  extends DatabaseOperation {
    protected T mapper;
    private Class<T> mapperType;

    public DatabaseComplexResponseOperation(Class<T> mapperType){
        this.mapperType = mapperType;
    }

    public MalinkiComplexResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        mapper = session.getMapper(mapperType);

        MalinkiComplexResponse malinkiComplexResponse;

        malinkiComplexResponse = mainAction();
        session.commit();
        malinkiComplexResponse.setResult(getResultCode());

        session.close();
        closeInputStream(inputStream);

        return malinkiComplexResponse;
    }

    abstract protected MalinkiComplexResponse mainAction();
}

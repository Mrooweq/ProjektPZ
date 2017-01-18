package com.malinki.pz.dal.operations;

import java.io.InputStream;

import com.malinki.pz.lib.entity.MalinkiSimpleResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;

public abstract class DatabaseSimpleResponseOperation <T extends Mapper> extends DatabaseOperation {
    protected T mapper;
    private Class<T> mapperType;

    public DatabaseSimpleResponseOperation(Class<T> mapperType){
        this.mapperType = mapperType;
    }

    public MalinkiSimpleResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        mapper = session.getMapper(mapperType);

        MalinkiSimpleResponse malinkiSimpleResponse;

        malinkiSimpleResponse = mainAction();
        malinkiSimpleResponse.setResult(getResultCode());

        session.close();
        closeInputStream(inputStream);

        return malinkiSimpleResponse;
    }

    abstract protected MalinkiSimpleResponse mainAction();
}

package com.malinki.pz.dal;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.MalinkiComplexResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public abstract class DatabaseComplexResponseOperation <T extends FlightMapper>  extends DatabaseOperation {
    private Logger logger = Logger.getLogger(DatabaseSimpleResponseOperation.class);
    protected T mapper;
    protected DatabaseOperationResultEnum databaseOperationResultEnum;

    Class<T> mapperType;

    public MalinkiComplexResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        mapper = session.getMapper(mapperType);

        MalinkiComplexResponse malinkiComplexResponse;

        malinkiComplexResponse = mainAction();
        malinkiComplexResponse.setResult(getResultCode());

        session.close();
        closeInputStream(inputStream);

        return malinkiComplexResponse;
    }

    abstract protected MalinkiComplexResponse mainAction();
}

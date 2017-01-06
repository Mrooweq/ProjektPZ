package com.malinki.pz.dal;


import com.malinki.pz.dal.constants.Strings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class DatabaseOperation {
    private Logger logger = Logger.getLogger(DatabaseOperation.class);

    protected SqlSession establishSession(InputStream inputStream){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    protected InputStream openInputStream(){
        InputStream inputStream = null;

        try {
            inputStream = Resources.getResourceAsStream(Strings.MYBATIS_CONFIG_FILE_NAME);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return inputStream;
    }

    protected void closeInputStream(InputStream inputStream){
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }
    }

    protected boolean getBoolean(int dual){
        if(dual == 1)
            return true;
        else
            return false;
    }
}

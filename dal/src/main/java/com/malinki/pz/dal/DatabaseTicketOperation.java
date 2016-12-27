package com.malinki.pz.dal;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.TicketDTO;
import com.malinki.pz.lib.UserDTO;
import com.malinki.pz.lib.UserResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.constants.Strings;

public abstract class DatabaseTicketOperation {
    private Logger logger = Logger.getLogger(DatabaseTicketOperation.class);
    protected Mapper mapper;
    protected DatabaseOperationResultEnum databaseOperationResultEnum;

    public int performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        mapper = session.getMapper(Mapper.class);
        int result = 0;

        try {
            mainAction();
            result = setResponse();
        } finally {
            session.close();
            closeInputStream(inputStream);
        }

        return result;
    }

    private SqlSession establishSession(InputStream inputStream){
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory.openSession();
    }

    private InputStream openInputStream(){
        InputStream inputStream = null;

        try {
            inputStream = Resources.getResourceAsStream(Strings.MYBATIS_CONFIG_FILE_NAME);
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }

        return inputStream;
    }

    private int setResponse() {
        int result = 0;

        switch (databaseOperationResultEnum){
            case TICKET_BOUGHT_SUCCESSFULLY:
                logger.log(Level.INFO, Strings.TICKET_BOUGHT_SUCCESSFULLY);
                result = HttpServletResponse.SC_OK;
                break;
            case TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR:
                logger.log(Level.INFO, Strings.TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR);
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;
            default:
                break;
        }

        return result;
    }

    private void closeInputStream(InputStream inputStream){
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }
    }

    abstract protected void mainAction();
}

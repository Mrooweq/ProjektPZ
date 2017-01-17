package com.malinki.pz.dal;


import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.constants.Strings;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public class DatabaseOperation {
    private final Logger logger = Logger.getLogger(DatabaseOperation.class);

    protected DatabaseOperationResultEnum databaseOperationResultEnum;

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

    protected int getResultCode() {
        int result = 0;

        logger.log(Level.INFO, databaseOperationResultEnum.getName());

        switch (databaseOperationResultEnum){
            case CLASSES_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case CLASSES_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            case POSSIBLE_AIRPORTS_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case POSSIBLE_AIRPORTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            case TICKET_BOUGHT_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case TICKET_NOT_BOUGHT_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            case USER_LOGGED_IN_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;
            case USER_LOG_IN_ATTEMPT_FAILED_DUE_TO_WRONG_USERNAME_OR_PASSWORD:
                result = HttpServletResponse.SC_FORBIDDEN;
                break;

            case USER_REGISTERED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case USER_REGISTER_ATTEMPT_FAILED_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;
            case USERNAME_ALREADY_USED:
                result = HttpServletResponse.SC_CONFLICT;
                break;
            case EMAIL_ALREADY_USED:
                result = HttpServletResponse.SC_NOT_ACCEPTABLE;
                break;

            case FLIGTS_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case FLIGTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            case TICKETS_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case TICKETS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            case TICKET_FOR_PDF_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case TICKET_FOR_PDF_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;

            default:
                break;
        }

        return result;
    }
}

package com.malinki.pz.dal;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.ProjektPZResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public abstract class DatabaseAirportOperation extends DatabaseOperation {
    private Logger logger = Logger.getLogger(DatabaseAirportOperation.class);
    protected FlightMapper flightMapper;
    protected DatabaseOperationResultEnum databaseOperationResultEnum;

    public ProjektPZResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        flightMapper = session.getMapper(FlightMapper.class);

        ProjektPZResponse projektPZResponse;

        projektPZResponse = mainAction();
        projektPZResponse.setResult(getResultCode());

        session.close();
        closeInputStream(inputStream);

        return projektPZResponse;
    }


    private int getResultCode() {
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
            default:
                break;
        }

        return result;
    }

    abstract protected ProjektPZResponse mainAction();
}

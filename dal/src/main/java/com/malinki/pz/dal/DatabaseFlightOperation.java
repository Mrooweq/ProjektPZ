package com.malinki.pz.dal;

import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.FlightResponse;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;

public abstract class DatabaseFlightOperation extends DatabaseOperation {
    private Logger logger = Logger.getLogger(DatabaseAirportOperation.class);
    protected FlightMapper flightMapper;
    protected DatabaseOperationResultEnum databaseOperationResultEnum;

    public FlightResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        flightMapper = session.getMapper(FlightMapper.class);

        FlightResponse flightResponse;

        flightResponse = mainAction();
        flightResponse.setResult(getResultCode());

        session.close();
        closeInputStream(inputStream);

        return flightResponse;
    }

    private int getResultCode() {
        int result = 0;

        logger.log(Level.INFO, databaseOperationResultEnum.getName());

        switch (databaseOperationResultEnum){
            case FLIGTS_FETCHED_SUCCESSFULLY:
                result = HttpServletResponse.SC_OK;
                break;
            case FLIGTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR:
                result = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                break;
            default:
                break;
        }

        return result;
    }

    abstract protected FlightResponse mainAction();
}

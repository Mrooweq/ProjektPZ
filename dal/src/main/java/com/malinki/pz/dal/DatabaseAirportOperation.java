package com.malinki.pz.dal;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.malinki.pz.lib.PossibleAirportsResponse;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.dal.constants.Strings;

public abstract class DatabaseAirportOperation {
    private Logger logger = Logger.getLogger(DatabaseAirportOperation.class);
    protected FlightMapper flightMapper;
    protected DatabaseOperationResultEnum databaseOperationResultEnum;

    public PossibleAirportsResponse performAction() {
        InputStream inputStream = openInputStream();
        SqlSession session = establishSession(inputStream);
        flightMapper = session.getMapper(FlightMapper.class);
        int result;
        PossibleAirportsResponse possibleAirportsResponse = new PossibleAirportsResponse();
        List<String> possibleAirportsList;

        try {
            possibleAirportsList = mainAction();
            result = setResponse();
        } finally {
            session.close();
            closeInputStream(inputStream);
        }

        possibleAirportsResponse.setPossibleAirportsList(possibleAirportsList);
        possibleAirportsResponse.setResult(result);

        return possibleAirportsResponse;
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

        logger.log(Level.INFO, databaseOperationResultEnum.getName());

        switch (databaseOperationResultEnum){
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

    private void closeInputStream(InputStream inputStream){
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.log(Level.ERROR, e.toString());
        }
    }

    abstract protected List<String> mainAction();
}

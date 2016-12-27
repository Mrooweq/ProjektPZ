package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseFlightOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfPossibleAirports extends DatabaseFlightOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    private String src;

    public FetchingOfPossibleAirports(String src) {
        this.src = src;
    }

    @Override
    protected List<String> mainAction() {
        List<String> possibleAirports = null;

        try{
            if(src.isEmpty())
                possibleAirports = mapper.getPossibleDestinations();
            else
                possibleAirports = mapper.getPossibleDestinationsWithParam(src);

            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return possibleAirports;
    }
}



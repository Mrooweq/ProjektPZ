package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseAirportOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.ProjektPZResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfPossibleDestinations extends DatabaseAirportOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    private String src;

    public FetchingOfPossibleDestinations(String src) {
        this.src = src;
    }

    @Override
    protected ProjektPZResponse mainAction() {
        List<String> possibleAirports = null;

        try{
            if(src.isEmpty())
                possibleAirports = flightMapper.getPossibleDestinations();
            else
                possibleAirports = flightMapper.getPossibleDestinationsWithParam(src);

            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        ProjektPZResponse projektPZResponse = new ProjektPZResponse();
        projektPZResponse.setResponseList(possibleAirports);

        return projektPZResponse;
    }
}



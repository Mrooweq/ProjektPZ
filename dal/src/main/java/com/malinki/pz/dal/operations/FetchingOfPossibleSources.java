package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseSearcherOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.MalinkiSimpleResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfPossibleSources extends DatabaseSearcherOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    private String dest;

    public FetchingOfPossibleSources(String dest) {
        this.dest = dest;
    }

    @Override
    protected MalinkiSimpleResponse mainAction() {
        List<String> possibleAirports = null;

        try{
            if(dest.isEmpty())
                possibleAirports = flightMapper.getPossibleSources();
            else
                possibleAirports = flightMapper.getPossibleSourcesWithParam(dest);

            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.POSSIBLE_AIRPORTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        MalinkiSimpleResponse malinkiSimpleResponse = new MalinkiSimpleResponse();
        malinkiSimpleResponse.setResponseList(possibleAirports);

        return malinkiSimpleResponse;
    }
}



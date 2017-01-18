package com.malinki.pz.dal.operations.specific;

import com.malinki.pz.dal.operations.DatabaseSimpleResponseOperation;
import com.malinki.pz.dal.mappers.FlightMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.entity.MalinkiSimpleResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfPossibleSources extends DatabaseSimpleResponseOperation {
    private final Logger logger = Logger.getLogger(UserRegistration.class);

    private String dest;

    public FetchingOfPossibleSources(String dest) {
        super(FlightMapper.class);
        this.dest = dest;
    }

    @Override
    protected MalinkiSimpleResponse mainAction() {
        List<String> possibleAirports = null;

        try{
            if(dest.isEmpty())
                possibleAirports = ((FlightMapper)mapper).getPossibleSources();
            else
                possibleAirports = ((FlightMapper)mapper).getPossibleSourcesWithParam(dest);

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



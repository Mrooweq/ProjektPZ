package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseComplexResponseOperation;
import com.malinki.pz.dal.FlightMapper;
import com.malinki.pz.dal.TicketMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.MalinkiComplexResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class FetchingFlights extends DatabaseComplexResponseOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);
    private FlightRequest flightRequest;

    public FetchingFlights(FlightRequest flightRequest){
        super(FlightMapper.class);
        this.flightRequest = flightRequest;
    }

    @Override
    protected MalinkiComplexResponse mainAction() {
        List<FlightDTO> flightDTOResultList;
        MalinkiComplexResponse malinkiComplexResponse = new MalinkiComplexResponse();

        try{
            flightDTOResultList = ((FlightMapper)mapper).getFlights(flightRequest.getFlightToSearchDTO());
            malinkiComplexResponse.setDtoResult(flightDTOResultList);

            databaseOperationResultEnum = DatabaseOperationResultEnum.FLIGTS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.FLIGTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return malinkiComplexResponse;
    }
}

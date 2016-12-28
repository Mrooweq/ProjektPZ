package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseFlightOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.FlightDTO;
import com.malinki.pz.lib.FlightRequest;
import com.malinki.pz.lib.FlightResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class FetchingFlights extends DatabaseFlightOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);
    private FlightRequest flightRequest;

    public FetchingFlights(FlightRequest flightRequest){
        this.flightRequest = flightRequest;
    }

    @Override
    protected FlightResponse mainAction() {
        List<FlightDTO> flightDTOResultList;
        FlightResponse flightResponse = new FlightResponse();

        try{
            flightDTOResultList = flightMapper.getFlights(flightRequest.getFlightToSearchDTO());
            flightResponse.setFlightDTOResultList(flightDTOResultList);

            databaseOperationResultEnum = DatabaseOperationResultEnum.FLIGTS_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.FLIGTS_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        return flightResponse;
    }
}

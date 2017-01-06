package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseAirportOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.ProjektPZResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfClasses extends DatabaseAirportOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    @Override
    protected ProjektPZResponse mainAction() {
        List<String> classesList = null;

        try{
            classesList = flightMapper.getClasses();
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        ProjektPZResponse projektPZResponse = new ProjektPZResponse();
        projektPZResponse.setResponseList(classesList);

        return projektPZResponse;
    }
}



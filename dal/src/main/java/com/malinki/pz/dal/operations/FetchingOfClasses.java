package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseSimpleResponseOperation;
import com.malinki.pz.dal.FlightMapper;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.MalinkiSimpleResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfClasses extends DatabaseSimpleResponseOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    @Override
    protected MalinkiSimpleResponse mainAction() {
        List<String> classesList = null;

        try{
            classesList = ((FlightMapper)mapper).getClasses();
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        MalinkiSimpleResponse malinkiSimpleResponse = new MalinkiSimpleResponse();
        malinkiSimpleResponse.setResponseList(classesList);

        return malinkiSimpleResponse;
    }
}



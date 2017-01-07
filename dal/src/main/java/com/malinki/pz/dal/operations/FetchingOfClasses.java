package com.malinki.pz.dal.operations;

import com.malinki.pz.dal.DatabaseSearcherOperation;
import com.malinki.pz.dal.constants.DatabaseOperationResultEnum;
import com.malinki.pz.lib.MalinkiResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;


public class FetchingOfClasses extends DatabaseSearcherOperation {
    private Logger logger = Logger.getLogger(UserRegistration.class);

    @Override
    protected MalinkiResponse mainAction() {
        List<String> classesList = null;

        try{
            classesList = flightMapper.getClasses();
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_FETCHED_SUCCESSFULLY;
        } catch (Exception e){
            logger.log(Level.ERROR, e.toString());
            databaseOperationResultEnum = DatabaseOperationResultEnum.CLASSES_NOT_FETCHED_SUCCESSFULLY_DUE_TO_ERROR;
        }

        MalinkiResponse malinkiResponse = new MalinkiResponse();
        malinkiResponse.setResponseList(classesList);

        return malinkiResponse;
    }
}



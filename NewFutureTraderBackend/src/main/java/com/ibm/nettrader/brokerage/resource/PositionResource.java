/*
 * (c) 2021 IBM Financial Industry Solutions GmbH, All rights reserved.
 */

package com.ibm.nettrader.brokerage.resource;

import com.ibm.nettrader.brokerage.custom.exceptions.EmptyResponse;
import com.ibm.nettrader.brokerage.utilities.CallStoredProcedure;
import java.sql.SQLException;
import java.util.Optional;
import javax.sql.DataSource;
import org.json.JSONObject;
import org.json.XML;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class PositionResource.Used to get all positions.
 */
@RestController
public class PositionResource {
  private static final Logger logger = LoggerFactory.getLogger(PositionResource.class);

  CallStoredProcedure callStoredProcedure = null;
  
  @Autowired
  private DataSource dataSource;
  
  /**
   * Gets the all positions.
   *
   * @return the all positions
   * @throws SQLException the SQL exception
   */ 
  @GetMapping(value = "/api/positions", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAllPositions() throws SQLException {
    
    logger.info("Entered into positions endpoint...");
    callStoredProcedure = new CallStoredProcedure(dataSource);
    
    // convert XMl to JSON Object
    logger.info("Calling Positions Stored procedure...");
    JSONObject outObject = XML.toJSONObject(
        callStoredProcedure.executeStoredProcedure("POSITION", "GET").toString());
    logger.info("Positions Stored procedure executed successfully.");
    
    // check outXML is empty or not
    if (outObject == null || outObject.isEmpty()) {
      throw new EmptyResponse("Recieved empty positions from databse");
    }
    return outObject.toString();
  }
}

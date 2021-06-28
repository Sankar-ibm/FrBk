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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class is used to to get account details.
 */
@RestController 
public class AccountResource {
  
  /** The Constant logger. */
  private static final Logger logger = LoggerFactory.getLogger(AccountResource.class);
  
  /** The call stored procedure. */
  CallStoredProcedure callStoredProcedure = null;
  
  /** The data source. */
  @Autowired
  private DataSource dataSource;
  
  /**
   * Gets the account.
   *
   * @return the account
   * @throws SQLException the SQL exception
   */
  @GetMapping(value = "/api/account", produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAccount() throws SQLException {

    logger.info("Entered into account endpoint...");
    callStoredProcedure = new CallStoredProcedure(dataSource);
    logger.info("Calling Account Stored procedure...");
    
    // convert XMl to JSON Object
    JSONObject outObject = XML.toJSONObject(
            callStoredProcedure.executeStoredProcedure("ACCOUNT", "GET").toString());
    logger.info("Account Stored procedure executed successfully.");
    
    // check outXML is empty or not
    if (outObject == null || outObject.isEmpty()) {
      throw new EmptyResponse("Recieved empty account from databse");
    }
    return outObject.toString();
  }
}

/*
 * (c) 2021 IBM Financial Industry Solutions GmbH, All rights reserved.
 */

package com.ibm.nettrader.brokerage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The Class GetPostionServiceApplication.
 * This is default SpringBoot Main class.
 */
@SpringBootApplication
public class FutureTraderServiceApplication {

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(FutureTraderServiceApplication.class, args);
  }
}

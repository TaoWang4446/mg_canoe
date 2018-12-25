package com.hworld.canoe.framework.validation;

import java.io.Serializable;

/**
 * <p>
 * This class represents messages while exception occurs. There are several
 * concepts need to be clarified:<br>
 * Surveillance message: describes the exception cause. used internally.<br>
 * Warning message: represents the message to be displayed publicly.
 * </p>
 * @author Raymond Lei Chen
 */
public class SurveillanceFeature implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1986469037326982256L;

  /**
   * Surveillance id
   */
  private String survId;

  /**
   * Surveillance message
   */
  private String survMsg;

  /**
   * Parameters assisting warn message to display
   */
  private Object[] params;

  /**
   * Warn message id
   */
  private String warnMsgId;

  /**
   * text name of HTML element on which the cursor is to be focused.
   */
  private String focusName;

  /**
   * index of the focusName ( element[focusIndex] )
   */
  private int focusIndex;

  /**
   * validateflg
   */
  private boolean validateflg;

  /**
   * Constructor
   */
  public SurveillanceFeature() {
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   */
  public SurveillanceFeature(String survId) {
    this.survId = survId;
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   * @param survMsg String
   */
  public SurveillanceFeature(String survId, String survMsg) {
    this.survId = survId;
    this.survMsg = survMsg;
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   * @param params Object[]
   */
  public SurveillanceFeature(String survId, Object[] params) {
    this.survId = survId;
    this.params = params;
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   * @param params Object[]
   * @param focusName String
   * @param focusIndex int
   * @param validateflg boolean
   */
  public SurveillanceFeature(String survId, Object[] params, String focusName,
      int focusIndex, boolean validateflg) {
    this.survId = survId;
    this.params = params;
    this.focusName = focusName;
    this.focusIndex = focusIndex;
    this.validateflg = validateflg;
  }

  /**
   * Constructor
   * @param survId String
   * @param survMsg String
   * @param params Object[]
   */
  public SurveillanceFeature(String survId, String survMsg, Object[] params) {
    this.survId = survId;
    this.survMsg = survMsg;
    this.params = params;
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   * @param params Object[]
   * @param warnMsgId String
   */
  public SurveillanceFeature(String survId, Object[] params, String warnMsgId) {
    this.survId = survId;
    this.params = params;
    this.warnMsgId = warnMsgId;
    this.validateflg = false;
  }

  /**
   * Constructor
   * @param survId String
   * @param survMsg String
   * @param params Object[]
   * @param warnMsgId String
   */
  public SurveillanceFeature(String survId, String survMsg, Object[] params,
      String warnMsgId) {
    this.survId = survId;
    this.survMsg = survMsg;
    this.params = params;
    this.warnMsgId = warnMsgId;
    this.validateflg = false;
  }

  /**
   * Gets parameters
   * @return Object[]
   */
  public Object[] getParams() {
    return params;
  }

  /**
   * Sets parameters
   * @param params Object[]
   */
  public void setParams(Object[] params) {
    this.params = params;
  }

  /**
   * Gets surveillance id
   * @return String
   */
  public String getSurvId() {
    return survId;
  }

  /**
   * Sets surveillance id
   * @param survId String
   */
  public void setSurvId(String survId) {
    this.survId = survId;
  }

  /**
   * Gets surveillance message
   * @return Sgtring
   */
  public String getSurvMsg() {
    return this.survMsg;
  }

  /**
   * Sets surveillance message
   * @param survMsg String
   */
  public void setSurvMsg(String survMsg) {
    this.survMsg = survMsg;
  }

  /**
   * Gets warn message id
   * @return String
   */
  public String getWarnMsgId() {
    return warnMsgId;
  }

  /**
   * Sets warn message id
   * @param warnMsgId String
   */
  public void setWarnMsgId(String warnMsgId) {
    this.warnMsgId = warnMsgId;
  }

  /**
   * @return the focusName
   */
  public String getFocusName() {
    return focusName;
  }

  /**
   * @param focusName the focusName to set
   */
  public void setFocusName(String focusName) {
    this.focusName = focusName;
  }

  /**
   * @return the index
   */
  public int getFocusIndex() {
    return focusIndex;
  }

  /**
   * @param focusIndex the focusIndex to set
   */
  public void setFocusIndex(int focusIndex) {
    this.focusIndex = focusIndex;
  }

  /**
   * @return the validateflg
   */
  public boolean isValidateflg() {
    return validateflg;
  }

  /**
   * @param validateflg the validateflg to set
   */
  public void setValidateflg(boolean validateflg) {
    this.validateflg = validateflg;
  }

}

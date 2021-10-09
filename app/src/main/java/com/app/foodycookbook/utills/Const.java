package com.app.foodycookbook.utills;

public interface Const {
    String PLEASE_ENETR_VALID_EMAIL_ID = "Please enter valid username";
    String PASSWORD_VALIDATION = " Password should be alphanumeric with at-least 1 number and\n" +
            "1 uppercase character, and length should be\n" +
            "minimum of 8 characters";
    String PLEASE_FILL_THE_ALL_FIELD = "Please fill all the field";
    String PLEASE_WAIT = "Please wait...";
    long EXIT_TIME = 2000;
    int CELL_PHONE_REQUEST = 101;
    String DATE_FORMATE_dd_MM_yyyy = "dd MMM yyyy HH:mm:ss";

    String HEADER_TITLE_KEY = "HEADER_TITLE_KEY";
    String ITEM_POSITION = "ITEM_POSITION";
    String ORDER_TYPE_KEY = "ORDER_TYPE_KEY";
    String TIME_PERIOD_KEY = "TIME_PERIOD_KEY";
    String GET_DISB_PIRCHASE_ORDERS = "GetDisbPurchaseOrders";
    String GET_DISB_PIRCHASE_ORDER = "GetDisbPurchaseOrder";
    String GET_PURCHASE_ORDERS = "GetPurchaseOrders";
    String GET_PURCHASE_ORDER = "GetPurchaseOrder";
    String APPROVE_DISB_PURCHASE_ORDER = "ApproveDisbPurchaseOrder";
    String APPROVE_PURCHASE_ORDER = "ApprovePurchaseOrder";
    String DECLINE_DISB_PURCHASE_ORDER = "DeclineDisbPurchaseOrder";
    String DECLINE_PURCHASE_ORDER = "DeclinePurchaseOrder";

    String PURCHASE_ORDER_TYPE = "DovesNet.Services.API.MobileAPI+PurchaseOrder";
    String DISP_PURCHASE_ORDER_TYPE = "DovesNet.Services.API.MobileAPI+DisbPurchaseOrder";
    String ORDER_ID_KEY = "ORDER_ID_KEY";
    String EMPTY = "";
    String user_not_authenticated = "User not authenticated";
    String session_expired_please_login_again = "Session expired please login again.";
    int all = 0;
    int operational = 1;
    int disbursement = 5;
    String APPROVED = "Approved";
    String DECLINED = "Declined";
    String authenticated_failed = "Authentication failed.";
}

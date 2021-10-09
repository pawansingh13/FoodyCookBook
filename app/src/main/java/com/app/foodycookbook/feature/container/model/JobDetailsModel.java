package com.app.foodycookbook.feature.container.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobDetailsModel implements Serializable {
    @SerializedName("responseModel")
    @Expose
    private ResponseModel responseModel;
    @SerializedName("statusCode")
    @Expose
    private Integer statusCode;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("description")
    @Expose
    private String description;

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public class ResponseModel implements Serializable {

        @SerializedName("chatConnectionUrl")
        @Expose
        private String chatConnectionUrl;
        @SerializedName("chatChannelName")
        @Expose
        private String chatChannelName;

        public String getGeekName() {
            return geekName;
        }

        public void setGeekName(String geekName) {
            this.geekName = geekName;
        }

        @SerializedName("geekName")
        @Expose
        private String geekName;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("customerPay")
        @Expose
        private String customerPay;
        @SerializedName("salesTax")
        @Expose
        private String salesTax;

        public String getBreakReturnTime() {
            return breakReturnTime;
        }

        public void setBreakReturnTime(String breakReturnTime) {
            this.breakReturnTime = breakReturnTime;
        }

        public String breakReturnTime;

        @SerializedName("jobRequestId")
        @Expose
        private Integer jobRequestId;
        @SerializedName("orderId")
        @Expose
        private Integer orderId;
        @SerializedName("hours")
        @Expose
        private Integer hours;
        @SerializedName("minutes")
        @Expose
        private Integer minutes;
        @SerializedName("statusOfJob")
        @Expose
        private Integer statusOfJob;
        @SerializedName("haveOngoingJob")
        @Expose
        private Boolean haveOngoingJob;
        @SerializedName("estimatedHoursByGeek")
        @Expose
        private String estimatedHoursByGeek;
        @SerializedName("estimatedMinutesByGeek")
        @Expose
        private String estimatedMinutesByGeek;
        @SerializedName("flatRateByGeek")
        @Expose
        private String flatRateByGeek;
        @SerializedName("estimatedTotalTime")
        @Expose
        private String estimatedTotalTime;
        @SerializedName("typeOfEstimates")
        @Expose
        private Integer typeOfEstimates;

        public String getCustomerPay() {
            return customerPay;
        }

        public String getChatConnectionUrl() {
            return chatConnectionUrl;
        }

        public void setChatConnectionUrl(String chatConnectionUrl) {
            this.chatConnectionUrl = chatConnectionUrl;
        }

        public String getChatChannelName() {
            return chatChannelName;
        }

        public void setChatChannelName(String chatChannelName) {
            this.chatChannelName = chatChannelName;
        }

        public void setCustomerPay(String customerPay) {
            this.customerPay = customerPay;
        }

        public String getSalesTax() {
            return salesTax;
        }

        public void setSalesTax(String salesTax) {
            this.salesTax = salesTax;
        }

        public Integer getJobRequestId() {
            return jobRequestId;
        }

        public void setJobRequestId(Integer jobRequestId) {
            this.jobRequestId = jobRequestId;
        }

        public Integer getOrderId() {
            return orderId;
        }

        public void setOrderId(Integer orderId) {
            this.orderId = orderId;
        }

        public Integer getHours() {
            return hours;
        }


        public void setHours(Integer hours) {
            this.hours = hours;
        }

        public Integer getMinutes() {
            return minutes;
        }

        public void setMinutes(Integer minutes) {
            this.minutes = minutes;
        }

        public Integer getStatusOfJob() {
            return statusOfJob;
        }

        public void setStatusOfJob(Integer statusOfJob) {
            this.statusOfJob = statusOfJob;
        }

        public Boolean getHaveOngoingJob() {
            return haveOngoingJob;
        }

        public void setHaveOngoingJob(Boolean haveOngoingJob) {
            this.haveOngoingJob = haveOngoingJob;
        }

        public String getEstimatedHoursByGeek() {
            return estimatedHoursByGeek;
        }

        public void setEstimatedHoursByGeek(String estimatedHoursByGeek) {
            this.estimatedHoursByGeek = estimatedHoursByGeek;
        }

        public String getEstimatedMinutesByGeek() {
            return estimatedMinutesByGeek;
        }

        public void setEstimatedMinutesByGeek(String estimatedMinutesByGeek) {
            this.estimatedMinutesByGeek = estimatedMinutesByGeek;
        }

        public String getFlatRateByGeek() {
            return flatRateByGeek;
        }

        public void setFlatRateByGeek(String flatRateByGeek) {
            this.flatRateByGeek = flatRateByGeek;
        }

        public String getEstimatedTotalTime() {
            return estimatedTotalTime;
        }

        public void setEstimatedTotalTime(String estimatedTotalTime) {
            this.estimatedTotalTime = estimatedTotalTime;
        }

        public Integer getTypeOfEstimates() {
            return typeOfEstimates;
        }

        public void setTypeOfEstimates(Integer typeOfEstimates) {
            this.typeOfEstimates = typeOfEstimates;
        }

    }
}

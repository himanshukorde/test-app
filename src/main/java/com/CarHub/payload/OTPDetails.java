package com.CarHub.payload;

public class OTPDetails {

    public String getOtp() {
        return otp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    private final String otp;
    private final long timestamp;

    public OTPDetails(String otp, long timestamp) {
        this.otp = otp;
        this.timestamp = timestamp;
    }
}

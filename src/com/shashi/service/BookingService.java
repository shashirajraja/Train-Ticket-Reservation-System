package com.shashi.service;

import java.util.List;

import com.shashi.beans.HistoryBean;
import com.shashi.beans.TrainException;

public interface BookingService {

	public List<HistoryBean> getAllBookingsByCustomerId(String customerEmailId) throws TrainException;

	public HistoryBean createHistory(HistoryBean bookingDetails) throws TrainException;

}

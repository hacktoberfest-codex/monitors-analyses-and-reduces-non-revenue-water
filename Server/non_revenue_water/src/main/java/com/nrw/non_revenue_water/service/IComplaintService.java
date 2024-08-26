package com.nrw.non_revenue_water.service;

import java.util.List;

import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.model.Complaint;

public interface IComplaintService {

    List<Complaint> getAllComplaints();

    Complaint updateComplaint(Complaint complaint);

    Complaint registerComplaint(Complaint complaint, Account account);

    List<Complaint> getComplaintStatus(long accountNumber);
}

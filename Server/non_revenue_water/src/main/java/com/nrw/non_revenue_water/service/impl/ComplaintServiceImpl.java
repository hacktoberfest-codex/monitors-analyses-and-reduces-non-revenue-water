package com.nrw.non_revenue_water.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.constant.ComplaintStatus;
import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.model.Complaint;
import com.nrw.non_revenue_water.repository.AccountRepository;
import com.nrw.non_revenue_water.repository.ComplaintRepository;
import com.nrw.non_revenue_water.service.IComplaintService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ComplaintServiceImpl implements IComplaintService {
    private final ComplaintRepository complaintRepository;
    private final AccountRepository accountRepository;

    @Override
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint updateComplaint(Complaint complaint) {
        var existingComplaint = complaintRepository.findByAccountId(complaint.getAccountId()).orElseThrow();
        existingComplaint.setAccountId(complaint.getAccountId());
        existingComplaint.setAccountHolderName(complaint.getAccountHolderName());
        existingComplaint.setAccountEmail(complaint.getAccountEmail());
        existingComplaint.setComplaintDescription(complaint.getComplaintDescription());
        existingComplaint.setComplaintStatus(complaint.getComplaintStatus());
        complaintRepository.save(existingComplaint);
        return existingComplaint;
    }

    @Override
    public Complaint registerComplaint(Complaint complaint, Account account) {
        complaint.setAccount(account);
        complaint.setComplaintStatus(ComplaintStatus.PENDING);
        complaintRepository.save(complaint);
        return complaint;
    }

    @Override
    public List<Complaint> getComplaintStatus(long accountNumber) {
        var account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        return complaintRepository.findByAccount(account);
    }

}

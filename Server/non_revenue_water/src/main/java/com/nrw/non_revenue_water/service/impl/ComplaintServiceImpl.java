package com.nrw.non_revenue_water.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nrw.non_revenue_water.constant.AccountType;
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
        int numberOfComplaint = account.getNumberOfComplaints();//Get the no. of complaints of the user
        account.setNumberOfComplaints(numberOfComplaint + 1);//Update the number of complaints by 1
        accountRepository.save(account);//Save the changes

        Account adminAccount = accountRepository.findByAccountType(AccountType.ADMIN).orElseThrow();//Get the admin account from db
        int numberOfComplaintAdmin = adminAccount.getNumberOfComplaints();//Get the no. of complaints of the ADMIN
        adminAccount.setNumberOfComplaints(numberOfComplaintAdmin + 1);//Update the number of complaints by 1
        accountRepository.save(adminAccount);//Save the changes

        complaint.setAccount(account);//Map the complaint to the user
        complaint.setComplaintStatus(ComplaintStatus.PENDING);//Initially set the staus of the complaints to PENDING
        complaintRepository.save(complaint);
        return complaint;
    }

    @Override
    public List<Complaint> getComplaintStatus(long accountNumber) {
        var account = accountRepository.findByAccountNumber(accountNumber).orElseThrow();
        return complaintRepository.findByAccount(account);
    }

}

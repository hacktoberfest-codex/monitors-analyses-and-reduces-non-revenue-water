package com.nrw.non_revenue_water.mapper;

import org.springframework.beans.BeanUtils;

import com.nrw.non_revenue_water.dto.ComplaintRequestDTO;
import com.nrw.non_revenue_water.dto.ComplaintResponseDTO;
import com.nrw.non_revenue_water.dto.ComplaintUpdateDTO;
import com.nrw.non_revenue_water.model.Complaint;

public class ComplaintMapper {
    ComplaintMapper() {

    }

    // Update complaint from admin
    public static Complaint modelMapper(ComplaintUpdateDTO complaintdto) {
        var complaint = new Complaint();
        BeanUtils.copyProperties(complaintdto, complaint);
        return complaint;

    }

    // Regsiter complaint for user
    public static Complaint modelMapperComplaint(ComplaintRequestDTO complaintRequest) {
        var complaint = new Complaint();
        BeanUtils.copyProperties(complaintRequest, complaint);

        return complaint;
    }

    // Complaint response to client
    public static ComplaintResponseDTO dtoMapper(Complaint complaint) {
        return new ComplaintResponseDTO(complaint.getAccountId(), complaint.getAccountHolderName(),
                complaint.getAccountEmail(),
                complaint.getComplaintDescription(),
                complaint.getComplaintStatus());
    }
}

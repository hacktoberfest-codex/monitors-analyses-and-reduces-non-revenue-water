package com.nrw.non_revenue_water.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nrw.non_revenue_water.dto.AccountRequestDTO;
import com.nrw.non_revenue_water.dto.AccountResponseDTO;
import com.nrw.non_revenue_water.dto.AdminAccountResponseDTO;
import com.nrw.non_revenue_water.dto.ComplaintRequestDTO;
import com.nrw.non_revenue_water.dto.ComplaintResponseDTO;
import com.nrw.non_revenue_water.dto.ComplaintUpdateDTO;
import com.nrw.non_revenue_water.dto.TokenDTO;
import com.nrw.non_revenue_water.mapper.AccountMapper;
import com.nrw.non_revenue_water.mapper.ComplaintMapper;
import com.nrw.non_revenue_water.model.Credential;
import com.nrw.non_revenue_water.service.IAccountService;
import com.nrw.non_revenue_water.service.IComplaintService;
import com.nrw.non_revenue_water.service.IJWTService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final IAccountService accountService;
    private final IJWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final IComplaintService complaintService;

    // User signup
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO dto) {
        var account = AccountMapper.modelMapper(dto);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    // Admin signup
    @PostMapping("/adminregister")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminAccountResponseDTO createAccountAdmin(@Valid @RequestBody AccountRequestDTO dto) {
        var account = AccountMapper.modelMapperAdmin(dto);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapperAdmin(result);
    }

    // Both user and admin login
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public TokenDTO accountByEmailAndPassword(@RequestBody Credential credential) {
        var auth = new UsernamePasswordAuthenticationToken(credential.getAccountEmail(),
                credential.getAccountPassword());
        authenticationManager.authenticate(auth);

        var account = accountService.getAccountByEmail(credential.getAccountEmail());
        var token = jwtService.generateToken(String.valueOf(account.getAccountNumber()));
        return new TokenDTO(token);

    }

    // Complaints view for admin
    @GetMapping("/complaint")
    public List<ComplaintResponseDTO> complaintDetails(@RequestAttribute long accountNumber) {
        var result = complaintService.getAllComplaints();
        var complaints = result.stream().map(ComplaintMapper::dtoMapper).toList();
        return complaints;
    }

    // Complaint update by admin
    @PutMapping("/updatecomplaint")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ComplaintResponseDTO updateComplaint(@Valid @RequestBody ComplaintUpdateDTO complaintdto) {
        var complaint = ComplaintMapper.modelMapper(complaintdto);
        var updateComplaint = complaintService.updateComplaint(complaint);
        return ComplaintMapper.dtoMapper(updateComplaint);

    }

    // Register complaint for user and update the number of complaints of the user and admin
    @PostMapping("/registercomplaint")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ComplaintResponseDTO registerComplaint(@Valid @RequestBody ComplaintRequestDTO complaintRequest) {
        var complaint = ComplaintMapper.modelMapperComplaint(complaintRequest);
        var email = complaint.getAccountEmail();
        var account = accountService.getAccountByEmail(email); // Check if the user exists or not
        var res = complaintService.registerComplaint(complaint, account);
        return ComplaintMapper.dtoMapper(res);
    }

    // Complaint status view for user
    @GetMapping("/complaintstatus")
    public List<ComplaintResponseDTO> complaintStatus(@RequestAttribute long accountNumber) {
        var result = complaintService.getComplaintStatus(accountNumber);
        var complaints = result.stream().map(ComplaintMapper::dtoMapper).toList();
        return complaints;
    }

    // Account Details
    @GetMapping("/current")
    public AccountResponseDTO currentAccountByNumber(@RequestAttribute long accountNumber) {
        var result = accountService.getAccount(accountNumber);
        return AccountMapper.dtoMapper(result);
    }

    // Upload image
    @PostMapping("/image")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountResponseDTO uploadPicture(@RequestAttribute long accountNumber,
            @RequestParam("file") MultipartFile file) throws Exception {
        if (file.isEmpty())
            throw new RuntimeException("Image not found");

        var result = accountService.uploadProfilePicture(accountNumber, file);
        return AccountMapper.dtoMapper(result);
    }

    // Get profile picture
    @GetMapping(value = "/{accountNumber}/image", produces = { MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE })
    public byte[] getImage(@PathVariable long accountNumber) throws Exception {
        return accountService.getProfilePicture(accountNumber);
    }

    // Deposit amount for user
    @PatchMapping("/deposit/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@RequestAttribute long accountNumber, @PathVariable double balance) {
        accountService.depositBalance(accountNumber, balance);
    }

}

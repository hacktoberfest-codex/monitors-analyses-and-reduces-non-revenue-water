package com.nrw.non_revenue_water.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nrw.non_revenue_water.dto.AccountRequestDTO;
import com.nrw.non_revenue_water.dto.AccountResponseDTO;
import com.nrw.non_revenue_water.dto.AdminAccountResponseDTO;
import com.nrw.non_revenue_water.dto.TokenDTO;
import com.nrw.non_revenue_water.mapper.AccountMapper;
import com.nrw.non_revenue_water.model.Credential;
import com.nrw.non_revenue_water.service.IAccountService;
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

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountResponseDTO createAccount(@Valid @RequestBody AccountRequestDTO dto) {
        var account = AccountMapper.modelMapper(dto);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapper(result);
    }

    @PostMapping("/adminregister")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminAccountResponseDTO createAccountAdmin(@Valid @RequestBody AccountRequestDTO dto) {
        var account = AccountMapper.modelMapperAdmin(dto);
        var result = accountService.createAccount(account);
        return AccountMapper.dtoMapperAdmin(result);
    }

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

}

package com.nrw.non_revenue_water.mapper;

import org.springframework.beans.BeanUtils;

import com.nrw.non_revenue_water.dto.AccountRequestDTO;
import com.nrw.non_revenue_water.dto.AccountResponseDTO;
import com.nrw.non_revenue_water.dto.AdminAccountResponseDTO;
import com.nrw.non_revenue_water.model.Account;
import com.nrw.non_revenue_water.model.Address;
import com.nrw.non_revenue_water.model.AdminAccount;
import com.nrw.non_revenue_water.model.Credential;

public class AccountMapper {
    private AccountMapper() {
    }

    public static Account modelMapper(AccountRequestDTO dto) {
        var credential = new Credential();
        BeanUtils.copyProperties(dto, credential);

        var address = new Address();
        BeanUtils.copyProperties(dto, address);

        var account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setCredential(credential);
        account.setAddress(address);

        return account;
    }

    public static Account modelMapperAdmin(AccountRequestDTO dto) {
        var credential = new Credential();
        BeanUtils.copyProperties(dto, credential);

        var address = new Address();
        BeanUtils.copyProperties(dto, address);

        var admin = new AdminAccount();
        BeanUtils.copyProperties(dto, admin);

        var account = new Account();
        BeanUtils.copyProperties(dto, account);
        account.setCredential(credential);
        account.setAddress(address);
        account.setAdminAccount(admin);

        return account;
    }

    public static AccountResponseDTO dtoMapper(Account result) {
        var credential = result.getCredential();
        var address = result.getAddress() == null ? new Address() : result.getAddress();

        return new AccountResponseDTO(result.getAccountNumber(),
                result.getAccountHolderName(),
                result.getAboutCustomer(),
                result.getContactNumber(),
                credential.getAccountEmail(),
                result.getAccountBalance(),
                result.getNumberOfComplaints(),
                result.getAccountType(),
                address.getCity(),
                address.getState(),
                address.getArea(),
                address.getCountry(),
                address.getZipcode());

    }

    public static AdminAccountResponseDTO dtoMapperAdmin(Account account) {
        var credential = account.getCredential();
        var admin = account.getAdminAccount();

        return new AdminAccountResponseDTO(account.getAccountNumber(),
                account.getAccountHolderName(),
                account.getAboutCustomer(),
                account.getContactNumber(),
                credential.getAccountEmail(),
                account.getAccountBalance(),
                account.getNumberOfComplaints(),
                admin.getTodaysRevenue(),
                admin.getTotalUsers(),
                account.getAccountType());

    }

}

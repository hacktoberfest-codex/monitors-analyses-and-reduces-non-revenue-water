import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAccount } from '../model/user_account';
export const BASE_URL = 'http://localhost:1200/water/api/v1/accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  http = inject(HttpClient);
  noauth = { headers: { "noauth": "noauth" } };

  constructor() { }
  
  createAccount(account: any) {
    return this.http.post(BASE_URL, account, this.noauth);
  }

  loginAccount(account: any) {
    return this.http.post(BASE_URL + "/login", account, this.noauth);
  }

  depositBalance(balance: any) {

    return this.http.patch(BASE_URL + "/deposit/" + balance, {});
  }

  withdrawalBalance(balance: any) {
    return this.http.patch(BASE_URL + "/withdrawal/" + balance, {});
  }

  transferBalance(balance: any, reciever: any) {
    return this.http.patch(BASE_URL + "/transfer/" + reciever + "/balance/" + balance, {});
  }

  getCurrentAccount() {
    return this.http.get<UserAccount>(BASE_URL + "/current");
  }

  updateAccount(account: UserAccount) {
    const accountNumber = account.accountNumber;
    return this.http.put<UserAccount>(`${BASE_URL}/${accountNumber}`, account);
  }

  uploadImage(file: File) {
    const formData = new FormData();
    formData.append("file", file);
    return this.http.post<UserAccount>(BASE_URL + "/image", formData);
  }
}

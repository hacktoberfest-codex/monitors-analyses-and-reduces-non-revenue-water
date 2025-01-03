import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAccount } from '../model/user_account';
import { environment } from 'src/environments/environment.development';
import { Complaint } from '../model/complaint';
import { ContactUs } from '../model/contact';

export const BASE_URL = environment.base_url + '/accounts';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  http = inject(HttpClient);
  noauth = { headers: { "noauth": "noauth" } };

  constructor() { }

  createAccount(account: any) {
    return this.http.post(BASE_URL + "/register", account, this.noauth);
  }

  createAccountAdmin(account: any) {
    return this.http.post(BASE_URL + "/adminregister", account, this.noauth);
  }

  loginAccount(account: any) {
    return this.http.post(BASE_URL + "/login", account, this.noauth);
  }

  registerComplaint(complaint: Complaint) {
    return this.http.post(BASE_URL + "/registercomplaint", complaint)
  }

  getComplaintStatus() {
    return this.http.get(BASE_URL + "/complaintstatus")
  }

  getAllComplaints() {
    return this.http.get<Complaint>(BASE_URL + "/complaint")
  }

  updateComplaint(complaint: Complaint) {
    return this.http.put<Complaint>(BASE_URL + "/updatecomplaint", complaint)
  }

  contactUserForm(contact: ContactUs) {
    return this.http.post(BASE_URL + "/contactus", contact)
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

  setImage(account: UserAccount) {
    const date = new Date();
    return this.http.get(BASE_URL + "/" + account.accountNumber +
      "/image?r=" + date.getTime())
  }
}

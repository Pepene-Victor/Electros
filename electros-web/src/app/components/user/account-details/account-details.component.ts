import {Component, OnInit} from '@angular/core';
import {UserControllerService} from "../../../api/services/user-controller.service";
import {Subscription} from "rxjs";
import {AdministratorControllerService} from "../../../api/services/administrator-controller.service";
import {CustomerControllerService} from "../../../api/services/customer-controller.service";
import {AdministratorDto} from "../../../api/models/administrator-dto";
import {CustomerDto} from "../../../api/models/customer-dto";


@Component({
  selector: 'app-account-details',
  templateUrl: './account-details.component.html',
  styleUrls: ['./account-details.component.scss']
})
export class AccountDetailsComponent implements OnInit {
  name?: string;
  email?: string;
  company?: string;
  personalPhoneNumber?: string;
  isAdmin: boolean = false
  private _subscriptions: Subscription[] = [];

  constructor(private _userService: UserControllerService,
              private _adminService: AdministratorControllerService,
              private _customerService: CustomerControllerService) {
  }

  ngOnInit(): void {
    let loggedAccount = this._userService.getLoggedAccount();
    if(loggedAccount.role == "ADMINISTRATOR"){
      this.isAdmin = true;
      this._subscriptions.push(this._adminService.findByUserUsingGET(loggedAccount.username).subscribe({
        next: (admin: AdministratorDto) => {
          this.name = admin.firstName + " " + admin.lastName;
          this.email = admin.email;
          this.company = admin.company;
          this.personalPhoneNumber = admin.personalPhoneNumber;
        }
      }));
    }else{
      this._subscriptions.push(this._customerService.findByUserUsingGET1(loggedAccount.username).subscribe({
        next: (customer: CustomerDto) => {
          this.name = customer.name
          this.email = customer.email
        }
      }))
    }

  }

  changeEmail(){
    this._userService.setAccountDetailsType('email');
  }

  changePassword(){
    this._userService.setAccountDetailsType('password');
  }
}

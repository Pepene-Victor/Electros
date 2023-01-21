import { Component, OnInit } from '@angular/core';
import {Subscription, switchMap} from "rxjs";
import {FormBuilder, FormControlStatus, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserControllerService} from "../../../api/services/user-controller.service";
import {User} from "../../../api/models/user";
import {AuthService} from "../../../api/services/auth.service";
import {UserDto} from "../../../api/models/user-dto";
import {ErrorMessage} from "@angular/compiler-cli/ngcc/src/execution/cluster/api";
import {AdministratorDto} from "../../../api/models/administrator-dto";
import {AdministratorControllerService} from "../../../api/services/administrator-controller.service";
import {CustomerControllerService} from "../../../api/services/customer-controller.service";
import {CustomerDto} from "../../../api/models/customer-dto";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent implements OnInit {
  private _subscriptions: Subscription[] = [];
  private _currentUserDetails!: User;
  private _newUserDetails?: User;
  userForm!: FormGroup;
  adminForm!: FormGroup;
  customerForm!: FormGroup;
  subscription?: Subscription;
  isAdmin: boolean = false;
  showError: string = "";
  formValidation: string = 'INVALID';
  accountDetailsType?: string;


  constructor(private _fb: FormBuilder,
              private _router: Router,
              private _userService: UserControllerService,
              private _authService: AuthService,
              private _adminService: AdministratorControllerService,
              private _customerService: CustomerControllerService) {
    this._createUserForm();
    this._createAdminForm();
    this._createCustomerForm();
  }

  ngOnInit(): void {
    let loggedAccount: User = this._userService.getLoggedAccount();
    this.accountDetailsType = this._userService.getAccountDetailsType();
    this._currentUserDetails = loggedAccount;
    this.isAdmin = this._userService.getIsAdmin();
  }

  ngOnDestroy(): void {
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }

  create() {
    if(this._userService.getIsLoggedIn() && this._userService.getIsAdmin()){
      const user: UserDto = {
        username: this.userForm.controls.username.value,
        password: this.userForm.controls.password.value,
        passwordStatus: "OK",
        role: "ADMINISTRATOR"
      };
      const entity: AdministratorDto = {
        firstName: this.adminForm.controls.fisrtName.value,
        lastName: this.adminForm.controls.lastName.value,
        company: this.customerForm.controls.company.value,
        status: "FREE",
        personalPhoneNumber: this.customerForm.controls.personalPhoneNumber.value,
      };
      const username = user.username;
      const paramsAdmin ={
        entity,
        username
      }
      this._subscriptions.push(this._userService.createUsingPOST5(user).pipe(
        switchMap(() => this._adminService.createUsingPOST1(paramsAdmin))
      ).subscribe({
        next: () => {
          console.log("Register success!");
          this._router.navigate(['/home'])
            .then(() => {
              window.location.reload();
            });
        },
        error: (error: ErrorMessage) => {
          {
            this.showError = error.error
          }
        }
      }));
    }else{
      const user: UserDto = {
        username: this.userForm.controls.username.value,
        password: this.userForm.controls.password.value,
        passwordStatus: "OK",
        role: "CUSTOMER"
      };
      const entity: CustomerDto = {
        name: this.customerForm.controls.name.value,
        gender: this.customerForm.controls.gender.value,
        status: "FREE",
        age: this.customerForm.controls.age.value,
      };
      const username = this.userForm.controls.username.value;
      const paramsCustomer ={
        entity,
        username
      }
      this._subscriptions.push(this._userService.createUsingPOST5(user).pipe(
        switchMap(() => this._customerService.createUsingPOST2(paramsCustomer))
      ).subscribe({
        next: () => {
          console.log("Register success!");
          this._router.navigate(['/home'])
            .then(() => {
              window.location.reload();
            });
        },
        error: (error: ErrorMessage) => {
          {
            this.showError = error.error
          }
        }
      }));
    }

  }

  save() {
      if (this.accountDetailsType == 'email') {
      const user: UserDto = {

        username: this.userForm.controls.username.value
      }
      this._newUserDetails = Object.assign(this._currentUserDetails, user);

      if(this._userService.getIsAdmin()){
        let admin: AdministratorDto = this._userService.getAccountDetailsAdmin();
        admin.email = this.userForm.controls.username.value;
        this._subscriptions.push(this._adminService.updateUsingPUT1(admin).subscribe());
      }else{
        let customer: CustomerDto = this._userService.getAccountDetailsCustomer();
        customer.email = this.userForm.controls.username.value;
        this._subscriptions.push(this._customerService.updateUsingPUT2(customer).subscribe());
      }
      this._subscriptions.push(this._userService.updateUsernameUsingPUT(this._newUserDetails).pipe(
        switchMap(() => this._authService.logout())
      ).subscribe({
        next: () => {
          localStorage.clear();
          alert('Email has been changed !');
          this._reloadLoginPage();
        },
        error: (error: ErrorMessage) => {
          {
            this.showError = error.error
          }
        }
      }));
    } else if(this.accountDetailsType == 'password') {
      const user: User = {
        password: this.userForm.controls.password.value
      }
      this._newUserDetails = Object.assign(this._currentUserDetails, user);
      this._subscriptions.push(this._userService.updatePasswordUsingPUT(this._newUserDetails).pipe(
        switchMap(() => this._authService.logout())
      ).subscribe({
        next: () => {
          localStorage.clear();
          alert('Password has been changed !');
          this._reloadLoginPage();
        },
        error: (error: ErrorMessage) => {
          {
            this.showError = error.error
          }
        }
      }));
    }else{
        if(this._userService.getIsAdmin()){
          let admin: AdministratorDto = this._userService.getAccountDetailsAdmin();
          admin.email = this.userForm.controls.username.value;
          this._subscriptions.push(this._adminService.updateUsingPUT1(admin).subscribe());
        }else{
          let customer: CustomerDto = this._userService.getAccountDetailsCustomer();
          this._subscriptions.push(this._customerService.updateUsingPUT2(customer).subscribe());
        }

      }
  }

  private _reloadLoginPage(){
    this._router.navigate(['/login'])
      .then(() => {
        window.location.reload();
      });
  }

  private _createAdminForm() {
    this.adminForm = this._fb.group({
      firstName: [null,
        [Validators.pattern("^[A-Za-z]*$"),
          Validators.minLength(5),
          Validators.maxLength(50)]],
      lastName: [null,
        [Validators.pattern("^[A-Za-z]*$"),
          Validators.minLength(5),
          Validators.maxLength(50)]],
      company: [null],
      personalPhoneNumber: [null],
    })
    this._subscriptions.push(
      this.adminForm.statusChanges.subscribe((value: FormControlStatus) => {
        this.formValidation = value;
      })
    );
  }
  private _createCustomerForm() {
    this.customerForm = this._fb.group({
      name: [null,
        [Validators.pattern("^[A-Za-z0-9]*$"),
          Validators.minLength(5),
          Validators.maxLength(50)]],
      gender: [null],
      age:[null],
    })
    this._subscriptions.push(
      this.customerForm.statusChanges.subscribe((value: FormControlStatus) => {
        this.formValidation = value;
      })
    );
  }
  private _createUserForm() {
    this.userForm = this._fb.group({
      username: [null,
        [Validators.minLength(5),
          Validators.maxLength(50),
          Validators.email]],
      password: [null,
        [Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[a-zA-z0-9@$!%*?&]*$"),
          Validators.minLength(8),
          Validators.maxLength(20)]],
      confirmPassword: [null],
      role: [null]
    })
    this._subscriptions.push(
      this.userForm.statusChanges.subscribe((value: FormControlStatus) => {
        this.formValidation = value;
      })
    );
  }
}


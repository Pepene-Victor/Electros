import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {ProductDto} from "../../../api/models/product-dto";
import {FormBuilder} from "@angular/forms";
import {ProductControllerService} from "../../../api/services/product-controller.service";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {UserControllerService} from "../../../api/services/user-controller.service";
import {UserDto} from "../../../api/models/user-dto";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit{
  private _subscriptions: Subscription [] = [];
  users: UserDto [] = [];
  productDialog: boolean = false;

  constructor (private _userService: UserControllerService,
              private _confirmationService: ConfirmationService,
              private _router: Router) {
  }
  ngOnInit(): void {
    const params = {
      pageSize: 100,
      pageNumber: 0
    }
    this._subscriptions.push(this._userService.getAllUsingGET2(params).subscribe((users: UserDto[]) =>{
      this.users = users;
    }));
  }
  ngOnDestroy(){
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }
  paginate(event: any) {
    const pageNumber = event.page;
    const params = {
      pageSize: 10,
      pageNumber
    };
    this._subscriptions.push(this._userService.getAllUsingGET2(params).subscribe((users: UserDto[]) =>{
      this.users = users;
    }));
  }
  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
  }
}



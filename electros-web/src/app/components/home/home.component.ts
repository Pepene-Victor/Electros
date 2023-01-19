import { Component, OnInit } from '@angular/core';
import {UserControllerService} from "../../api/services/user-controller.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {UserDto} from "../../api/models/user-dto";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  private _subscriptions: Subscription[] = [];
  isUserLogged: boolean = false;

  constructor(private _userService: UserControllerService,
              private _router: Router) { }

  ngOnInit(): void {
    this._subscriptions.push(this._userService.getLoggedUserUsingGET().subscribe({
      next: (user: UserDto) => {
        this.isUserLogged = true;
      },
      error: () =>{
        localStorage.clear();
      }
    }));
  }
  ngOnDestroy(): void {
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }

}

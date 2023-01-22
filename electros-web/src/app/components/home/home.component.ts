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
  cars: any;
  responsiveOptions;
  products: any [] = ["p1.jpeg","p2.jpeg","p3.jpeg","p4.jpeg","p5.jpeg","p6.jpeg","p7.jpeg","p8.jpeg","p9.jpeg"];

  constructor(private _userService: UserControllerService,
              private _router: Router) {

    this.responsiveOptions = [
      {
        breakpoint: '560px',
        numVisible: 3,
        numScroll: 3
      },
      {
        breakpoint: '768px',
        numVisible: 2,
        numScroll: 2
      },
      {
        breakpoint: '560px',
        numVisible: 1,
        numScroll: 1
      }
    ];
  }

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

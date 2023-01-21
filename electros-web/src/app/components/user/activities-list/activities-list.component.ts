import {Component, OnInit} from '@angular/core';
import {UserDto} from "../../../api/models/user-dto";
import {Subscription} from "rxjs";
import {UserControllerService} from "../../../api/services/user-controller.service";
import {ConfirmationService} from "primeng/api";
import {Router} from "@angular/router";
import {ActivityDto} from "../../../api/models/activity-dto";
import {ActivityControllerService} from "../../../api/services/activity-controller.service";

@Component({
  selector: 'app-activities-list',
  templateUrl: './activities-list.component.html',
  styleUrls: ['./activities-list.component.scss']
})
export class ActivitiesListComponent implements OnInit{
  private _subscriptions: Subscription [] = [];
  activities: ActivityDto [] = [];
  productDialog: boolean = false;

  constructor (private _activityService: ActivityControllerService,
               private _confirmationService: ConfirmationService,
               private _router: Router) {
  }
  ngOnInit(): void {
    const params = {
      pageSize: 100,
      pageNumber: 0
    }
    this._subscriptions.push(this._activityService.getAllUsingGET(params).subscribe((activities: ActivityDto[]) =>{
      this.activities = activities;
    }));
  }
  ngOnDestroy(){
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }
  paginate(event: any) {
    // const pageNumber = event.page;
    // const params = {
    //   pageSize: 15,
    //   pageNumber
    // };
    // this._subscriptions.push(this._activityService.getAllUsingGET(params).subscribe((activities: ActivityDto[]) =>{
    //   this.activities = activities;
    // }));
  }
  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
  }
}



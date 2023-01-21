import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {AccountDetailsComponent} from "./account-details/account-details.component";
import {ChangeUserDetailsComponent} from "./change-user-details/change-user-details.component";
import {UserListComponent} from "./user-list/user-list.component";
import {ActivitiesListComponent} from "./activities-list/activities-list.component";
import {CartComponent} from "./cart/cart.component";

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'account-details', component: AccountDetailsComponent },
  { path: 'change-user-details', component: ChangeUserDetailsComponent },
  { path: 'users-list', component: UserListComponent },
  { path: 'activities-list', component: ActivitiesListComponent },
  { path: 'cart', component: CartComponent }
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserRoutingModule { }

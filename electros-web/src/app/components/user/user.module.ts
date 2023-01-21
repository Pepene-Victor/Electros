import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {AccordionModule} from "primeng/accordion";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {UserRoutingModule} from "./user-routing.module";
import {AccountDetailsComponent} from "./account-details/account-details.component";
import {ChangeUserDetailsComponent} from "./change-user-details/change-user-details.component";
import {RippleModule} from "primeng/ripple";
import { UserFormComponent } from './user-form/user-form.component';
import { UserListComponent } from './user-list/user-list.component';
import {ConfirmDialogModule} from "primeng/confirmdialog";
import {DialogModule} from "primeng/dialog";
import {CardModule} from "primeng/card";
import {ProductRoutingModule} from "../product/product-routing.module";
import {ToastModule} from "primeng/toast";
import {TableModule} from "primeng/table";
import { ActivitiesListComponent } from './activities-list/activities-list.component';
import { CartComponent } from './cart/cart.component';



@NgModule({
    declarations: [
        ChangeUserDetailsComponent,
        AccountDetailsComponent,
        UserFormComponent,
        UserListComponent,
        ActivitiesListComponent,
        CartComponent
    ],
    exports: [
        UserFormComponent,
        CartComponent
    ],
    imports: [
        CommonModule,
        UserRoutingModule,
        AccordionModule,
        InputTextModule,
        ButtonModule,
        ReactiveFormsModule,
        HttpClientModule,
        FormsModule,
        RippleModule,
        DialogModule,
        CardModule,
        ToastModule,
        TableModule,
    ]
})
export class UserModule { }

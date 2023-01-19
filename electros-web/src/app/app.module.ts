import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {UserModule} from "./components/user/user.module";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ProductModule} from "./components/product/product.module";
import {MenubarModule} from "primeng/menubar";
import {StepsModule} from "primeng/steps";
import {ToastModule} from "primeng/toast";
import {RippleModule} from "primeng/ripple";
import {AccordionModule} from "primeng/accordion";
import {InputTextModule} from "primeng/inputtext";
import {ButtonModule} from "primeng/button";
import { LoginComponent } from './components/login/login.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { RegisterComponent } from './components/register/register.component';

// @ts-ignore
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    AccordionModule,
    InputTextModule,
    ButtonModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    UserModule,
    RippleModule,
    ProductModule,
    BrowserAnimationsModule,
    ToastModule,
    StepsModule,
    MenubarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

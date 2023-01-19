import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {FormBuilder, FormControlStatus, FormGroup, Validators} from "@angular/forms";
import {first, Subscription} from "rxjs";
import {UserControllerService} from "../../api/services/user-controller.service";
import {AuthService} from "../../api/services/auth.service";
import {UserDto} from "../../api/models/user-dto";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private _subscriptions: Subscription[] = [];
  loginForm!: FormGroup;
  showError: boolean = false;
  formValidation: string = 'INVALID';

  constructor(private _fb: FormBuilder,
              private _loginService: AuthService,
              private _router: Router,
              private _userService: UserControllerService) {
    this._createForm();
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }

  submitForm() {
    const uploadData = new FormData()
    uploadData.append('username', this.loginForm.get('username')?.value);
    uploadData.append('password', this.loginForm.get('password')?.value);
    this._subscriptions.push(this._loginService.login(uploadData).pipe(first()).subscribe({
      next: () => {
        console.log("Login Success!");
      },
      error: () => {{this.showError = true}}
    }));
    this._subscriptions.push(this._userService.getLoggedUserUsingGET().subscribe({
      next: (user: UserDto) => {
        if(user != null){
          this._userService.setLoggedAccount(user);
          this._router.navigate(['/home'])
            .then(() => {
              window.location.reload();
            });
        }
      }
    }))

  }

  private _createForm(){
    this.loginForm = this._fb.group({
      password: [null,
        [Validators.required]],
      username: [null,
        [Validators.required]]
    })
    this._subscriptions.push(
      this.loginForm.statusChanges.subscribe((value: FormControlStatus) =>{
        this.formValidation = value;
        console.log('Form status', this.formValidation);
      })
    );
  }
}

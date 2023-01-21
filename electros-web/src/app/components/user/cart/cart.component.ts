import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {UserDto} from "../../../api/models/user-dto";
import {UserControllerService} from "../../../api/services/user-controller.service";
import {ConfirmationService} from "primeng/api";
import {Router} from "@angular/router";
import {ProductDto} from "../../../api/models/product-dto";
import {ProductControllerService} from "../../../api/services/product-controller.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit{
  private _subscriptions: Subscription [] = [];
  products: ProductDto [] = [];
  productDialog: boolean = false;
  count: any;
  userLogged:any;

  constructor (private _productService: ProductControllerService,
               private _confirmationService: ConfirmationService,
               private _router: Router, private _http: HttpClient,
               private _userService: UserControllerService) {
  }
  ngOnInit(): void {
    this.userLogged = this._userService.getLoggedAccount();
    this.products  = this._productService.getProductsToOrder();
  }
  ngOnDestroy(): void{

  }
  order() {
    // const order ={
    //   product: this.products,
    //   productsCount: this.count,
    //   oderStatus: "APPROVED",
    //   user: this.userLogged,
    //   email: this.userLogged.email
    // }
    // const data = new FormData();
    // const entity = JSON.stringify(order);
    // data.append('entity',entity);
    // this._subscriptions.push(this._http.post("http://localhost:8090/order/create", entity).subscribe())
    const emptyProductCart: ProductDto [] = [];
    this._productService.setProductsToOrder(emptyProductCart);
    this._router.navigate(["/products"]);
  }
  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
  }

}

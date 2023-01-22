import { Component, OnInit } from '@angular/core';
import {ProductDto} from "../../../api/models/product-dto";
import {Subscription} from "rxjs";
import {ProductControllerService} from "../../../api/services/product-controller.service";
import {FormBuilder} from "@angular/forms";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";
import {UserControllerService} from "../../../api/services/user-controller.service";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.scss']
})
export class ProductsListComponent implements OnInit {
  private _subscriptions: Subscription [] = [];
  products: ProductDto [] = [];
  productsToOrder: ProductDto [] = [];
  productDialog: boolean = false;
  rows:number = 0;
  isAdmin:boolean = false;
  productImage: any;
  constructor(private _fb: FormBuilder, private _productService: ProductControllerService,
              private _confirmationService: ConfirmationService, private _messageService: MessageService,
              private _router: Router, private _userService: UserControllerService) {
    this._getDialogProductStatus();
    this._updateProductInList();
  }

  ngOnInit(): void {
    this.isAdmin = this._userService.getIsAdmin();
    const params = {
      pageSize: 100,
      pageNumber: 0
    }
    this._subscriptions.push(this._productService.getAllUsingGET1(params).subscribe((products: ProductDto[]) =>{
      products.forEach((product: ProductDto) =>{
      })
      this.products = products;
      this.rows = params.pageSize;

    }));
    this.productsToOrder = this._productService.getProductsToOrder();


  }

  ngOnDestroy(){
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
    this._productService.setProductsToOrder(this.productsToOrder)
  }



  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
  }

  addProduct(){
    this.productDialog = true;
    this._productService.isEdit$.next(false);
  }

  editProduct(product: ProductDto) {
    this.productDialog = true;
    this._productService.product$.next(product);
    this._productService.isEdit$.next(true);
  }

  hideDialog() {
    this.productDialog = false;
  }

  deleteProduct(product: ProductDto) {
    this._confirmationService.confirm({
      message: 'Are you sure you want to delete the product?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this._subscriptions.push(this._productService.deleteUsingDELETE1(product.id).subscribe({
          next: () => {
            const index = this.products.indexOf(product);
            this.products.splice(index, 1);
          }
        }));
        this._messageService.add({severity:'success', summary: 'Successful', detail: 'Product Deleted', life: 3000});
      }
    });
  }
  private _getDialogProductStatus(){
    this._subscriptions.push(
      this._productService.productDialog$.subscribe({
        next: value => {
          this.productDialog = value;
        }
      }));
  }

  private _updateProductInList(){
    this._subscriptions.push(this._productService.product$.subscribe({
      next: (product: ProductDto) => {
        const foundIndex = this.products.findIndex((productToUpdate: ProductDto) => product.id === productToUpdate.id);
        this.products[foundIndex] = product;
      }
    }));
  }

  paginate(event: any) {
    // const pageNumber = event.page;
    // const params = {
    //   pageSize: 10,
    //   pageNumber
    // };
    // this._subscriptions.push(this._productService.getAllUsingGET1(params).subscribe((products: ProductDto[]) =>{
    //   products.forEach((product: ProductDto) => {
    //     this.products.push(product);
    //   })
    // }));
  }

  addProductToCart(product: any) {

    this.productsToOrder.push(product)
  }
}

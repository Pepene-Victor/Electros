import { Component, OnInit } from '@angular/core';
import {ProductDto} from "../../../api/models/product-dto";
import {Subscription} from "rxjs";
import {ProductControllerService} from "../../../api/services/product-controller.service";
import {FormBuilder} from "@angular/forms";
import {ConfirmationService, MessageService} from "primeng/api";
import {Router} from "@angular/router";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.scss']
})
export class ProductsListComponent implements OnInit {
  private _subscriptions: Subscription [] = [];
  products: ProductDto [] = [];
  productDialog: boolean = false;

  constructor(private _fb: FormBuilder, private _productService: ProductControllerService,
              private _confirmationService: ConfirmationService, private _messageService: MessageService,
              private _router: Router) {
    this._getDialogProductStatus();
    this._updateProductInList();
  }

  ngOnInit(): void {
    const params = {
      pageSize: 10,
      currentPage: 0
    }
    this._subscriptions.push(this._productService.getAllUsingGET1(params).subscribe((products: ProductDto[]) =>{
      this.products = products;
      console.log(this.products);
    }));
  }

  ngOnDestroy(){
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    })
  }

  getValue(event: Event): string {
    return (event.target as HTMLInputElement).value;
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
    const id: number = product.id;
    this._confirmationService.confirm({
      message: 'Are you sure you want to delete the product?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this._subscriptions.push(this._productService.deleteUsingDELETE1(id).subscribe({
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
    const currentPage = event.page;
    const params = {
      pageSize: 10,
      currentPage
    };
    this._subscriptions.push(this._productService.getAllUsingGET1(params).subscribe((products: ProductDto[]) =>{
      this.products = products;
    }));
  }
}

import { Component, OnInit } from '@angular/core';
import {EMPTY, map, Subscription, switchMap} from "rxjs";
import {ProductDto} from "../../../api/models/product-dto";
import {FormBuilder, FormControlStatus, FormGroup, Validators} from "@angular/forms";
import {MenuItem} from "primeng/api";
import {ProductControllerService} from "../../../api/services/product-controller.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.scss']
})
export class ProductFormComponent implements OnInit {

  private _subscriptions: Subscription [] = [];
  productForm!: FormGroup;
  items: MenuItem [] = [];
  formValidation: string = 'INVALID';
  showError: string = "";
  editProduct: boolean = false;


  constructor(private _fb: FormBuilder,
              private _productService: ProductControllerService,
              private _router: Router) {
    this._createProductForm();
  }

  ngOnInit(): void {
    // this._subscriptions.push( this._productService.isEdit$.pipe(
    //   switchMap((value: boolean) => {
    //     this.editProduct = value;
    //     if(this.editProduct){
    //       return this._productService.product$.pipe(
    //         map(product => product));
    //     }
    //     return EMPTY;
    //   })
    // ).subscribe({
    //   next: (product: ProductDto) => {
    //     this.productForm.patchValue(product);
    //   }
    // }));
  }
  ngOnDestroy(){
    this._subscriptions.forEach(sub => {
      sub.unsubscribe();
    });
  }
  createProduct() {
    const entity: ProductDto = this.productForm.getRawValue();
    const file: any = null;
    const params = {
      file,
      entity
    }
    this._subscriptions.push(this._productService.createUsingPOST3(params).subscribe({
      next: () => {
        console.log("Product created!");
      },
      error: (error) => {{this.showError = error.error}}
    }));
    // this._productService.product$.next(product);
  }
  updateProduct() {
    const product: ProductDto = this.productForm.getRawValue();
    this._subscriptions.push(this._productService.updateUsingPUT3(product).subscribe({
      next: () => {
        console.log("Product updated!");
      },
      error: (error) => {{this.showError = error.error}}
    }));
    // this._productService.product$.next(product);
  }

  private _createProductForm(){
    this.productForm = this._fb.group({
      productName: [null,
        [Validators.required,
          Validators.maxLength(100)]],
      fabricationYear: [null,
        [Validators.required,
          Validators.maxLength(4)]],
      stock: [null,
        [Validators.required,
          Validators.pattern("^[0-9]*$")]],
      price: [null,
        [Validators.required,
          Validators.pattern("^(0|([1-9][0-9]*))(\\.[0-9]+)?$")]],
      unitMeasurement: [null,
        [Validators.maxLength(2)]],
      warrantyYears: [null,
        [Validators.required,
          Validators.maxLength(2)]],
    });
    this._subscriptions.push(
      this.productForm.statusChanges.subscribe((value: FormControlStatus) =>{
        this.formValidation = value;
      })
    );
  }

}

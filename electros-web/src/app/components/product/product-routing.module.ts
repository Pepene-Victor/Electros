import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ProductsListComponent} from "./products-list/products-list.component";

const routes: Routes = [
  { path: '', component: ProductsListComponent },
]

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ProductRoutingModule { }

/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import {BehaviorSubject, Observable, Observable as __Observable, Subject} from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ProductDto } from '../models/product-dto';

/**
 * Product Controller
 */
@Injectable({
  providedIn: 'root',
})
class ProductControllerService extends __BaseService {
  static readonly getAllUsingGET1Path = '/products/all';
  static readonly createUsingPOST4Path = '/products/create';
  static readonly createUsingPOST3Path = '/products/create-product';
  static readonly deleteUsingDELETE1Path = '/products/delete/{id}';
  static readonly findByIdUsingGET1Path = '/products/find-by-id/{id}';
  static readonly updateUsingPUT3Path = '/products/update';

  isEdit$: Subject<boolean> = new Subject<boolean>();
  productDialog$: Subject<boolean> = new Subject<boolean>();
  product$: BehaviorSubject<ProductDto> = new BehaviorSubject<ProductDto>({} as ProductDto);
  productToBeCreated$: BehaviorSubject<ProductDto> = new BehaviorSubject<ProductDto>({} as ProductDto);
  getIsEdit(){
    let isEdit = localStorage.getItem("isEdit");
    if(!!isEdit) {
      return JSON.parse(isEdit);
    }
  }

  setIsEdit(isEdit: boolean){
    localStorage.setItem("isEdit", JSON.stringify(isEdit));
  }

  getProductsToOrder(){
    let productsToOrder = localStorage.getItem("isEdit");
    if(!!productsToOrder) {
      return JSON.parse(productsToOrder);
    }
  }

  setProductsToOrder(productsToOrder: any){
    localStorage.setItem("isEdit", JSON.stringify(productsToOrder));
  }

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAll
   * @param params The `ProductControllerService.GetAllUsingGET1Params` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGET1Response(params: ProductControllerService.GetAllUsingGET1Params): __Observable<__StrictHttpResponse<Array<ProductDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.pageSize != null) __params = __params.set('pageSize', params.pageSize.toString());
    if (params.pageNumber != null) __params = __params.set('pageNumber', params.pageNumber.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/products/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ProductDto>>;
      })
    );
  }
  /**
   * getAll
   * @param params The `ProductControllerService.GetAllUsingGET1Params` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGET1(params: ProductControllerService.GetAllUsingGET1Params): __Observable<Array<ProductDto>> {
    return this.getAllUsingGET1Response(params).pipe(
      __map(_r => _r.body as Array<ProductDto>)
    );
  }

  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOST4Response(entity: ProductDto): __Observable<__StrictHttpResponse<ProductDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/products/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDto>;
      })
    );
  }
  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOST4(entity: ProductDto): __Observable<ProductDto> {
    return this.createUsingPOST4Response(entity).pipe(
      __map(_r => _r.body as ProductDto)
    );
  }

  /**
   * create
   * @param params The `ProductControllerService.CreateUsingPOST3Params` containing the following parameters:
   *
   * - `file`: file
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST3Response(params: ProductControllerService.CreateUsingPOST3Params): __Observable<__StrictHttpResponse<ProductDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = params.file;
    __body = params.entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/products/create-product`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDto>;
      })
    );
  }
  /**
   * create
   * @param params The `ProductControllerService.CreateUsingPOST3Params` containing the following parameters:
   *
   * - `file`: file
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST3(params: { file: any; entity: ProductDto }): Observable<ProductDto> {
    return this.createUsingPOST3Response(params).pipe(
      __map(_r => _r.body as ProductDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE1Response(id: number|undefined): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = id;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/products/delete/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<null>;
      })
    );
  }
  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE1(id: number|undefined): __Observable<null> {
    return this.deleteUsingDELETE1Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET1Response(id: number): __Observable<__StrictHttpResponse<ProductDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/products/find-by-id/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET1(id: number): __Observable<ProductDto> {
    return this.findByIdUsingGET1Response(id).pipe(
      __map(_r => _r.body as ProductDto)
    );
  }

  /**
   * update
   * @param params The `ProductControllerService.UpdateUsingPUT3Params` containing the following parameters:
   *
   * - `stock`:
   *
   * - `productName`:
   *
   * - `price`:
   *
   * - `warrantyYears`:
   *
   * - `unitMeasurement`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `fabricationYear`:
   *
   * - `createdDate`:
   *
   * @return OK
   */
  updateUsingPUT3Response(params: ProductControllerService.UpdateUsingPUT3Params): __Observable<__StrictHttpResponse<ProductDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.stock != null) __params = __params.set('stock', params.stock.toString());
    if (params.productName != null) __params = __params.set('productName', params.productName.toString());
    if (params.price != null) __params = __params.set('price', params.price.toString());
    if (params.warrantyYears != null) __params = __params.set('warrantyYears', params.warrantyYears.toString());
    if (params.unitMeasurement != null) __params = __params.set('unitMeasurement', params.unitMeasurement.toString());
    if (params.image != null) __params = __params.set('image', params.image.toString());
    if (params.id != null) __params = __params.set('id', params.id.toString());
    if (params.fabricationYear != null) __params = __params.set('fabricationYear', params.fabricationYear.toString());
    if (params.createdDate != null) __params = __params.set('createdDate', params.createdDate.toString());
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/products/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ProductDto>;
      })
    );
  }
  /**
   * update
   * @param params The `ProductControllerService.UpdateUsingPUT3Params` containing the following parameters:
   *
   * - `stock`:
   *
   * - `productName`:
   *
   * - `price`:
   *
   * - `warrantyYears`:
   *
   * - `unitMeasurement`:
   *
   * - `image`:
   *
   * - `id`:
   *
   * - `fabricationYear`:
   *
   * - `createdDate`:
   *
   * @return OK
   */
  updateUsingPUT3(params: ProductControllerService.UpdateUsingPUT3Params): __Observable<ProductDto> {
    return this.updateUsingPUT3Response(params).pipe(
      __map(_r => _r.body as ProductDto)
    );
  }
}

module ProductControllerService {

  /**
   * Parameters for getAllUsingGET1
   */
  export interface GetAllUsingGET1Params {

    /**
     * pageSize
     */
    pageSize?: number;

    /**
     * pageNumber
     */
    pageNumber?: number;
  }

  /**
   * Parameters for createUsingPOST3
   */
  export interface CreateUsingPOST3Params {

    /**
     * file
     */
    file: string;

    /**
     * entity
     */
    entity: ProductDto;
  }

  /**
   * Parameters for updateUsingPUT3
   */
  export interface UpdateUsingPUT3Params {
    stock?: number;
    productName?: string;
    price?: number;
    warrantyYears?: number;
    unitMeasurement?: string;
    image?: ArrayBuffer;
    id?: number;
    fabricationYear?: number;
    createdDate?: string;
  }
}

export { ProductControllerService }

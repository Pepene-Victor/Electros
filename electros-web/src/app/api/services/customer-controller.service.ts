/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import {StrictHttpResponse, StrictHttpResponse as __StrictHttpResponse} from '../strict-http-response';
import {Observable, Observable as __Observable} from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { CustomerDto } from '../models/customer-dto';

/**
 * Customer Controller
 */
@Injectable({
  providedIn: 'root',
})
class CustomerControllerService extends __BaseService {
  static readonly createUsingPOST2Path = '/customer/create';
  static readonly findByUserUsingGET1Path = '/customer/find-by-username';
  static readonly updateUsingPUT2Path = '/customer/update';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * create
   * @param params The `CustomerControllerService.CreateUsingPOST2Params` containing the following parameters:
   *
   * - `username`: username
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST2Response(params: CustomerControllerService.CreateUsingPOST2Params): __Observable<__StrictHttpResponse<CustomerDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.username != null) __params = __params.set('username', params.username.toString());
    __body = params.entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/customer/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CustomerDto>;
      })
    );
  }
  /**
   * create
   * @param params The `CustomerControllerService.CreateUsingPOST2Params` containing the following parameters:
   *
   * - `username`: username
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST2(params: CustomerControllerService.CreateUsingPOST2Params): __Observable<CustomerDto> {
    return this.createUsingPOST2Response(params).pipe(
      __map(_r => _r.body as CustomerDto)
    );
  }

  /**
   * findByUser
   * @param username username
   * @return OK
   */
  findByUserUsingGET1Response(username: string | undefined): Observable<StrictHttpResponse<CustomerDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (username != null) __params = __params.set('username', username.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/customer/find-by-username`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CustomerDto>;
      })
    );
  }
  /**
   * findByUser
   * @param username username
   * @return OK
   */
  findByUserUsingGET1(username: string | undefined): Observable<CustomerDto> {
    return this.findByUserUsingGET1Response(username).pipe(
      __map(_r => _r.body as CustomerDto)
    );
  }

  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT2Response(entity: CustomerDto): __Observable<__StrictHttpResponse<CustomerDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/customer/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<CustomerDto>;
      })
    );
  }
  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT2(entity: CustomerDto): __Observable<CustomerDto> {
    return this.updateUsingPUT2Response(entity).pipe(
      __map(_r => _r.body as CustomerDto)
    );
  }
}

module CustomerControllerService {

  /**
   * Parameters for createUsingPOST2
   */
  export interface CreateUsingPOST2Params {

    /**
     * username
     */
    username: string;

    /**
     * entity
     */
    entity: CustomerDto;
  }
}

export { CustomerControllerService }

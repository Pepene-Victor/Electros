/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { AdministratorDto } from '../models/administrator-dto';

/**
 * Administrator Controller
 */
@Injectable({
  providedIn: 'root',
})
class AdministratorControllerService extends __BaseService {
  static readonly createUsingPOST1Path = '/admin/create';
  static readonly findByUserUsingGETPath = '/admin/find-by-username';
  static readonly updateUsingPUT1Path = '/admin/update';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * create
   * @param params The `AdministratorControllerService.CreateUsingPOST1Params` containing the following parameters:
   *
   * - `username`: username
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST1Response(params: AdministratorControllerService.CreateUsingPOST1Params): __Observable<__StrictHttpResponse<AdministratorDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.username != null) __params = __params.set('username', params.username.toString());
    __body = params.entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/admin/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdministratorDto>;
      })
    );
  }
  /**
   * create
   * @param params The `AdministratorControllerService.CreateUsingPOST1Params` containing the following parameters:
   *
   * - `username`: username
   *
   * - `entity`: entity
   *
   * @return OK
   */
  createUsingPOST1(params: AdministratorControllerService.CreateUsingPOST1Params): __Observable<AdministratorDto> {
    return this.createUsingPOST1Response(params).pipe(
      __map(_r => _r.body as AdministratorDto)
    );
  }

  /**
   * findByUser
   * @param username username
   * @return OK
   */
  findByUserUsingGETResponse(username: string): __Observable<__StrictHttpResponse<AdministratorDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (username != null) __params = __params.set('username', username.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/admin/find-by-username`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdministratorDto>;
      })
    );
  }
  /**
   * findByUser
   * @param username username
   * @return OK
   */
  findByUserUsingGET(username: string): __Observable<AdministratorDto> {
    return this.findByUserUsingGETResponse(username).pipe(
      __map(_r => _r.body as AdministratorDto)
    );
  }

  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT1Response(entity: AdministratorDto): __Observable<__StrictHttpResponse<AdministratorDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/admin/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<AdministratorDto>;
      })
    );
  }
  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT1(entity: AdministratorDto): __Observable<AdministratorDto> {
    return this.updateUsingPUT1Response(entity).pipe(
      __map(_r => _r.body as AdministratorDto)
    );
  }
}

module AdministratorControllerService {

  /**
   * Parameters for createUsingPOST1
   */
  export interface CreateUsingPOST1Params {

    /**
     * username
     */
    username: string;

    /**
     * entity
     */
    entity: AdministratorDto;
  }
}

export { AdministratorControllerService }

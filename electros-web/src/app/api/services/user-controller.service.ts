/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { UserDto } from '../models/user-dto';

/**
 * User Controller
 */
@Injectable({
  providedIn: 'root',
})
class UserControllerService extends __BaseService {
  static readonly getAllUsingGET2Path = '/user/all';
  static readonly createUsingPOST5Path = '/user/create';
  static readonly deleteUsingDELETE2Path = '/user/delete/{id}';
  static readonly findByIdUsingGET2Path = '/user/find-by-id/{id}';
  static readonly registerUsingPOSTPath = '/user/register';
  static readonly updateUsingPUT4Path = '/user/update';
  static readonly updatePasswordUsingPUTPath = '/user/update/password';
  static readonly updateUsernameUsingPUTPath = '/user/update/username';
  static readonly getLoggedUserUsingGETPath = '/user/username';

  getAccountDetailsType(){
    let accountDetailsType = localStorage.getItem("accountDetailsType")
    if(!!accountDetailsType){
      return JSON.parse(accountDetailsType);
    }
  }

  setAccountDetailsType(accountDetailsType: string){
    localStorage.setItem("accountDetailsType", JSON.stringify(accountDetailsType));
  }

  getLoggedAccount(){
    let loggedAccount = localStorage.getItem("user");
    if(!!loggedAccount) {
      return JSON.parse(loggedAccount);
    }
  }

  setLoggedAccount(loggedAccount: UserDto){
    localStorage.setItem("user", JSON.stringify(loggedAccount));
  }

  getIsLoggedIn(){
    let isLoggedIn = localStorage.getItem("loginStatus");
    if(!!isLoggedIn) {
      return JSON.parse(isLoggedIn);
    }
  }

  setIsLoggedIn(isLoggedIn: boolean){
    localStorage.setItem("loginStatus", JSON.stringify(isLoggedIn));
  }

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAll
   * @param params The `UserControllerService.GetAllUsingGET2Params` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGET2Response(params: UserControllerService.GetAllUsingGET2Params): __Observable<__StrictHttpResponse<Array<UserDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.pageSize != null) __params = __params.set('pageSize', params.pageSize.toString());
    if (params.pageNumber != null) __params = __params.set('pageNumber', params.pageNumber.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/user/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<UserDto>>;
      })
    );
  }
  /**
   * getAll
   * @param params The `UserControllerService.GetAllUsingGET2Params` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGET2(params: UserControllerService.GetAllUsingGET2Params): __Observable<Array<UserDto>> {
    return this.getAllUsingGET2Response(params).pipe(
      __map(_r => _r.body as Array<UserDto>)
    );
  }

  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOST5Response(entity: UserDto): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/user/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOST5(entity: UserDto): __Observable<UserDto> {
    return this.createUsingPOST5Response(entity).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETE2Response(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = id;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/user/delete/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE2(id: number): __Observable<null> {
    return this.deleteUsingDELETE2Response(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET2Response(id: number): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/user/find-by-id/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET2(id: number): __Observable<UserDto> {
    return this.findByIdUsingGET2Response(id).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * register
   * @param params The `UserControllerService.RegisterUsingPOSTParams` containing the following parameters:
   *
   * - `username`:
   *
   * - `role`:
   *
   * - `passwordStatus`:
   *
   * - `password`:
   *
   * - `id`:
   *
   * - `createdDate`:
   *
   * @return OK
   */
  registerUsingPOSTResponse(params: UserControllerService.RegisterUsingPOSTParams): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.username != null) __params = __params.set('username', params.username.toString());
    if (params.role != null) __params = __params.set('role', params.role.toString());
    if (params.passwordStatus != null) __params = __params.set('passwordStatus', params.passwordStatus.toString());
    if (params.password != null) __params = __params.set('password', params.password.toString());
    if (params.id != null) __params = __params.set('id', params.id.toString());
    if (params.createdDate != null) __params = __params.set('createdDate', params.createdDate.toString());
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/user/register`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * register
   * @param params The `UserControllerService.RegisterUsingPOSTParams` containing the following parameters:
   *
   * - `username`:
   *
   * - `role`:
   *
   * - `passwordStatus`:
   *
   * - `password`:
   *
   * - `id`:
   *
   * - `createdDate`:
   *
   * @return OK
   */
  registerUsingPOST(params: UserControllerService.RegisterUsingPOSTParams): __Observable<UserDto> {
    return this.registerUsingPOSTResponse(params).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT4Response(entity: UserDto): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/user/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT4(entity: UserDto): __Observable<UserDto> {
    return this.updateUsingPUT4Response(entity).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * updatePassword
   * @param userDto userDto
   * @return OK
   */
  updatePasswordUsingPUTResponse(userDto: UserDto): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/user/update/password`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * updatePassword
   * @param userDto userDto
   * @return OK
   */
  updatePasswordUsingPUT(userDto: UserDto): __Observable<UserDto> {
    return this.updatePasswordUsingPUTResponse(userDto).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * updateUsername
   * @param userDto userDto
   * @return OK
   */
  updateUsernameUsingPUTResponse(userDto: UserDto): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = userDto;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/user/update/username`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * updateUsername
   * @param userDto userDto
   * @return OK
   */
  updateUsernameUsingPUT(userDto: UserDto): __Observable<UserDto> {
    return this.updateUsernameUsingPUTResponse(userDto).pipe(
      __map(_r => _r.body as UserDto)
    );
  }

  /**
   * getLoggedUser
   * @return OK
   */
  getLoggedUserUsingGETResponse(): __Observable<__StrictHttpResponse<UserDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/user/username`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<UserDto>;
      })
    );
  }
  /**
   * getLoggedUser
   * @return OK
   */
  getLoggedUserUsingGET(): __Observable<UserDto> {
    return this.getLoggedUserUsingGETResponse().pipe(
      __map(_r => _r.body as UserDto)
    );
  }
}

module UserControllerService {

  /**
   * Parameters for getAllUsingGET2
   */
  export interface GetAllUsingGET2Params {

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
   * Parameters for registerUsingPOST
   */
  export interface RegisterUsingPOSTParams {
    username: string;
    role: 'ADMINISTRATOR' | 'CUSTOMER';
    passwordStatus: 'NEW' | 'OK' | 'OLD';
    password: string;
    id?: number;
    createdDate?: string;
  }
}

export { UserControllerService }

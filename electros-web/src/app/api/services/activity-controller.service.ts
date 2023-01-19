/* tslint:disable */
import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { BaseService as __BaseService } from '../base-service';
import { ApiConfiguration as __Configuration } from '../api-configuration';
import { StrictHttpResponse as __StrictHttpResponse } from '../strict-http-response';
import { Observable as __Observable } from 'rxjs';
import { map as __map, filter as __filter } from 'rxjs/operators';

import { ActivityDto } from '../models/activity-dto';

/**
 * Activity Controller
 */
@Injectable({
  providedIn: 'root',
})
class ActivityControllerService extends __BaseService {
  static readonly getAllUsingGETPath = '/activities/all';
  static readonly createUsingPOSTPath = '/activities/create';
  static readonly deleteUsingDELETEPath = '/activities/delete/{id}';
  static readonly findByIdUsingGETPath = '/activities/find-by-id/{id}';
  static readonly updateUsingPUTPath = '/activities/update';

  constructor(
    config: __Configuration,
    http: HttpClient
  ) {
    super(config, http);
  }

  /**
   * getAll
   * @param params The `ActivityControllerService.GetAllUsingGETParams` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGETResponse(params: ActivityControllerService.GetAllUsingGETParams): __Observable<__StrictHttpResponse<Array<ActivityDto>>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    if (params.pageSize != null) __params = __params.set('pageSize', params.pageSize.toString());
    if (params.pageNumber != null) __params = __params.set('pageNumber', params.pageNumber.toString());
    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/activities/all`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<Array<ActivityDto>>;
      })
    );
  }
  /**
   * getAll
   * @param params The `ActivityControllerService.GetAllUsingGETParams` containing the following parameters:
   *
   * - `pageSize`: pageSize
   *
   * - `pageNumber`: pageNumber
   *
   * @return OK
   */
  getAllUsingGET(params: ActivityControllerService.GetAllUsingGETParams): __Observable<Array<ActivityDto>> {
    return this.getAllUsingGETResponse(params).pipe(
      __map(_r => _r.body as Array<ActivityDto>)
    );
  }

  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOSTResponse(entity: ActivityDto): __Observable<__StrictHttpResponse<ActivityDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'POST',
      this.rootUrl + `/activities/create`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ActivityDto>;
      })
    );
  }
  /**
   * create
   * @param entity entity
   * @return OK
   */
  createUsingPOST(entity: ActivityDto): __Observable<ActivityDto> {
    return this.createUsingPOSTResponse(entity).pipe(
      __map(_r => _r.body as ActivityDto)
    );
  }

  /**
   * delete
   * @param id id
   */
  deleteUsingDELETEResponse(id: number): __Observable<__StrictHttpResponse<null>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = id;
    let req = new HttpRequest<any>(
      'DELETE',
      this.rootUrl + `/activities/delete/${encodeURIComponent(String(id))}`,
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
  deleteUsingDELETE(id: number): __Observable<null> {
    return this.deleteUsingDELETEResponse(id).pipe(
      __map(_r => _r.body as null)
    );
  }

  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGETResponse(id: number): __Observable<__StrictHttpResponse<ActivityDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;

    let req = new HttpRequest<any>(
      'GET',
      this.rootUrl + `/activities/find-by-id/${encodeURIComponent(String(id))}`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ActivityDto>;
      })
    );
  }
  /**
   * findById
   * @param id id
   * @return OK
   */
  findByIdUsingGET(id: number): __Observable<ActivityDto> {
    return this.findByIdUsingGETResponse(id).pipe(
      __map(_r => _r.body as ActivityDto)
    );
  }

  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUTResponse(entity: ActivityDto): __Observable<__StrictHttpResponse<ActivityDto>> {
    let __params = this.newParams();
    let __headers = new HttpHeaders();
    let __body: any = null;
    __body = entity;
    let req = new HttpRequest<any>(
      'PUT',
      this.rootUrl + `/activities/update`,
      __body,
      {
        headers: __headers,
        params: __params,
        responseType: 'json'
      });

    return this.http.request<any>(req).pipe(
      __filter(_r => _r instanceof HttpResponse),
      __map((_r) => {
        return _r as __StrictHttpResponse<ActivityDto>;
      })
    );
  }
  /**
   * update
   * @param entity entity
   * @return OK
   */
  updateUsingPUT(entity: ActivityDto): __Observable<ActivityDto> {
    return this.updateUsingPUTResponse(entity).pipe(
      __map(_r => _r.body as ActivityDto)
    );
  }
}

module ActivityControllerService {

  /**
   * Parameters for getAllUsingGET
   */
  export interface GetAllUsingGETParams {

    /**
     * pageSize
     */
    pageSize?: number;

    /**
     * pageNumber
     */
    pageNumber?: number;
  }
}

export { ActivityControllerService }

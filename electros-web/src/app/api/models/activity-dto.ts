/* tslint:disable */
export interface ActivityDto {
  activityType: 'CREATE' | 'DELETE' | 'REQUEST' | 'UPDATE';
  createdDate?: string;
  description?: string;
  id?: number;
}

/* tslint:disable */
export interface User {
  createdDate?: string;
  id?: number;
  password?: string;
  passwordStatus?: 'NEW' | 'OK' | 'OLD';
  role?: 'ADMINISTRATOR' | 'CUSTOMER';
  username?: string;
}

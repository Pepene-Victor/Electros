/* tslint:disable */
export interface UserDto {
  createdDate?: string;
  id?: number;
  password: string;
  passwordStatus: 'NEW' | 'OK' | 'OLD';
  role: 'ADMINISTRATOR' | 'CUSTOMER';
  username: string;
}

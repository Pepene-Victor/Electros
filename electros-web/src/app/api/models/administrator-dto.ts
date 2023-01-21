/* tslint:disable */
import { User } from './user';
export interface AdministratorDto {
  company?: string;
  createdDate?: string;
  email?: string;
  firstName: string;
  id?: number;
  lastName: string;
  personalPhoneNumber?: string;
  status: 'BLOCKED' | 'FREE' | 'PENDING';
  user?: User;
}

/* tslint:disable */
import { User } from './user';
export interface CustomerDto {
  age?: number;
  createdDate?: string;
  email: string;
  gender?: 'FEMININE' | 'MASCULINE' | 'OTHER';
  id?: number;
  name: string;
  status: 'BLOCKED' | 'FREE' | 'PENDING';
  user: User;
}

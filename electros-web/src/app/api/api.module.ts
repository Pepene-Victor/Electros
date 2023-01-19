/* tslint:disable */
import { NgModule, ModuleWithProviders } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ApiConfiguration, ApiConfigurationInterface } from './api-configuration';

import { ActivityControllerService } from './services/activity-controller.service';
import { AdministratorControllerService } from './services/administrator-controller.service';
import { CustomerControllerService } from './services/customer-controller.service';
import { ProductControllerService } from './services/product-controller.service';
import { UserControllerService } from './services/user-controller.service';

/**
 * Provider for all Api services, plus ApiConfiguration
 */
@NgModule({
  imports: [
    HttpClientModule
  ],
  exports: [
    HttpClientModule
  ],
  declarations: [],
  providers: [
    ApiConfiguration,
    ActivityControllerService,
    AdministratorControllerService,
    CustomerControllerService,
    ProductControllerService,
    UserControllerService
  ],
})
export class ApiModule {
  static forRoot(customParams: ApiConfigurationInterface): ModuleWithProviders<ApiModule> {
    return {
      ngModule: ApiModule,
      providers: [
        {
          provide: ApiConfiguration,
          useValue: {rootUrl: customParams.rootUrl}
        }
      ]
    }
  }
}

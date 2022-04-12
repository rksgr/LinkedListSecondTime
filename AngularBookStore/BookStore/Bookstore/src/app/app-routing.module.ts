import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './component/home-page/home-page.component';
import { HeaderComponent } from './component/header/header.component';
import { AddUserComponent } from './component/add-user/add-user.component';
import { LoginUserComponent } from './component/login-user/login-user.component';
import { ForgotPasswordComponent } from './component/forgot-password/forgot-password.component';
import { ResetPasswordComponent } from './component/reset-password/reset-password.component';
import { CartComponent } from './component/cart/cart.component';
import { OrderComponent } from './component/order/order.component';
import { TestFileComponent } from './component/test-file/test-file.component';

const routes: Routes = [
  {path: '',redirectTo: 'login-user', pathMatch:'full'},
  {path : 'home-page', component: HomePageComponent},
  {path : 'add-user', component: AddUserComponent},
  {path : 'login-user', component: LoginUserComponent},
  {path : 'forgot-password', component: ForgotPasswordComponent},
  {path : 'reset-password', component: ResetPasswordComponent},
  {path : 'cart', component: CartComponent},
  {path : 'order', component: OrderComponent},
  {path : 'testFile', component: TestFileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

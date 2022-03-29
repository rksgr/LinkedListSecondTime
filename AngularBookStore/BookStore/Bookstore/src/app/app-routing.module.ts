import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './component/home/home.component';
import { LoginUserComponent } from './component/login-user/login-user.component';
import { RegisterComponent } from './component/register/register.component';
import { CartComponent } from './component/cart/cart.component';
import { FeedbackComponent } from './component/feedback/feedback.component';
import { TestFileComponent } from './component/test-file/test-file.component';
import { AuthenticateComponent } from './component/authenticate/authenticate.component';


const routes: Routes = [
  {path: '',redirectTo: 'login-user', pathMatch:'full'},
  {path : 'home', component: HomeComponent},
  {path : 'login-user', component: LoginUserComponent},
  {path : 'register', component: RegisterComponent},
  {path : 'cart', component: CartComponent},
  {path : 'testFile', component: TestFileComponent},
  {path : 'feedback', component: FeedbackComponent},
  {path : 'authenticate', component: AuthenticateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

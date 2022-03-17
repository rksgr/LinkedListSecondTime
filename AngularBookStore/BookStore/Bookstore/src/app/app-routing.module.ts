import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './component/home-page/home-page.component';
import { HeaderComponent } from './component/header/header.component';
import { AddUserComponent } from './component/add-user/add-user.component';
import { LoginUserComponent } from './component/login-user/login-user.component';

const routes: Routes = [
  {path: '',redirectTo: 'home-page', pathMatch:'full'},
  {path : 'home-page', component: HomePageComponent},
  {path : 'add-user', component: AddUserComponent},
  {path : 'login-user', component: LoginUserComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

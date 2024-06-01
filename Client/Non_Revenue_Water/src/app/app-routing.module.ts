import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { ServiceProvidedComponent } from './service-provided/service-provided.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { OurTeamComponent } from './our-team/our-team.component';
import { HelpFaqComponent } from './help-faq/help-faq.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminSignupComponent } from './admin-signup/admin-signup.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';


const routes: Routes = [
  { path: "login", component: LoginComponent },
  { path: "adminlogin", component: AdminLoginComponent },
  { path: "signup", component: SignupComponent },
  { path: "adminsignup", component: AdminSignupComponent },
  { path: "dashboard", component: DashboardComponent },
  { path: "profile", component: ProfileComponent },
  { path: "home", component: HomeComponent },
  { path: "service", component: ServiceProvidedComponent },
  { path: "about", component: AboutUsComponent },
  { path: "team", component: OurTeamComponent },
  { path: "help", component: HelpFaqComponent },
  { path: "userhome", component: UserHomeComponent },
  { path: "adminhome", component: AdminHomeComponent },
  { path: "", pathMatch: "full", redirectTo: "login" },
  { path: "**", redirectTo: "login" },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

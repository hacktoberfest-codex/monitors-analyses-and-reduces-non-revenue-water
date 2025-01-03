import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule, routes } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { RouterLink, provideRouter } from '@angular/router';
import { RouterLinkActive } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ToastComponent } from './toast/toast.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule, provideHttpClient, withInterceptors } from '@angular/common/http';
import { ModalComponent } from './modal/modal.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { NavbarComponent } from './navbar/navbar.component';
import { ServiceProvidedComponent } from './service-provided/service-provided.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { OurTeamComponent } from './our-team/our-team.component';
import { HelpFaqComponent } from './help-faq/help-faq.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

import { MatDividerModule } from '@angular/material/divider';
import {MatListModule} from '@angular/material/list';
import { UserHomeComponent } from './user-home/user-home.component';
import { WaterUsageComponent } from './water-usage/water-usage.component';
import { PaybillComponent } from './paybill/paybill.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { AdminSignupComponent } from './admin-signup/admin-signup.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { requestInterceptor } from './interceptor/request.interceptor';
import { ComplaintComponent } from './complaint/complaint.component';
import { AboutComponent } from './about/about.component';
import { ComplaintViewComponent } from './complaint-view/complaint-view.component';
import { ContributeComponent } from './contribute/contribute.component';
import { ContributeViewComponent } from './contribute-view/contribute-view.component';
import { ContactUsComponent } from './contact-us/contact-us.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ToastComponent,
    DashboardComponent,
    ModalComponent,
    ProfileComponent,
    HomeComponent,
    NavbarComponent,
    ServiceProvidedComponent,
    AboutUsComponent,
    OurTeamComponent,
    HelpFaqComponent,
    UserHomeComponent,
    WaterUsageComponent,
    PaybillComponent,
    AdminDashboardComponent,
    AdminSignupComponent,
    AdminLoginComponent,
    AdminHomeComponent,
    ComplaintComponent,
    AboutComponent,
    ComplaintViewComponent,
    ContributeComponent,
    ContributeViewComponent,
    ContactUsComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterLink,
    FormsModule,
    HttpClientModule,
    RouterLinkActive,
    NgxChartsModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatListModule,
    
  ],
  providers: [provideRouter(routes),
    provideHttpClient(withInterceptors([requestInterceptor]))],
  bootstrap: [AppComponent]
})
export class AppModule { }

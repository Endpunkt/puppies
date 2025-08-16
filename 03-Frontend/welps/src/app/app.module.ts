import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ItemMenuListComponent } from './components/item-menu-list/item-menu-list.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { PuppyService } from './services/puppy.service';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { PuppyDetailsComponent } from './components/puppy/puppy-details.component';
import { FoodDetailsComponent } from './components/food/food-details.component';
import { AdminComponent } from './components/admin/admin.component';






const rout: Routes = [
  {path: 'admin', component: AdminComponent},
  {path: 'food/:foodId', component: FoodDetailsComponent},
  {path: 'foods/:foodId', component: FoodDetailsComponent},
  {path: 'puppy/:puppyId', component: PuppyDetailsComponent },
  {path: 'puppies/:puppyId', component: PuppyDetailsComponent},
  {path: 'puppies', component: ItemMenuListComponent},
  {path: 'category/:id', component: ItemMenuListComponent},
  {path: 'category', component: ItemMenuListComponent},
  {path: 'search', component: ItemMenuListComponent},
  { path: '', redirectTo: 'puppies', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AdminComponent,
    AppComponent,
    NavbarComponent,
    ItemMenuListComponent,
    HeaderComponent,
    FooterComponent,
    PuppyDetailsComponent,
    FoodDetailsComponent,
    AdminComponent 
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(rout),
    //ComponentModule,
    BrowserAnimationsModule,
    FormsModule,
    MatPaginatorModule
  ],

  exports: [RouterModule],
  providers: [PuppyService,provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }

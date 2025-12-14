import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreditoComponent } from './pages/credito/credito.component';

const routes: Routes = [
  {
    path: '',
    component: CreditoComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }

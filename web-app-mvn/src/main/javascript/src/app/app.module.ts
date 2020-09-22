import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {MenubarModule} from 'primeng/menubar';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProjectlistComponent } from './projectlist/projectlist.component';
import { ProjectComponent } from './project/project.component';
import { ActorComponent } from './actor/actor.component';
import { ActorlistComponent } from './actorlist/actorlist.component';

@NgModule({
  declarations: [
    AppComponent,
    ProjectlistComponent,
    ProjectComponent,
    // ActorComponent,
    ActorlistComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MenubarModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

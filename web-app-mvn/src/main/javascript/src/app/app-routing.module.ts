import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProjectlistComponent } from './projectlist/projectlist.component';
import { ProjectComponent } from './project/project.component';
import { ActorlistComponent } from './actorlist/actorlist.component';
import { ActorComponent } from './actor/actor.component';

const routes: Routes = [
	{ path: 'project', component: ProjectlistComponent },
	{ path: 'project/:projectId', component: ProjectComponent },
	{ path: 'persona', component: ActorlistComponent },
	{ path: 'persona/:personaId', component: ActorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

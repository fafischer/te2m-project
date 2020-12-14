import { Component } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

	title = 'te2m::projects';

    items: MenuItem[];

    ngOnInit() {
       this.items = [
              {
                  label: 'Project',
                  routerLink: ['/project']
              },
              { label: 'Personas',
              	routerLink: ['/persona']}
          ];
      }
}

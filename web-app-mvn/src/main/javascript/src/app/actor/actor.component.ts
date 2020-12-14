import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['./actor.component.css']
})
export class ActorComponent implements OnInit {

	actorId;

  constructor(
    private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
  	  this.route.paramMap.subscribe(params => {
        this.actorId = params.get('personaId');
      });
  }

}

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-cenario',
  templateUrl: './list-cenario.component.html',
  styleUrls: ['./list-cenario.component.scss']
})
export class ListCenarioComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }


  valor!: number;
  destino!: number;

  transferir() {
    console.log('Solicitada nova transferência');
    console.log('Valor: ', this.valor);
    console.log('Destino: ', this.destino);
  }
}

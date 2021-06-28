import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-ft-header',
  templateUrl: './ft-header.component.html',
  styleUrls: ['./ft-header.component.scss']
})
export class FtHeaderComponent implements OnInit {
  placement="bottom";
	triggerText="Margindeckung";
  Textsaldo="Saldo aktuell";
  triggerText2="Verpflichtungen";
  triggertext3="Verfügungsrahmen";
  modalText="detailsss"
  loadingTemplate: string;
  constructor() {
    this.loadingTemplate =
      `<span class="ag-overlay-loading-center"></span>`;
   } 

  columnDefs = [
    { field: ' 5.000.000,00',width:160},
    { field: '2.736.978,82' ,width:150},
    { field: '2.250.627,60',width:140},
    {field: '180,82%' , width:140, color:'green'}
];
column = [
  { field: 'Verfügungsrahmen' ,width:160 },
  { field: 'Verpflichtungen' ,width:150},
  { field: 'Saldo aktuell' ,width:140},
  {field: 'Margindeckung' ,width:140}
];
  ngOnInit() {
  }
}

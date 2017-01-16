import {Component, AfterViewInit, Input} from '@angular/core';

@Component({
  selector: 'ticket-info',
  templateUrl: 'tickets_info.component.html',
  styleUrls: ['tickets_info.component.css']
})

export class TicketInfo implements AfterViewInit {
  @Input() linkBody: string;

  ngAfterViewInit() {
    $('#click').click(() => {
      let link = document.createElement("a");
      link.download = "ticket.pdf";
      link.href = "data:application/pdf;base64," + this.linkBody;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    });
  }
}

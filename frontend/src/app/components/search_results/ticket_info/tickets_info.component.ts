import {Component, AfterViewInit, Input} from '@angular/core';

@Component({
  selector: 'ticket-info',
  templateUrl: 'tickets_info.component.html',
  styleUrls: ['tickets_info.component.css']
})

export class TicketInfo implements AfterViewInit {
  @Input() linkBody: string;

  initiate_user_download = function (file_name, mime_type, text) {
    if (undefined === window.navigator.msSaveOrOpenBlob) {
      let e = document.createElement('a');
      let href = 'data:' + mime_type + ';base64,' + encodeURIComponent(text);
      e.setAttribute('href', href);
      e.setAttribute('download', file_name);
      document.body.appendChild(e);
      e.click();
      document.body.removeChild(e);
    }
    else {
      var binary_string = window.atob(text);
      var len = binary_string.length;
      var bytes = new Uint8Array(len);
      for (let i = 0; i < bytes.length; ++i) {
        bytes[i] = binary_string.charCodeAt(i);
      }
      let blob = new Blob([bytes], {type: mime_type});
      window.navigator.msSaveOrOpenBlob(blob, file_name);
    }
  };

  ngAfterViewInit() {
    $('#click').click(() => {
      this.initiate_user_download('ticket.pdf', 'application/pdf', this.linkBody);
    });
  }
}

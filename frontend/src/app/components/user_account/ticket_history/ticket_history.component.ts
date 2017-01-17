import {Component, OnInit} from '@angular/core';
import {TicketService} from "../../../_services/tickets.service";
import {Ticket} from "../../../_mocks/ticket";
declare var $: JQueryStatic;

@Component({
  selector: 'ticket-history',
  templateUrl: 'ticket_history.component.html',
  styleUrls: ['ticket_history.component.css']
})
export class TicketHistory implements OnInit {
  public rows: Array<any> = [];
  public columns: Array<any> = [
    {title: 'Departure Date', name: 'departureDate', sort: false},
    {title: 'Arrival Date', name: 'arrivalDate', sort: false},
    {title: 'From', name: 'from', sort: false},
    {title: 'To', name: 'to', sort: false},
    {title: 'Ticket', name: 'ticket', sort: false},
  ];
  public TableData: Array<any> = [];
  public page: number = 1;
  public itemsPerPage: number = 5;
  public maxSize: number = 5;
  public length: number = 0;

  public config: any = {
    paging: true,
    sorting: {columns: this.columns},
    filtering: {filterString: ''},
    className: ['table-striped', 'table-bordered']
  };

  private data: Array<any> = this.TableData;

  public constructor(private ticketService: TicketService) {
    this.length = this.data.length;
  }

  clicked() {
    if (this.TableData.length > 0)
      $('.history-content').slideToggle('slow');
    $('.btn-group').toggleClass('dropup');
  }

  public ngOnInit(): void {
    $('.history-content').hide();
    $('.no-results').hide();
    this.ticketService.getArchivalTickets().subscribe(
      () => {
        this.ticketService.tickets().subscribe(
          data => {
            for (let ticket of data) {
              this.TableData.push({
                "id": 1,
                "departureDate": ticket.departureDate,
                "arrivalDate": ticket.arrivalDate,
                "from": ticket.from,
                "to": ticket.to,
                "ticket": '<a id="ticket" class="pointer" style="text-decoration: underline">ticket.pdf</a>'
              });
            }
            this.onChangeTable(this.config, this.page);
            $('.loader').hide();
            if (this.TableData.length > 0) {
              $('.history-content').show();
            } else {
              $('.no-results').show()
            }
          },
          error => {
            console.log(error);
          }
        )
      },
      error => {
        console.log(error);
      }
    );
  }

  public changePage(page: any, data: Array<any> = this.data): Array<any> {
    let start = (page - 1) * this.itemsPerPage;
    let end = this.itemsPerPage > -1 ? (start + this.itemsPerPage) : data.length;
    return data.slice(start, end);
  }

  public changeSort(data: any, config: any): any {
    if (!config.sorting) {
      return data;
    }

    let columns = this.config.sorting.columns || [];
    let columnName: string = void 0;
    let sort: string = void 0;

    for (let i = 0; i < columns.length; i++) {
      if (columns[i].sort !== '' && columns[i].sort !== false) {
        columnName = columns[i].name;
        sort = columns[i].sort;
      }
    }

    if (!columnName) {
      return data;
    }

    // simple sorting
    return data.sort((previous: any, current: any) => {
      if (previous[columnName] > current[columnName]) {
        return sort === 'desc' ? -1 : 1;
      } else if (previous[columnName] < current[columnName]) {
        return sort === 'asc' ? -1 : 1;
      }
      return 0;
    });
  }

  public changeFilter(data: any, config: any): any {
    let filteredData: Array<any> = data;
    this.columns.forEach((column: any) => {
      if (column.filtering) {
        filteredData = filteredData.filter((item: any) => {
          return item[column.name].match(column.filtering.filterString);
        });
      }
    });

    if (!config.filtering) {
      return filteredData;
    }

    if (config.filtering.columnName) {
      return filteredData.filter((item: any) =>
        item[config.filtering.columnName].match(this.config.filtering.filterString));
    }

    let tempArray: Array<any> = [];
    filteredData.forEach((item: any) => {
      let flag = false;
      this.columns.forEach((column: any) => {
        if (item[column.name].toString().match(this.config.filtering.filterString)) {
          flag = true;
        }
      });
      if (flag) {
        tempArray.push(item);
      }
    });
    filteredData = tempArray;

    return filteredData;
  }

  public onChangeTable(config: any, page: any = {page: this.page, itemsPerPage: this.itemsPerPage}): any {
    if (config.filtering) {
      Object.assign(this.config.filtering, config.filtering);
    }

    if (config.sorting) {
      Object.assign(this.config.sorting, config.sorting);
    }

    let filteredData = this.changeFilter(this.data, this.config);
    let sortedData = this.changeSort(filteredData, this.config);
    this.rows = page && config.paging ? this.changePage(page, sortedData) : sortedData;
    this.length = sortedData.length;
  }

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

  public onCellClick(data: any): any {
    if (data.column == 'ticket') {
      $('.loader').show();
      $('.history-content').hide();
      let ticket = data.row;
      this.ticketService.getPDFToTicket(new Ticket(ticket.id, ticket.departureDate, ticket.arrivalDate,
        ticket.from, ticket.to)).subscribe(
        data => {
          console.log(data);
          //this.initiate_user_download('ticket.pdf', 'application/pdf', this.data);
          $('.loader').hide();
          $('.history-content').fadeIn("slow");
        }, error => {
          setTimeout(() => {
            $('.loader').hide();
            $('.history-content').fadeIn("slow");
          }, 4000);
          console.log(error);
        }
      );
    }
  }
}

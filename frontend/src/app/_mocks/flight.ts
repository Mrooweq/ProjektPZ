export class Flight {
  airlineName: string;
  airlineShortcut: string;
  price: number;
  departureDate: string;
  arrivalDate: string;
  flightNumber: number;
  from: string;
  to: string;
  _class: string;
  numberOfPlaces: number;

  constructor(airlineName: string, airlineShortcut: string, price: number, departureDate: string, arrivalDate: string, flightNumber: number, from: string, to: string, _class: string, numberOfPlaces: number) {
    this.airlineName = airlineName;
    this.airlineShortcut = airlineShortcut;
    this.price = price;
    this.departureDate = departureDate;
    this.arrivalDate = arrivalDate;
    this.flightNumber = flightNumber;
    this.from = from;
    this.to = to;
    this._class = _class;
    this.numberOfPlaces = numberOfPlaces;
  }
}

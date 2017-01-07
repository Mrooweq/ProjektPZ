export class Flight {
  airlineName: string;
  price: number;
  flightDate: string;
  flightNumber: number;
  from: string;
  to: string;

  constructor(airlineName: string, price: number, flightDate: string, flightNumber: number, from: string, to: string) {
    this.airlineName = airlineName;
    this.price = price;
    this.flightDate = flightDate;
    this.flightNumber = flightNumber;
    this.from = from;
    this.to = to;
  }
}

import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { WarehouseReceiptDetail } from '../model/warehouse-receipt-detail';
import { WarehouseReceiptDetailRequest } from '../request/warehouse-receipt-detail-request';
import { WarehouseReceiptDetailResponse } from '../response/warehouse-receipt-detail-response';
import { WarehouseReceiptResponse } from '../response/warehouse-receipt-response';
const API_URL = 'http://localhost:8080/api/';
@Injectable({
  providedIn: 'root'
})
export class WarehouseReceiptService {

  items!: WarehouseReceiptDetail | null;

  constructor(private http: HttpClient) { }

  add(warehouseReceiptDetail: WarehouseReceiptDetail){
    let local_storage;
    let itemsInWarehouseReceipt = [];
    this.items = warehouseReceiptDetail;
    if(localStorage.getItem('warehouse')  == null){
      local_storage =[];
      console.log("LOCALSTORAGE NULL",JSON.parse(localStorage.getItem('warehouse')  || '{}'));
      console.log(this.items);
      itemsInWarehouseReceipt.push(this.items);
      localStorage.setItem('warehouse', JSON.stringify(itemsInWarehouseReceipt));
      console.log('Pushed first Item: ', itemsInWarehouseReceipt);
    }else
    {
      local_storage = JSON.parse(localStorage.getItem('warehouse') || '{}');
      console.log("LOCAL STORAGE HAS ITEMS",JSON.parse(localStorage.getItem('warehouse') || '{}'));
      for(var i in local_storage)
      {
        if(this.items.productId == local_storage[i].productId && this.items.sizeId == local_storage[i].sizeId && this.items.colorId == local_storage[i].colorId)
        {
          console.log('same product! index is ', i); 
          this.items = null;
          break;  
        }
      }
      if(this.items){
        itemsInWarehouseReceipt.push(this.items);
      }
      local_storage.forEach(function (item: any){
        itemsInWarehouseReceipt.push(item);
      })
      localStorage.setItem('warehouse', JSON.stringify(itemsInWarehouseReceipt));
    }
  }

  getItems(){
    console.log("warehouse: ", JSON.parse(localStorage.getItem('warehouse') || '{}'));
    return this.items = JSON.parse(localStorage.getItem('warehouse') || '{}');
   }

   createWarehouseReceipt(token: String, employeeId: string, total: number): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'admin/add-warehouseReceipt', {employeeId: employeeId, amount: total}, { headers: headers, responseType: 'text'})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  createWarehouseReceiptDetail(token: String, warehouseReceiptDetailRequest: WarehouseReceiptDetailRequest): Observable<any> {
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.post(API_URL + 'admin/add-warehouseReceiptDetail', warehouseReceiptDetailRequest, { headers: headers, responseType: 'text'})
                  .pipe(
                    retry(3), 
                    catchError(this.handleError));
  }

  getAllWarehouseReceipt(token: String): Observable<WarehouseReceiptResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<WarehouseReceiptResponse[]>(API_URL + 'admin/warehouseReceipt', { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getAllWarehouseReceiptDetailByWarehouseReceiptId(token: string, warehouseReceiptId: string): Observable<WarehouseReceiptDetailResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    return this.http.get<WarehouseReceiptDetailResponse[]>(API_URL + 'admin/warehouseReceiptDetail/' + warehouseReceiptId, { headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  getAllWarehouseReceiptBySearch(token: string, warehouseReceiptId: string, employeeId: string, fromDate: string, toDate: string): Observable<WarehouseReceiptResponse[]>{
    let tokenStr = 'Bearer ' + token;
    const headers = new HttpHeaders().set('Authorization', tokenStr);
    let params = new HttpParams();
    params = params.append('warehouseReceiptId', warehouseReceiptId);
    params = params.append('employeeId', employeeId);
    params = params.append('fromDate', fromDate);
    params = params.append('toDate', toDate);
    return this.http.get<WarehouseReceiptResponse[]>(API_URL + 'admin/warehouseReceipt/search', {params: params, headers: headers})
                  .pipe(
                    catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    // return an observable with a user-facing error message
    return throwError(
      'Something bad happened; please try again later.');
  };

  clearCart(){
    localStorage.removeItem('warehouse');
  }
}

import { Pipe, PipeTransform } from '@angular/core';
import { Product } from '../model/product';

@Pipe({
  name: 'searchProduct'
})
export class SearchProductPipe implements PipeTransform {

  transform(products: Product[], searchValue: string): Product[] {
    if(!products || !searchValue){
      return products;
    }
    return products.filter(product => 
      product.productId.toLocaleLowerCase().includes(searchValue.toLocaleLowerCase()));
  }

}

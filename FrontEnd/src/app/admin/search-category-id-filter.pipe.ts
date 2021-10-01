import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../model/category';

@Pipe({
  name: 'searchCategoryIdFilter'
})
export class SearchCategoryIdFilterPipe implements PipeTransform {

  transform(categories: Category[], searchIdValue: string): Category[] {
    if(!categories || !searchIdValue){
      return categories;
    }
    return categories.filter(category => 
      category.categoryId.toLocaleLowerCase().includes(searchIdValue.toLocaleLowerCase()));
  }

}

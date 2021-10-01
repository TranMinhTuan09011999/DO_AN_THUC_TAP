import { Pipe, PipeTransform } from '@angular/core';
import { Category } from '../model/category';

@Pipe({
  name: 'searchCategoryNameFilter'
})
export class SearchCategoryNameFilterPipe implements PipeTransform {

  transform(categories: Category[], searchCategoryNameValue: string): Category[] {
    if(!categories || !searchCategoryNameValue){
      return categories;
    }
    return categories.filter(category => 
      category.categoryName.toLocaleLowerCase().includes(searchCategoryNameValue.toLocaleLowerCase()));
  }

}

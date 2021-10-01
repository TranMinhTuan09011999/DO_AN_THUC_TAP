import { Pipe, PipeTransform } from '@angular/core';
import { Provider } from '../model/provider';

@Pipe({
  name: 'searchProviderNameFilter'
})
export class SearchProviderNameFilterPipe implements PipeTransform {

  transform(providers: Provider[], searchNameValue: string): Provider[] {
    if(!providers || !searchNameValue){
      return providers;
    }
    return providers.filter(provider => 
      provider.providerName.toLocaleLowerCase().includes(searchNameValue.toLocaleLowerCase()));
  }

}

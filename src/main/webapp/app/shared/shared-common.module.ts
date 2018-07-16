import { NgModule } from '@angular/core';

import { JhipsterNewsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [JhipsterNewsSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [JhipsterNewsSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class JhipsterNewsSharedCommonModule {}

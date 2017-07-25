package com.wisedu.cpdaily.ui.contact.search;


import com.wisedu.cpdaily.di.components.NetComponent;
import com.wisedu.cpdaily.di.scope.ScopeFragment;

import dagger.Component;

/**
 * 注入器
 */
@ScopeFragment
@Component(dependencies = NetComponent.class, modules = SearchModule.class)
interface SearchComponent {
    void inject(SearchFragment fragment);
}

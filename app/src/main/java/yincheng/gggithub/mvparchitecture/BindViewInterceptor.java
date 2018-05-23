/*
 * Copyright (C) 2017 grandcentrix GmbH
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package yincheng.gggithub.mvparchitecture;


import yincheng.gggithub.provider.annotation.CallOnMainThread;
import yincheng.gggithub.mvparchitecture.distinctuntilchanged.DistinctUntilChanged;

/**
 * Intercepts the supply of the {@link TiView} binding to the {@link TiPresenter}. Allows to proxy
 * the view to add behaviors like {@link DistinctUntilChanged} or {@link
 * CallOnMainThread}
 */
public interface BindViewInterceptor {

    <V extends TiView> V intercept(final V view);

}

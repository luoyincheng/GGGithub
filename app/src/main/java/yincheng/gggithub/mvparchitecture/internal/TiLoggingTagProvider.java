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

package yincheng.gggithub.mvparchitecture.internal;

/**
 * Interface providing access to a logging tag for composition classes. Provides better tags for
 * the log output
 */
public interface TiLoggingTagProvider {

    /**
     * @return the tag which should be used for logging
     */
    String getLoggingTag();
}

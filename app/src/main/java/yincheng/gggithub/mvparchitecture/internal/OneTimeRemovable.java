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

import java.util.concurrent.atomic.AtomicBoolean;

import yincheng.gggithub.mvparchitecture.Removable;

/**
 * Removable which allows removing only once
 */
public abstract class OneTimeRemovable implements Removable {

   /**
    * Atomically:原子的，
    * "原子操作(atomic operation)是不需要synchronized"，这是Java多线程编程的老生常谈了。
    * 所谓原子操作是指不会被线程调度机制打断的操作；这种操作一旦开始，就一直运行到结束，中间不会有任何
    * context switch （切 [1]  换到另一个线程）。
    * <p>
    * 如果这个操作所处的层(layer)的更高层不能发现其内部实现与结构，那么这个操作是一个原子(atomic)操作。
    * 原子操作可以是一个步骤，也可以是多个操作步骤，但是其顺序不可以被打乱，也不可以被切割而只执行其中的一部分。
    * 将整个操作视作一个整体是原子性的核心特征。
    * 原子操作，可以是多个操作步骤，但是
    * 1.顺序不能被打乱
    * 2.不能被切割而只执行其中的一部分
    */
   private final AtomicBoolean removed = new AtomicBoolean(false);

   @Override
   public boolean isRemoved() {
      return removed.get();
   }

   /**
    * Called when the added Object should be removed. Only called once
    */
   public abstract void onRemove();

   @Override
   public void remove() {
      // allow calling remove only once
      if (removed.compareAndSet(false, true)) {
         onRemove();
      }
   }
}

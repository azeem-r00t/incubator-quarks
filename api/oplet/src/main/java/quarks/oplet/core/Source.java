/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package quarks.oplet.core;

import java.util.Collections;
import java.util.List;

import quarks.function.Consumer;
import quarks.oplet.OpletContext;

public abstract class Source<T> extends AbstractOplet<Void, T> {

    private Consumer<T> destination;

    @Override
    public void initialize(OpletContext<Void, T> context) {
        super.initialize(context);

        destination = context.getOutputs().get(0);
    }

    protected Consumer<T> getDestination() {
        return destination;
    }
    
    /**
     * Submit a tuple to single output.
     * @param tuple Tuple to be submitted.
     */
    protected void submit(T tuple) {
        getDestination().accept(tuple);
    }

    @Override
    public final List<Consumer<Void>> getInputs() {
        return Collections.emptyList();
    }
}

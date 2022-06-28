/*
 * Copyright 2010 Luke Daley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.remotecontrol.client

import io.remotecontrol.groovy.client.ClosureCommandGenerator
import io.remotecontrol.groovy.client.RawClosureCommand
import spock.lang.Specification
import spock.lang.Unroll

class CommandGeneratorSpec extends Specification {

    def generator = new ClosureCommandGenerator(this.getClass().classLoader)

    @Unroll
    def "support size #size"() {
        expect:
        generator.generate(new RawClosureCommand(command, Collections.emptyList())).supports.size() == size
        command instanceof Closure

        where:
        command                      | size
        TestClosures.testClosureZero | 0
        TestClosures.testClosureOne  | 1
        TestClosures.testClosureFour | 4
    }
}

class TestClosures {
    static Closure testClosureZero = { -> "123" }
    static Closure testClosureOne = { -> def c = { -> "123" } }
    static Closure testClosureFour = { -> def c = { -> def a = { -> def b = { -> } } }; def d = { -> } }
}
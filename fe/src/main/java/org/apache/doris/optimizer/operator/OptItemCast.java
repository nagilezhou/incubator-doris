// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.apache.doris.optimizer.operator;

import org.apache.doris.catalog.Type;
import org.apache.doris.optimizer.OptUtils;

// indicates cast expression, such as 'cast(c1 as int)'
// OptItemCast
// |--- child
public class OptItemCast extends OptItem {
    private Type destType;

    public OptItemCast(Type destType) {
        super(OptOperatorType.OP_ITEM_CAST);
        this.destType = destType;
    }

    public Type getDestType() { return destType; }

    @Override
    public Type getReturnType() {
        return destType;
    }

    @Override
    public int hashCode() {
        return OptUtils.combineHash(super.hashCode(), destType.getPrimitiveType());
    }

    @Override
    public boolean equals(Object object) {
        if (!super.equals(object)) {
            return false;
        }
        OptItemCast rhs = (OptItemCast) object;
        return destType.equals(rhs.destType);
    }

    @Override
    public String getExplainString(String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix).append("ItemCast(type=").append(type).append(")");
        return sb.toString();
    }
}
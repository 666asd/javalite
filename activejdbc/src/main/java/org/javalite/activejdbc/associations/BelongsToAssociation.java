/*
Copyright 2009-2019 Igor Polevoy

Licensed under the Apache License, Version 2.0 (the "License"); 
you may not use this file except in compliance with the License. 
You may obtain a copy of the License at 

http://www.apache.org/licenses/LICENSE-2.0 

Unless required by applicable law or agreed to in writing, software 
distributed under the License is distributed on an "AS IS" BASIS, 
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
See the License for the specific language governing permissions and 
limitations under the License. 
*/


package org.javalite.activejdbc.associations;

import org.javalite.activejdbc.Model;

import java.util.Map;

/**
 * This association indicates that a source table belongs to the target table.
 * It is used for one to many relationships.
 *
 * @author Igor Polevoy
 */
public class BelongsToAssociation extends Association {

    public static final String FK = "FK";

    private String fkName;

    public BelongsToAssociation(Map<String, Object> map) throws ClassNotFoundException {
        super(map);
        fkName = (String) map.get(FK);
    }

    public BelongsToAssociation(Class<? extends Model> source, Class<? extends Model> target, String fkName) {
        super(source, target);
        this.fkName = fkName;
    }

    public String getFkName() {
        return fkName;
    }
    
    @Override
    public String toString() {
        return getSourceClass().getSimpleName() + "  >----------  " + getTargetClass().getSimpleName() + ", type: " + "belongs-to";
    }

    @Override
    public boolean equals(Object other) {
        
        if(other == null || !other.getClass().equals(getClass())){
            return false;
        }

        BelongsToAssociation otherAss =(BelongsToAssociation )other;

        return otherAss.fkName.equals(fkName)
                && otherAss.getSourceClass().equals(getSourceClass())
                && otherAss.getTargetClass().equals(getTargetClass());
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put(FK, fkName);
        return map;
    }

}

/**
 * The MIT License
 * Copyright © 2017 WebFolder OÜ
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.webfolder.cdp.type.indexeddb;

import java.util.ArrayList;
import java.util.List;

/**
 * Database with an array of object stores
 */
public class DatabaseWithObjectStores {
    private String name;

    private Integer version;

    private List<ObjectStore> objectStores = new ArrayList<>();

    /**
     * Database name.
     */
    public String getName() {
        return name;
    }

    /**
     * Database name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Database version.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Database version.
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Object stores in this database.
     */
    public List<ObjectStore> getObjectStores() {
        return objectStores;
    }

    /**
     * Object stores in this database.
     */
    public void setObjectStores(List<ObjectStore> objectStores) {
        this.objectStores = objectStores;
    }
}

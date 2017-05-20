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
package io.webfolder.cdp.command;

import java.util.List;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.cachestorage.Cache;
import io.webfolder.cdp.type.cachestorage.RequestEntriesResult;

@Experimental
@Domain("CacheStorage")
public interface CacheStorage {
    /**
     * Requests cache names.
     * 
     * @param securityOrigin Security origin.
     * 
     * @return Caches for the security origin.
     */
    @Returns("caches")
    List<Cache> requestCacheNames(String securityOrigin);

    /**
     * Requests data from cache.
     * 
     * @param cacheId ID of cache to get entries from.
     * @param skipCount Number of records to skip.
     * @param pageSize Number of records to fetch.
     * 
     * @return RequestEntriesResult
     */
    RequestEntriesResult requestEntries(String cacheId, Integer skipCount, Integer pageSize);

    /**
     * Deletes a cache.
     * 
     * @param cacheId Id of cache for deletion.
     */
    void deleteCache(String cacheId);

    /**
     * Deletes a cache entry.
     * 
     * @param cacheId Id of cache where the entry will be deleted.
     * @param request URL spec of the request.
     */
    void deleteEntry(String cacheId, String request);
}

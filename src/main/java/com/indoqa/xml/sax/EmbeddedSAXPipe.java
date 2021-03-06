/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.indoqa.xml.sax;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * This class implements a ContentHandler for embedding a full SAX event stream into an existing stream of SAX events. An instance of
 * this class will pass unmodified all the SAX events to the linked ContentHandler, but it will ignore the startDocument/endDocument
 * and startDTD/endDTD events, as well as all comment events within the DTD.
 *
 * @version $Id: EmbeddedSAXPipe.java 729283 2008-12-24 09:25:21Z cziegeler $
 */
public class EmbeddedSAXPipe extends AbstractSAXPipe {

    private boolean inDTD;

    /**
     * Creates an EmbeddedXMLPipe that writes into the given ContentHandler.
     */
    public EmbeddedSAXPipe(ContentHandler handler) {
        this.setContentHandler(handler);
    }

    /**
     * Ignore all <code>comment</code> events if between startDTD/endDTD events.
     *
     * @exception SAXException if an error occurs
     */
    @Override
    public void comment(char ch[], int start, int len) throws SAXException {
        if (!this.inDTD) {
            super.comment(ch, start, len);
        }
    }

    /**
     * Ignore the <code>endDocument</code> event: this method does nothing.
     *
     * @exception SAXException if an error occurs
     */
    @Override
    public void endDocument() throws SAXException {
        // empty
    }

    /**
     * Ignore the <code>endDTD</code> event: this method does nothing.
     *
     * @exception SAXException if an error occurs
     */
    @Override
    public void endDTD() throws SAXException {
        // Ignored
        this.inDTD = false;
    }

    /**
     * Ignore the <code>startDocument</code> event: this method does nothing.
     *
     * @exception SAXException if an error occurs
     */
    @Override
    public void startDocument() throws SAXException {
        // empty
    }

    /**
     * Ignore the <code>startDTD</code> event: this method does nothing.
     *
     * @exception SAXException if an error occurs
     */
    @Override
    public void startDTD(String name, String publicId, String systemId) throws SAXException {
        // Ignored
        this.inDTD = true;
    }
}

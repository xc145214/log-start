/**
 * **********************************************************************
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * <p/>
 * COPYRIGHT (C) HONGLING CAPITAL CORPORATION 2012
 * ALL RIGHTS RESERVED BY HONGLING CAPITAL CORPORATION. THIS PROGRAM
 * MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY
 * HONGLING CAPITAL CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED
 * OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN
 * PERMISSION OF HONGLING CAPITAL CORPORATION. USE OF COPYRIGHT NOTICE
 * DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.
 * HONGLING CAPITAL CONFIDENTIAL AND PROPRIETARY
 * ***********************************************************************
 */
package com.xc.easyMock;

import java.util.HashMap;
import java.util.Map;

/**
 *  准备测试的类。
 *
 *  @author xiachuan at 2016/10/19 16:10。
 */

public class ClassTested {

    private Collaborator listener;

    private final Map<String, String> documents = new HashMap<String, String>();

    public void setListener(Collaborator listener) {
        this.listener = listener;
    }

    public void addDocument(String title, String content) {
        boolean documentChange = documents.containsKey(title);
        documents.put(title, content);
        if (documentChange) {
            notifyListenersDocumentChanged(title);
        } else {
            notifyListenersDocumentAdded(title);
        }
    }

    public boolean removeDocument(String title) {
        if (!documents.containsKey(title)) {
            return true;
        }

        if (!listenersAllowRemoval(title)) {
            return false;
        }

        documents.remove(title);
        notifyListenersDocumentRemoved(title);

        return true;
    }

    public boolean removeDocuments(String... titles) {
        if (!listenersAllowRemovals(titles)) {
            return false;
        }

        for (String title : titles) {
            documents.remove(title);
            notifyListenersDocumentRemoved(title);
        }
        return true;
    }

    private void notifyListenersDocumentAdded(String title) {
        listener.documentAdded(title);
    }

    private void notifyListenersDocumentChanged(String title) {
        listener.documentChanged(title);
    }

    private void notifyListenersDocumentRemoved(String title) {
        listener.documentRemoved(title);
    }

    private boolean listenersAllowRemoval(String title) {
        return listener.voteForRemoval(title) > 0;
    }

    private boolean listenersAllowRemovals(String... titles) {
        return listener.voteForRemovals(titles) > 0;
    }
}


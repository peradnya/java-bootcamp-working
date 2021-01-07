package com.peradnya.view;

import com.peradnya.model.Page;
import com.peradnya.model.Summary;

public interface TerminalViewCallback {
    void onPageChanged(Page page, Summary summary);
}

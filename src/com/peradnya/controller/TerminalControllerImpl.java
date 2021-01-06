package com.peradnya.controller;

import com.peradnya.view.TerminalViewCallback;

public class TerminalControllerImpl implements TerminalController {
    private TerminalViewCallback view;
    private int page;

    public TerminalControllerImpl(TerminalViewCallback view) {
        this.page = 0;
        this.view = view;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
        view.onPageChanged(page);
    }

    @Override
    public int getPage() {
        return page;
    }
}

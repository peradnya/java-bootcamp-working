package com.peradnya.view;

import com.peradnya.controller.TerminalController;
import com.peradnya.controller.TerminalControllerImpl;

public class TerminalView implements TerminalViewCallback {
    private final TerminalController controller;

    public TerminalView() {
        controller = new TerminalControllerImpl(this);
    }

    public void show() {
        while (true) {

        }
    }

    @Override
    public void onPageChanged(int page) {

    }
}

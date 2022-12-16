package task_b;

import task_b.Garden;

public class FileMonitor extends Monitor {
    public FileMonitor(Garden garden) {
        super(garden, "File monitor");
    }

    @Override
    protected void monitor() {
        garden.writeIntoFile();
    }
}
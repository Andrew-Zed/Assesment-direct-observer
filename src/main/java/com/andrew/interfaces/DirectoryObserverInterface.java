package com.andrew.interfaces;

public interface DirectoryObserverInterface {
    void startObserving(boolean fileInDirectory, FileManager textManager);
    void StopObserving();

}

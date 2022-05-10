package com.andrew;

import com.andrew.Statistics.MostUsedWordStat;
import com.andrew.Statistics.NumDotsStatistics;
import com.andrew.Statistics.NumWordsStatistics;
import com.andrew.fileManagers.TextManager;
import com.andrew.interfaces.Statistics;
import org.junit.Test;
import org.mockito.Mockito;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
public class DirObserverTest {

    Path processedFolder;
    Path unSupportedFilesFolder;
    boolean fileInDirectory;
    TextManager textManagerMock;
    DirObserver dirObserverMock;
    Path path = Path.of("com.dataAnalysis.fileManagers");
    Path destination = Path.of("test.txt");

    @Test
    public void testStartObserving() {
        textManagerMock = Mockito.mock(TextManager.class);
        dirObserverMock = Mockito.mock(DirObserver.class);
        doNothing().when(dirObserverMock).startObserving(fileInDirectory, textManagerMock);
        dirObserverMock.startObserving(fileInDirectory, textManagerMock);
        verify(dirObserverMock, times(1)).startObserving(fileInDirectory, textManagerMock);
    }

    @Test
    public void testProcessFiles() {
        textManagerMock = Mockito.mock(TextManager.class);
        dirObserverMock = Mockito.mock(DirObserver.class);
        when(textManagerMock.getFileExtension()).thenReturn("test.txt");
        doNothing().when(dirObserverMock).processFiles(path, textManagerMock);
        dirObserverMock.processFiles(path, textManagerMock);
        verify(dirObserverMock, times(1)).processFiles(path, textManagerMock);
        assertTrue(destination.getFileName().toString().toLowerCase(Locale.ROOT).endsWith(textManagerMock.getFileExtension()));

    }

    @Test
    public void testPrintStats() {

        String fileContent = "Not all who wander are lost. Some may be lost. We don't know for sure...";
        NumWordsStatistics numOfWords = new NumWordsStatistics();
        NumDotsStatistics numOfDots = new NumDotsStatistics();
        MostUsedWordStat mostUsedWord = new MostUsedWordStat();
        List<Statistics> statsArray = Arrays.asList(numOfWords, numOfDots, mostUsedWord);
        assertEquals(3, statsArray.size());

        assertEquals("15", statsArray.get(statsArray.indexOf(numOfWords)).getStatistics(fileContent).getValueOfStat());
        assertEquals("5", statsArray.get(statsArray.indexOf(numOfDots)).getStatistics(fileContent).getValueOfStat());
        assertEquals("lost.", statsArray.get(statsArray.indexOf(mostUsedWord)).getStatistics(fileContent).getValueOfStat());

    }


}



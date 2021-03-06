package ru.wref.service;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import java.io.IOException;

public class XdotScript {
  int iExitValue;
  String sCommandString;

  public void runScript(String command){
    sCommandString = command;
    CommandLine oCmdLine = CommandLine.parse(sCommandString);
    DefaultExecutor oDefaultExecutor = new DefaultExecutor();
    oDefaultExecutor.setExitValue(0);
    try {
      iExitValue = oDefaultExecutor.execute(oCmdLine);
    } catch (ExecuteException e) {
      System.err.println("Execution failed.");
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("permission denied.");
      e.printStackTrace();
    }
  }
}

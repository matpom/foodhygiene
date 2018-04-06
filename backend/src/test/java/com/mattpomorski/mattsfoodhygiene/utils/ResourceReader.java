package com.mattpomorski.mattsfoodhygiene.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class ResourceReader {

  public static String readResourceAsString(String fileName) {
    try {
      return IOUtils.toString(
          ResourceReader.class.getClassLoader().getResourceAsStream(fileName), "UTF-8"
      );
    } catch (IOException e) {
      throw new IllegalArgumentException("Unable to read resource " + fileName);
    }
  }
}

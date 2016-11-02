/*
 * Copyright (c) 2016 WisePersist.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wisepersist.gwt.uploader.client.events;

import org.wisepersist.gwt.uploader.client.File;

/**
 * Provides access to the raw information provided by Uploader when a 'fileQueueError' event occurs.
 * This class should not be instantiated directly, but instead you should create a {@link
 * FileQueueErrorHandler} and register it via the {@link org.wisepersist.gwt.uploader.client
 * .Uploader#setFileQueueErrorHandler(FileQueueErrorHandler)} method in order to access file
 * queue error events.
 *
 * <p>Note that the 'fileQueueError' event is fired for each file that was not queued after the
 * File Selection Dialog window is closed. A file may not be queued for several reasons such as,
 * the file exceeds the file size, the file is empty or a file or queue limit has been exceeded.
 *
 * <p>The reason for the queue error is specified by the {@link #getErrorCode()} method.
 *
 * @author delight.wjk@gmail.com
 */
public class FileQueueErrorEvent {

  /**
   * An enumeration of reported file queue error code types.
   *
   * @author delight.wjk@gmail.com
   */
  @SuppressWarnings("JavadocVariable")
  public enum ErrorCode {

    UNKNOWN(0),
    QUEUE_LIMIT_EXCEEDED(-100),
    FILE_EXCEEDS_SIZE_LIMIT(-110),
    ZERO_BYTE_FILE(-120),
    INVALID_FILETYPE(-130);

    private final int optionValue;

    /**
     * Constructs a new {@link ErrorCode} instance.
     *
     * @param optionValue The option value specified.
     */
    ErrorCode(final int optionValue) {
      this.optionValue = optionValue;
    }

    /**
     * Converts to int value.
     *
     * @return The int value converted.
     */
    public final int toInt() {
      return optionValue;
    }
  }

  private File file;
  private int errorCode;
  private String message;

  /**
   * This constructor is intended for internal use only.  You should not create file queued events
   * directly, but instead should register a {@link FileQueuedHandler}.
   *
   * @param file The file instance that experienced an error in the queue.
   * @param errorCode The error code returned by the Uploader component in the case of a file
   *     queue failure.
   * @param message A human readable error message explaining the cause of the file queue failure.
   */
  public FileQueueErrorEvent(final File file, final int errorCode, final String message) {
    this.file = file;
    this.errorCode = errorCode;
    this.message = message;
  }

  /**
   * Return the details of the file that experienced the error in the queue.
   *
   * @return File
   */
  public final File getFile() {
    return file;
  }

  /**
   * The error code returned by the Uploader component in the case of a file queu failure,
   * conveniently converted to an enumeration type.
   *
   * @return ErrorCode
   */
  public final ErrorCode getErrorCode() {
    ErrorCode result = ErrorCode.UNKNOWN;
    for (final ErrorCode code : ErrorCode.values()) {
      if (errorCode == code.toInt()) {
        result = code;
        break;
      }
    }
    return result;
  }

  /**
   * The original numeric error code returned by the Uploader component in the case of a file
   * queue failure.
   *
   * @return The error code as int value.
   */
  public final int getErrorCodeAsInt() {
    return errorCode;
  }

  /**
   * Return a human readable error message explaining the cause of the file queue failure.
   *
   * @return The message string.
   */
  public final String getMessage() {
    return message;
  }
}

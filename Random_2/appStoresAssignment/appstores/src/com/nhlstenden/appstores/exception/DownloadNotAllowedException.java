package com.nhlstenden.appstores.exception;

public class DownloadNotAllowedException extends Exception
{
    public DownloadNotAllowedException(String errorMessage)
    {
        super(errorMessage);
    }
}

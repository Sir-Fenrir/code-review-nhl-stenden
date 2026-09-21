package com.nhlstenden.appstoreinvestigation;

public class DownloadNotAllowedException extends Exception
{
    public DownloadNotAllowedException(String message)
    {
        super(message);
    }
}
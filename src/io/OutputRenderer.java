package io;

public interface OutputRenderer
{
    void print(String message);
    void println(String message);
    void printf(String message, Object... args);
}

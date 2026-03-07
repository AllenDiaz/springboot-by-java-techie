package com.demo.webflux.model;

/**
 * Simple immutable value object returned by reactive endpoints.
 */
public record Message(String content, String timestamp) {}

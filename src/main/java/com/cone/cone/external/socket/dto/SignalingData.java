package com.cone.cone.external.socket.dto;

public record SignalingData(
    Object candidate,
    Object sdp
) {}
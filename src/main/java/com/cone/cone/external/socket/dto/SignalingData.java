package com.cone.cone.external.socket.dto;

public record SignalingData(
    Long roomId,
    Object candidate,
    Object sdp
) {}
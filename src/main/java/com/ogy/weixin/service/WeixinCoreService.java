package com.ogy.weixin.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface WeixinCoreService {
//    public String weixinMessageHandelCoreService();

    public String getRespose(Map<String, String> parseXml);
}

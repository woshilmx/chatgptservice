package com.lmx.project.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatModel {
    /**
     * 角色
     */
    private String role;

    /**
     * 对话的内容
     */
    private String content;
}

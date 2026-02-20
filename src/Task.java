import java.util.*;

public class Task {
    public final String type; //Тип задачи (строка, совпадающая с именем команды)
    public final Map<String, Object> params; //Параметры задачи (например, растение, объём)
    public Task(String type, Map<String, Object> params) { this.type = type; this.params = params; }
}

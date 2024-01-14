package com.example.taskmanagerapi.constants;

public class Constants {
    public static final String DEFAULT_USER_REGISTRATION_REQUEST = "{\n"
            + "    \"email\":\"alice.smith@gmail.com\",\n"
            + "    \"name\":\"alice smith\",\n"
            + "    \"password\":\"Alice12345%\",\n"
            + "    \"repeatPassword\":\"Alice12345%\"\n"
            + "}";

    public static final String DEFAULT_USER_LOGIN_REQUEST = "{\n"
            + "    \"email\":\"alice.smith@gmail.com\",\n"
            + "    \"password\":\"Alice12345%\"\n"
            + "}";

    public static final String DEFAULT_USER_UPDATE_REQUEST = "{\n"
            + "    \"email\": \"alice.smith@gmail.com\",\n"
            + "    \"name\": \"updated Name\",\n"
            + "    \"phone\": \"+3801234567\"\n"
            + "}";

    public static final String DEFAULT_TASK_REQUEST = "{\n"
            + "    \"title\": \"Example Task Title\",\n"
            + "    \"description\": \"Example Task Description\",\n"
            + "    \"priority\": \"HIGH\",\n"
            + "    \"status\": \"NOT_STARTED\",\n"
            + "    \"dueDate\": \"2023-11-11\",\n"
            + "    \"userId\": 1,\n"
            + "    \"taskGroupId\": 1\n"
            + "}";

    public static final String DEFAULT_UPDATE_TASK_REQUEST = "{\n"
            + "    \"title\": \"Updated Task Title\",\n"
            + "    \"description\": \"Updated Task Description\",\n"
            + "    \"priority\": \"MEDIUM\",\n"
            + "    \"status\": \"IN_PROGRESS\",\n"
            + "    \"dueDate\": \"2023-12-31\",\n"
            + "    \"userId\": 1,\n"
            + "    \"taskGroupId\": 1\n"
            + "}";

    public static final String DEFAULT_TASK_GROUP_REQUEST = "{\n"
            + "    \"name\": \"Example Task Group Title\"\n"
            + "}";
}


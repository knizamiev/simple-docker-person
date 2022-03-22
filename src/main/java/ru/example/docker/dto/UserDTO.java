package ru.example.docker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.example.docker.model.User;

import java.util.Date;

public class UserDTO extends User {

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    @Override
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
